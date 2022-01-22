package com.urise.webapp.storage;

import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    public void saveOverflow() {
        System.out.println("Overflow is impossible");
    }
}