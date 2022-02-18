package com.urise.webapp.model;

import java.util.List;
import java.util.StringJoiner;

public class ButtonSection extends Section {
    private final List<String> list;

    public ButtonSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n" + "\u0387 ");
        list.forEach(joiner::add);
        return "\n\u0387 " + joiner.toString().replaceAll("[\\[\\]]", "");
    }
}
