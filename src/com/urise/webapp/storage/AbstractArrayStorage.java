package com.urise.webapp.storage;

import com.urise.webapp.exception.OverflowException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    protected void insert(Object searchKey, Resume r) {
        if (!isFull()) {
            paste(searchKey, r);
            size++;
        }
    }

    protected abstract void paste(Object index, Resume r);

    private boolean isFull() {
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
    protected void remove(Object searchKey) {
        if ((Integer)searchKey + 1 < STORAGE_LIMIT) {
            System.arraycopy(storage, (Integer)searchKey + 1, storage, (Integer)searchKey, size - 1 - (Integer)searchKey);
        } else {
            storage[(Integer)searchKey] = null;
        }
        size--;
    }

    @Override
        protected void refresh(Object searchKey, Resume r) {
        storage[(Integer)searchKey] = r;
    }

    @Override
        protected Resume getResume(Object searchKey) {
        return storage[(Integer)searchKey];
    }

    @Override
    protected final List<Resume> getList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
