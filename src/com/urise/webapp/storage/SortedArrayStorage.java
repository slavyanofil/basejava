package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, searchKey, BY_UUID_COMPARATOR);
    }

    @Override
    protected void paste(Object searchKey, Resume r) {
        int insertionPoint = -(Integer) searchKey - 1;
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = r;
    }

    private static final Comparator<Resume> BY_UUID_COMPARATOR = (Comparator.comparing(Resume::getUuid));
}
