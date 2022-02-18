package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
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
    protected void insert(Integer searchKey, Resume r) {
        storage.add(r);
    }

    @Override
    protected void remove(Integer searchKey) {
        storage.remove((searchKey).intValue());
    }

    @Override
    protected void refresh(Integer searchKey, Resume r) {
        storage.set(searchKey, r);
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return storage.get(searchKey);
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
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }
}
