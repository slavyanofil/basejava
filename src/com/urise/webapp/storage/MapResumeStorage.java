package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void insert(Resume resume, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void remove(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    protected void refresh(Resume resume, Resume r) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Resume resume) {
        return resume;
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
    protected boolean isExist(Resume resume) {
        return resume != null;
    }
}
