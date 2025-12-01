package ch.prog1.wirlesennicht.data;

import java.util.Date;

public class Buch implements Media{
    private String title;
    private String author;
    private String description;
    private String isbn;
    private boolean lent;

    public Buch(String title, String author, String description, String id) {

    }

    public String getTitle() {
        return "";
    }

    public String getCreator() {
        return "";
    }

    public String getDescription() {
        return "";
    }

    public String getId() {
        return "";
    }

    public Date getReturnDate() {
        return null;
    }

    public boolean isLent() {
        return false;
    }

    public boolean setLent() {
        return false;
    }
}
