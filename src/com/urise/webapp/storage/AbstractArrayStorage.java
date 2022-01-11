package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.OverflowException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
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

    protected abstract void insert(int index, Resume r);

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        if (index + 1 < STORAGE_LIMIT) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        } else {
            storage[index] = null;
        }
        size--;
    }

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage[index] = r;
    }

    public final int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);
}
