package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    @Override
    public void empty() {
        Arrays.fill(storage, 0, size, null);
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
