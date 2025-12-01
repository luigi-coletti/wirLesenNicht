package ch.prog1.wirlesennicht.data;

import java.util.Date;

public class Buch implements Media{
    private String title;
    private String author;
    private String description;
    private String isbn;
    private Date lentDate = null;

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

    public Date getReturnDate() {
        return null;
    }

    public Date getLentDate() {
        return null;
    }

    public boolean lend() {
        Date now = new Date();
        if(this.lentDate != null && this.lentDate.after(now))
            return false;


        return false;
    }

}
