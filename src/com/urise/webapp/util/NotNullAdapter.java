package com.urise.webapp.util;

public class NotNullAdapter {

    public String ofString(String obj) {
        return obj == null ? "" : obj;
    }
}
