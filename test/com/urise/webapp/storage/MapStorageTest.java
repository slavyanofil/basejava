package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    @Ignore
    public void saveOverflow() {
    }
}