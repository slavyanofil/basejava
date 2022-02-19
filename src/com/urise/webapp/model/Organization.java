package com.urise.webapp.model;

import java.util.List;

public class Organization extends AbstractSection {
    private final List<Experience> experiences;

    public Organization(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Experience experience : experiences) {
            sb.append("\n").append(experience.toString());
        }
        return sb.toString();
    }
}
