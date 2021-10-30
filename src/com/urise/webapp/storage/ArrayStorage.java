package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

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
        if (isPresent(uuid, "gotten")) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (isPresent(uuid, "deleted")) {
            int currentIndex = -1;
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    currentIndex = i;
                }
            }
            System.arraycopy(storage, size - currentIndex, storage, currentIndex, size - currentIndex);
            size--;
        }
    }

    public void update(Resume r) {
        if (isPresent(r.getUuid(), "updated")) {
            for (int i = 0; i < size; i++) {
                if (storage[i] == r) {
                    storage[i] = r;
                }
            }
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

    public boolean isPresent(String uuid, String operation) {
        boolean check = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("The resume to be " + operation + " is not found in current database");
        } else {
            System.out.println("The resume to be " + operation + " has already been added to current database");
        }
        return check;
    }

    private boolean isFull() {
        boolean check = false;
        if (size == storage.length) {
            check = true;
            System.out.println("ATTENTION: the database is full");
        }
        return check;
    }
}
