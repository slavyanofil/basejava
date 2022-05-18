package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    public static final Resume EMPTY = new Resume();
    private static final long serialVersionUID = 1L;

    static {
        EMPTY.addSection(SectionType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.addSection(SectionType.PERSONAL, TextSection.EMPTY);
        EMPTY.addSection(SectionType.ACHIEVEMENT, ListSection.EMPTY);
        EMPTY.addSection(SectionType.QUALIFICATIONS, ListSection.EMPTY);
        EMPTY.addSection(SectionType.EXPERIENCE, new OrganizationSection(Organization.EMPTY));
        EMPTY.addSection(SectionType.EDUCATION, new OrganizationSection(Organization.EMPTY));
    }

    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    // Unique identifier
    private String uuid;
    private String fullName;

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public AbstractSection getSection(SectionType key) {
        return sections.get(key);
    }

    public String getContact(ContactType key) {
        return contacts.get(key);
    }

    public void addContact(ContactType key, String contact) {
        contacts.put(key, contact);
    }

    public void addSection(SectionType key, AbstractSection section) {
        sections.put(key, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return Objects.equals(contacts, resume.contacts) && uuid.equals(resume.uuid) && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, uuid, fullName);
    }

    @Override
    public String toString() {
        return "[" + uuid + "]" + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int comparator = fullName.compareTo(o.fullName);
        return comparator != 0 ? comparator : uuid.compareTo(o.uuid);
    }
}
