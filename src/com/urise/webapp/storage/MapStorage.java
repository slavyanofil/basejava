package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapStorage extends AbstractStorage {
    private Map<Integer, Resume> storage = new HashMap<>();

    @Override
    protected void insert(int index, Resume r) {
        storage.put(size(), r);
    }

    @Override
    protected void remove(int index) {
        storage.remove(index);
    }

    @Override
    protected void refresh(int index, Resume r) {
        storage.replace(index, r);
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected int getIndex(String uuid) {
        for (Map.Entry<Integer, Resume> entry : storage.entrySet()) {
            if (Objects.equals(entry.getValue(), new Resume(uuid))) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
