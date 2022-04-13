package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {
    private final List<Experience> experiences;

    public Organization(List<Experience> experiences) {
        Objects.requireNonNull(experiences, "experiences must not be null");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        return experiences.equals(that.experiences);
    }

    @Override
    public int hashCode() {
        return experiences.hashCode();
    }
}
