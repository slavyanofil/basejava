package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public final void clear() {
        storage.clear();
        System.out.println("Database is empty");
    }

    @Override
    protected void insert(Object searchKey, Resume r) {
        storage.add(r);
    }

    @Override
    protected void remove(Object searchKey) {
        storage.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected void refresh(Object searchKey, Resume r) {
        storage.set((Integer) searchKey, r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected final List<Resume> getList() {
        return new ArrayList<>(storage);
    }

    @Override
    public final int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
