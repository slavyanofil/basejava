package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializationStrategy serializationStrategy;

    protected PathStorage(String dir, SerializationStrategy serializationStrategy) {

        Objects.requireNonNull(dir, "directory must not be null");
        this.serializationStrategy = serializationStrategy;
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory + " is not readable/writeable");
        }
    }

    @Override
    protected void insert(Path path, Resume r) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file", getFileName(path), e);
        }
        refresh(path, r);
    }

    @Override
    protected void remove(Path path) {
        try {
            if (!Files.deleteIfExists(path))
                System.out.println("Delete error");
        } catch (IOException e) {
            throw new StorageException("Delete error", getFileName(path), e);
        }
    }

    @Override
    protected void refresh(Path path, Resume r) {
        try {
            serializationStrategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Couldn't write file", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> getList() {
        return getFilesList().map(this::getResume).collect(Collectors.toList());
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return serializationStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Couldn't get file", getFileName(path), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::remove);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path operate error", getFileName(directory), e);
        }
    }
}
