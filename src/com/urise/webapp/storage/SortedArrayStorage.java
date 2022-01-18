package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insert(int index, Resume r) {
        if (!isFull()) {
            int insertionPoint = -index - 1;
            if (insertionPoint == size) {
                storage[size] = r;
            } else {
                System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
                storage[insertionPoint] = r;
            }
            size++;
        }
    }
}
