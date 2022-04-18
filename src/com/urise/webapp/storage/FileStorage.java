package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private SerializationStrategy serializationStrategy;

    protected FileStorage(File directory, SerializationStrategy serializationStrategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.serializationStrategy = serializationStrategy;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeable");
        }
        this.directory = directory;
    }

    @Override
    protected void insert(File file, Resume r) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        refresh(file, r);
    }

    @Override
    protected void remove(File file) {
        if (file.delete()) {
            System.out.println(file.getName() + " deleted");
        } else {
            throw new StorageException("Delete error", file.getName());
        }
    }

    @Override
    protected void refresh(File file, Resume r) {
        try {
            serializationStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> resumes = new ArrayList<>();
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                try {
                    resumes.add(serializationStrategy.doRead(new BufferedInputStream(new FileInputStream(file))));
                } catch (IOException e) {
                    throw new StorageException("IO error", file.getName(), e);
                }
            }
        }
        return resumes;
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return serializationStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                remove(file);
            }
        }
    }

    @Override
    public int size() {
        File[] list = directory.listFiles();
        if (list == null) {
            throw new StorageException("Get size error of storage", directory.getName());
        }
        return list.length;
    }
}
