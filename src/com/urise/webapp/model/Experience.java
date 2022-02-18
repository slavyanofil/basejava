package com.urise.webapp.model;

import java.time.LocalDate;

public class Experience {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String name;
    private final String link;
    private final String text;

    public Experience(LocalDate startDate, LocalDate endDate, String name, String link, String text) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.link = link;
        this.text = text;
    }

    @Override
    public String toString() {
        return "\n" + name + " " + link + " \n" + startDate.getMonthValue() + "/" + startDate.getYear() + " - " + endDate.getMonthValue() + "/" +
                endDate.getYear() + " \n" + text;
    }
}
