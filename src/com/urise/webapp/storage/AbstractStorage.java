package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public final void save(Resume r) {
        LOG.info("Save " + r);
        insert(getNotExistSearchKey(r.getUuid()), r);
        System.out.println("Resume " + r.getUuid() + " has been saved");
    }

    protected abstract void insert(SK searchKey, Resume r);

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        remove(getExistSearchKey(uuid));
    }

    protected abstract void remove(SK searchKey);

    public final void update(Resume r) {
        LOG.info("Update " + r);
        refresh(getExistSearchKey(r.getUuid()), r);
    }

    protected abstract void refresh(SK searchKey, Resume r);

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getResume(getExistSearchKey(uuid));
    }

    public final List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> list = getList();
        list.sort(Comparator.naturalOrder());
        return list;
    }

    protected abstract List<Resume> getList();

    protected abstract Resume getResume(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " doesn't exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " exists");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(SK searchKey);
}
