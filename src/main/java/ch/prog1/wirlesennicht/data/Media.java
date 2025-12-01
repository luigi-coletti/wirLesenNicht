package ch.prog1.wirlesennicht.data;

import java.time.LocalDate;

public interface Media {
    public String getTitle();
    public String getCreator();
    public String getDescription();
    public String getId();
    public LocalDate getReturnDate();
    public LocalDate getLentDate();
    public boolean lend();
}
