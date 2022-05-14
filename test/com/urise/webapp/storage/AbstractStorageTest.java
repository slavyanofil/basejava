package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.urise.webapp.ResumeTestData.createResume;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final Resume RESUME_1 = createResume(UUID_1, "User2");
    private static final Resume RESUME_2 = createResume(UUID_2, "User1");
    private static final Resume RESUME_3 = createResume(UUID_3, "User3");
    private final List<String> achievements = new ArrayList<>();
    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        RESUME_1.addContact(ContactType.EMAIL, "ya@ya.ru");
        RESUME_1.addContact(ContactType.MOBILE, "800-555-5555");
        achievements.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        achievements.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));
    }

    @Test
    public void save() {
        Resume r = createResume(UUID_4, "User4");
        storage.save(r);
        assertEquals(4, storage.size());
        assertEquals(r, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("not exist uuid");
    }

    @Test
    public void update() {
        Resume r = createResume(UUID_1, "Updated user");
        r.addContact(ContactType.MOBILE, "8-800-5555-555");
        storage.update(r);
        assertEquals(r, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(createResume("dummy", null));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> actualResumes = storage.getAllSorted();
        assertEquals(Arrays.asList(RESUME_2, RESUME_1, RESUME_3), actualResumes);
    }
}
