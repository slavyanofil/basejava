package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void insert(Object resume, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void remove(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }

    @Override
    protected void refresh(Object resume, Resume r) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected final List<Resume> getList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }
}
