package ch.prog1.wirlesennicht.data;

import java.time.LocalDate;

public abstract class Media {

    protected String id;
    protected String title;
    protected String description;
    protected LocalDate lentDate = null;


    public abstract String getTitle();

    public abstract String getCreator();

    public abstract String getDescription();

    public abstract String getId();

    public abstract LocalDate getReturnDate();

    public abstract LocalDate getLentDate();

    public abstract boolean lend();
}
