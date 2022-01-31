package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;

import static java.util.Comparator.comparing;

public abstract class AbstractStorage implements Storage {

    public final void save(Resume r) {
        insert(getNotExistIndex(r.getUuid()), r);
        System.out.println("Resume " + r.getUuid() + " has been saved");
    }

    protected abstract void insert(Object searchKey, Resume r);

    public final void delete(String uuid) {
        remove(getExistSearchKey(uuid));
    }

    protected abstract void remove(Object searchKey);

    public final void update(Resume r) {
        refresh(getExistSearchKey(r.getUuid()), r);
    }

    protected abstract void refresh(Object searchKey, Resume r);

    public final Resume get(String uuid) {
        return getResume(getExistSearchKey(uuid));
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = getList();
        list.sort(comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return list;
    }

    protected abstract List<Resume> getList();

    protected abstract Resume getResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistIndex(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);
}
