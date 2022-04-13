package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String text;

    public Position(LocalDate startDate, LocalDate endDate, String text) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(text, "text must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.text = text;
    }

    @Override
    public String toString() {
        return "\n" + startDate.getMonthValue() + "/" + startDate.getYear() + " - " + endDate.getMonthValue() + "/" +
                endDate.getYear() + " \n" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (!startDate.equals(position.startDate)) return false;
        if (!endDate.equals(position.endDate)) return false;
        return text.equals(position.text);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
