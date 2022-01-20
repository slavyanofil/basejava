package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    public final void clear() {
        storage.clear();
        System.out.println("Database is empty");
    }

    @Override
    protected void insert(int index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void remove(int index) {
        storage.remove(index);
    }

    @Override
    protected void refresh(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    public final Resume[] getAll() {
        return storage.toArray(new Resume[size()]);
    }

    @Override
    public final int size() {
        return storage.size();
    }
}
