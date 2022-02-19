package com.urise.webapp.model;

import java.util.List;

public class BulletedListSection extends AbstractSection {
    private final List<String> list;

    public BulletedListSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String strings : list) {
            sb.append("\n").append(strings);
        }
        return sb.toString();
    }
}
