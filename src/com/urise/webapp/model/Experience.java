package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Experience {
    private final String name;
    private final String link;
    private final List<Position> positions;

    public Experience(String name, String link, List<Position> positions) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(positions, "positions must not be null");
        this.name = name;
        this.link = link;
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "\n" + name + " " + link + " \n" + positions.toString().replaceAll("[\\[\\]]","");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (!name.equals(that.name)) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + positions.hashCode();
        return result;
    }
}
