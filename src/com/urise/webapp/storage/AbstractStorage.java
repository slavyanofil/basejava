package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.OverflowException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public final void save(Resume r) {
        if (!isFull()) {
            int index = getIndex(r.getUuid());
            if (index >= 0) {
                throw new ExistStorageException(r.getUuid());
            }
            insert(index, r);
            size++;
            System.out.println("Resume " + r.getUuid() + " has been saved");
        }
    }

    private boolean isFull() {
        if (size == STORAGE_LIMIT) {
            throw new OverflowException("Database is full");
        }
        return false;
    }

    public final void clear() {
        empty();
        size = 0;
        System.out.println("Database is empty");
    }

    protected abstract void empty();

    protected abstract void insert(int index, Resume r);

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        remove(index);
        size--;
    }

    protected abstract void remove(int index);

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        refresh(index, r);
    }

    protected abstract void refresh(int index, Resume r);

    public final int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    protected abstract Resume getResume(int index);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public final Resume[] getAll() {
        return getAllResumes();
    }

    protected abstract Resume[] getAllResumes();

    protected abstract int getIndex(String uuid);
}
