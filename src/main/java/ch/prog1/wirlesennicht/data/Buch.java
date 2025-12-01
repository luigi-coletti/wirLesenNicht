package ch.prog1.wirlesennicht.data;

import java.time.LocalDate;

public class Buch implements Media{
    private final String title;
    private final String author;
    private final String description;
    private final String isbn;
    private LocalDate lentDate = null;

    public Buch(String id, String title, String author, String description) {
        if(id.isEmpty() || title.isEmpty() || author.isEmpty() || description.isEmpty())
            throw new IllegalArgumentException();

        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = id;
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
        return this.isbn;
    }

    public LocalDate getReturnDate() {
        return this.lentDate.plusMonths(1);
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
