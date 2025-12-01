package ch.prog1.wirlesennicht.data;

import java.util.Date;

public interface Media {
    public String getTitle();
    public String getCreator();
    public String getDescription();
    public String getId();
    public Date getReturnDate();
    public Date getLentDate();
    public boolean lend();
}
