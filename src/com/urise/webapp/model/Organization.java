package com.urise.webapp.model;

import java.util.List;
import java.util.StringJoiner;

public class Organization extends Section {
    private final List<Experience> experiences;

    public Organization(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n" + "");
        experiences.forEach(item -> joiner.add(item.toString()));
        return joiner.toString().replaceAll("[\\[\\]]", "").replaceAll("\n,", "\n");
    }
}
