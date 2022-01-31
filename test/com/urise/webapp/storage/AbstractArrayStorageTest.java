package com.urise.webapp.storage;

import com.urise.webapp.exception.OverflowException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = OverflowException.class)
    public void saveOverflow() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("User " + i));
            }
        } catch (Exception e) {
            Assert.fail("Overflow is ahead of time");
        }
        storage.save(new Resume("Extra user"));
    }
}
