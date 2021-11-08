package com.urise.webapp.storage;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected boolean isPresent(String uuid, String operation) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                currentIndex = i;
                System.out.println("The resume to be " + operation + " has already been added to current database");
                return true;
            }
        }
        System.out.println("The resume to be " + operation + " is not found in current database");
        return false;
    }

    @Override
    protected void sort() {
    }
}
