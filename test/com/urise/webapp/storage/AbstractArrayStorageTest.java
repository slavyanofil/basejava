package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.OverflowException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1 = new Resume("uuid1");
    private static final Resume RESUME_2 = new Resume("uuid2");
    private static final Resume RESUME_3 = new Resume("uuid3");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void save() {
        Resume r = new Resume(UUID_4);
        storage.save(r);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(r, storage.get(UUID_4));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
        Assert.assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resumes[0], storage.get(UUID_1));
        Assert.assertEquals(resumes[1], storage.get(UUID_2));
        Assert.assertEquals(resumes[2], storage.get(UUID_3));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = OverflowException.class)
    public void saveOverflow() {
        for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++)
            storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void NotExistUpdate() {
        storage.update(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void NotExistDelete() {
        storage.delete(new Resume().getUuid());
    }
}
