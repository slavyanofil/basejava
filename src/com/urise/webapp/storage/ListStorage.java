package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isFull() {
        return false;
    }

    @Override
    protected int getIndex(String uuid) {
        for (Resume r : storage) {
            if (r.toString().equals(uuid)) {
                return storage.indexOf(r);
            }
        }
        return -1;
    }

    @Override
    public final void clear() {
        storage.clear();
        size = 0;
        System.out.println("Database is empty");
    }

    @Override
    protected void insert(int index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void remove(int index) {
        storage.remove(storage.get(index));
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
    protected Resume[] getAllResumes() {
        return storage.toArray(new Resume[size]);
    }
}
