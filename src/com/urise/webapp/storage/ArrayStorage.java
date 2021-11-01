package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[3];
    private int size;
    private int currentIndex;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (!isPresent(r.getUuid(), "saved") && !isFull()) {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        return (isPresent(uuid, "gotten")) ? storage[currentIndex] : null;
    }

    public void delete(String uuid) {
        if (isPresent(uuid, "deleted")) {
            if (currentIndex + 1 < storage.length) {
                System.arraycopy(storage, currentIndex + 1, storage, currentIndex, size - 1);
            } else {
                storage[currentIndex] = null;
            }
            size--;
        }
    }

    public void update(Resume r) {
        if (isPresent(r.getUuid(), "updated")) {
            storage[currentIndex] = r;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isPresent(String uuid, String operation) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                currentIndex = i;
                System.out.println("The resume to be " + operation + " has already been added to current database");
                return true;
            } else {
                System.out.println("The resume to be " + operation + " is not found in current database");
            }
        }
        return false;
    }

    private boolean isFull() {
        if (size == storage.length) {
            System.out.println("ATTENTION: the database is full");
            return true;
        }
        return false;
    }
}
