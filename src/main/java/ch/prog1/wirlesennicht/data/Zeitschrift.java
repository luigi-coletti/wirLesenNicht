package ch.prog1.wirlesennicht.data;

import java.time.LocalDate;

public class Zeitschrift extends Media{

    private final String editor;


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

    public LocalDate getPossibleLentDate(){
        LocalDate now =  LocalDate.now();
        return now.plusDays(5);
    }

    public boolean lend() {
        if(this.lentDate != null){
            lentDate = null;
            return true;
        }else {
            this.lentDate = LocalDate.now();
            return true;
        }
    }
}
