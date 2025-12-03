package ch.prog1.wirlesennicht.data;

import java.time.LocalDate;

public class Buch extends Media {

    private final String author;

    public Buch(String id, String title, String author, String description) {
        if(id.isEmpty() || title.isEmpty() || author.isEmpty() || description.isEmpty())
            throw new IllegalArgumentException();

        this.title = title;
        this.author = author;
        this.description = description;
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCreator() {
        return this.author;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public LocalDate getReturnDate() {
        return this.lentDate == null ? null : this.lentDate.plusMonths(1);
    }

    public LocalDate getLentDate() {
        return this.lentDate;
    }

    public LocalDate getPossibleLentDate(){
        LocalDate now =  LocalDate.now();
        return now.plusMonths(1);
    }

    public boolean lend() {
        LocalDate now =  LocalDate.now();
        if(this.lentDate != null && now.isBefore(this.getReturnDate()))
            return false;

        this.lentDate = now;
        return true;
    }

}
