package ch.prog1.wirlesennicht.data;

import java.time.LocalDate;

public class Film extends Media{

    private final String regisseur;

    public Film(String id, String title, String author, String description) {
        if(id.isEmpty() || title.isEmpty() || author.isEmpty() || description.isEmpty())
            throw new IllegalArgumentException();

        this.title = title;
        this.regisseur = author;
        this.description = description;
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCreator() {
        return this.regisseur;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public LocalDate getReturnDate() {
        return this.lentDate.plusWeeks(2);

    }

    public LocalDate getLentDate() {
        return this.lentDate;
    }

    public boolean lend() {
        LocalDate now =  LocalDate.now();
        if(this.lentDate != null && now.isBefore(this.getReturnDate()))
            return false;

        this.lentDate = now;
        return true;
    }
}
