package ch.prog1.wirlesennicht.data;

import java.time.LocalDate;

public class Zeitschrift implements Media{
    private final String title;
    private final String editor;
    private final String description;
    private final String id;
    private LocalDate lentDate = null;

    public Zeitschrift(String id, String title, String editor, String description) {
        if(id.isEmpty() || title.isEmpty() || editor.isEmpty() || description.isEmpty())
            throw new IllegalArgumentException();

        this.title = title;
        this.editor = editor;
        this.description = description;
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCreator() {
        return this.editor;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public LocalDate getReturnDate() {
        return this.lentDate.plusDays(5);
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
