package com.urise.webapp.storage;

import com.urise.webapp.exception.OverflowException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected boolean isFull() {
        if (size == STORAGE_LIMIT) {
            throw new OverflowException("Database is full");
        }
        return false;
    }

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Database is empty");
    }

    @Override
    protected void remove(int index) {
        if (index + 1 < STORAGE_LIMIT) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        } else {
            storage[index] = null;
        }
    }

    @Override
    protected void refresh(int index, Resume r) {
        storage[index] = r;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    @Override
    protected Resume[] getAllResumes() {
        return Arrays.copyOf(storage, size);
    }
}
