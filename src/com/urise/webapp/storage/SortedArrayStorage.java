package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insert(Resume r) {
        int insertionPoint = -Arrays.binarySearch(storage, 0, size, r) - 1;
        if (insertionPoint < 0 || insertionPoint + 2 == STORAGE_LIMIT) {
            storage[size] = r;
        } else {
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size + 1 - insertionPoint);
            storage[insertionPoint] = r;
        }
    }
}
