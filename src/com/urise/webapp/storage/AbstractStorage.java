package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void save(Resume r) {
        insert(getNotExistIndex(r.getUuid()), r);
        System.out.println("Resume " + r.getUuid() + " has been saved");
    }

    protected abstract void insert(int index, Resume r);

    public final void delete(String uuid) {
        remove(getExistIndex(uuid));
    }

    protected abstract void remove(int index);

    public final void update(Resume r) {
        refresh(getExistIndex(r.getUuid()), r);
    }

    protected abstract void refresh(int index, Resume r);

    public final Resume get(String uuid) {
        return getResume(getExistIndex(uuid));
    }

    protected abstract Resume getResume(int index);

    protected abstract int getIndex(String uuid);

    private int getExistIndex(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private int getNotExistIndex(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
