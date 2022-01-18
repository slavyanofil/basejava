package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        insert(index, r);
        System.out.println("Resume " + r.getUuid() + " has been saved");
    }

    protected abstract void insert(int index, Resume r);

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        remove(index);
    }

    protected abstract void remove(int index);

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        refresh(index, r);
    }

    protected abstract void refresh(int index, Resume r);

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    protected abstract Resume getResume(int index);

    protected abstract int getIndex(String uuid);
}
