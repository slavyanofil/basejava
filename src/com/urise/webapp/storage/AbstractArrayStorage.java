package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;
    protected int currentIndex;

    public final void save(Resume r) {
        if (!isPresent(r.getUuid(), "saved") && !isFull()) {
            storage[size] = r;
            size++;
        }
        sort();
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void delete(String uuid) {
        if (isPresent(uuid, "deleted")) {
            if (currentIndex + 1 < storage.length) {
                System.arraycopy(storage, currentIndex + 1, storage, currentIndex, size - 1);
            } else {
                storage[currentIndex] = null;
            }
            size--;
        }
    }

    public final void update(Resume r) {
        if (isPresent(r.getUuid(), "updated")) {
            storage[currentIndex] = r;
        }
        sort();
    }

    protected abstract void sort();

    public final int size() {
        return size;
    }

    public final Resume get(String uuid) {
        return isPresent(uuid, "gotten") ? storage[currentIndex] : null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract boolean isPresent(String uuid, String message);

    protected final boolean isFull() {
        if (size == STORAGE_LIMIT) {
            System.out.println("ATTENTION: the database is full");
            return true;
        }
        return false;
    }
}
