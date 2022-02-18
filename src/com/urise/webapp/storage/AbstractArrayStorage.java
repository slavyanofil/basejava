package com.urise.webapp.storage;

import com.urise.webapp.exception.OverflowException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    protected void insert(Integer searchKey, Resume r) {
        if (!isFull()) {
            paste(searchKey, r);
            size++;
        }
    }

    protected abstract void paste(Integer index, Resume r);

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
    protected void remove(Integer searchKey) {
        if (searchKey + 1 < STORAGE_LIMIT) {
            System.arraycopy(storage, searchKey + 1, storage, searchKey, size - 1 - searchKey);
        } else {
            storage[searchKey] = null;
        }
        size--;
    }

    @Override
    protected void refresh(Integer searchKey, Resume r) {
        storage[searchKey] = r;
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return storage[searchKey];
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
    protected boolean isExist(Integer index) {
        return index >= 0;
    }
}
