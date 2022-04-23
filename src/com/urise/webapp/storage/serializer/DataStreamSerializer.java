package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.urise.webapp.model.SectionType.*;

public class DataStreamSerializer implements SerializationStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection(r.getSections().entrySet(), dos, entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                if (type.equals(OBJECTIVE) || type.equals(PERSONAL)) {
                    dos.writeUTF(((TextSection) section).getText());
                } else if (type.equals(ACHIEVEMENT) || type.equals(QUALIFICATIONS)) {
                    writeCollection(((ListSection) section).getItems(), dos, dos::writeUTF);
                } else {
                    writeCollection(((OrganizationSection) section).getOrganizations(), dos, organization -> {
                        dos.writeUTF(organization.getHomePage().getName());
                        dos.writeUTF(organization.getHomePage().getUrl());
                        writeCollection(organization.getPositions(), dos, position -> {
                            writeLocalDate(position.getStartDate(), dos);
                            writeLocalDate(position.getEndDate(), dos);
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF(position.getDescription());
                        });
                    });
                }
            });
        }
    }

    private <T> void writeCollection(Collection<T> collection, DataOutputStream
            dos, ItemWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void writeLocalDate(LocalDate ld, DataOutputStream dos) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(() -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()), dis);
            readItems(() -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(sectionType, dis));
            }, dis);
            return resume;
        }
    }

    private void readItems(ItemRunner runner, DataInputStream dis) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            runner.run();
        }
    }

    private AbstractSection readSection(SectionType type, DataInputStream dis) throws IOException {
        if (type.equals(OBJECTIVE) || type.equals(PERSONAL)) {
            return new TextSection(dis.readUTF());
        } else if (type.equals(ACHIEVEMENT) || type.equals(QUALIFICATIONS)) {
            return new ListSection(readList(dis, dis::readUTF));
        } else {
            return new OrganizationSection(
                    readList(dis, () -> new Organization(
                            new Link(dis.readUTF(), dis.readUTF()),
                            readList(dis, () -> new Organization.Position(
                                    readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
                            ))
                    )));
        }
    }

    private <T> List<T> readList(DataInputStream dis, ItemReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private interface ItemWriter<T> {
        void write(T item) throws IOException;
    }

    private interface ItemRunner {
        void run() throws IOException;
    }

    private interface ItemReader<T> {
        T read() throws IOException;
    }
}
