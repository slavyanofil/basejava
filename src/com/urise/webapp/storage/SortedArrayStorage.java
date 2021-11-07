package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        super.save(r);
        sort();
    }

    @Override
    public void update(Resume r) {
        super.update(r);
        sort();
    }

    @Override
    protected boolean isPresent(String uuid, String operation) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        currentIndex = Arrays.binarySearch(storage, 0, size, searchKey);
        if (currentIndex >= 0) {
            System.out.println("The resume to be " + operation + " has already been added to current database");
            return true;
        }
        System.out.println("The resume to be " + operation + " is not found in current database");
        return false;
    }

    private void sort() {
        for (int i = 1; i < size; i++) {
            Resume currentResume = storage[i];
            int index = Arrays.binarySearch(storage, 0, i, currentResume);
            if (index < 0) {
                index = -index - 1;
            }
            System.arraycopy(storage, index, storage, index + 1, i - index);
            storage[index] = currentResume;
        }
    }
}
