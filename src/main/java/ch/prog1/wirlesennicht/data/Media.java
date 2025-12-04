package ch.prog1.wirlesennicht.data;

import java.time.LocalDate;

public abstract class Media {

    // Die ID des Mediums. Bei Büchern bsp. ISBN
    protected String id;
    // Der Titel des Mediums
    protected String title;
    // Eine Beschreibung des Mediums
    protected String description;
    // Das Datum an welchem das Medium ausgeliehen wurde. Default = null, da nichts ausgeliehen
    protected LocalDate lentDate = null;


    /**
     * Liefert Titel des Mediums als String zurück.
     *
     * @return String des Titels
     */
    public abstract String getTitle();

    /**
     * Liefert Ersteller des Mediums als String zurück. Author bei Buch,
     * Journalist bei Magazin und Regisseur bei Film
     *
     * @return String des Erstellers
     */
    public abstract String getCreator();

    /**
     * Liefert Beschreibung des Mediums als String zurück.
     *
     * @return String der Beschreibung
     */
    public abstract String getDescription();

    /**
     * Liefert den ID des Mediums als String zurück.
     * Bsp. ISBN bei Buch.
     *
     * @return String der ID
     */
    public abstract String getId();

    /**
     * Liefert zurück, bis wann das Medium ausgeliehen wurde.
     *
     * @return LocalDate des Rückgabedatums
     */
    public abstract LocalDate getReturnDate();

    /**
     * Liefert zurück, wann das Medium ausgeliehen wurde.
     *
     * @return LocalDate des Ausleihedatums
     */
    public abstract LocalDate getLentDate();

    /**
     * Liefert zurück, bis wann das Medium ausgeliehen werden könnte.
     *
     * @return LocalDate des möglichen Rückgabedatums
     */
    public abstract LocalDate getPossibleLentDate();

    /**
     * Leiht ein Medium aus, setzt also lentDate auf das standardmässige Rückgabedatum.
     *
     * @return boolean true wenn Ausleihe erfolgreich, false wenn Ausleihe abgelehnt.
     */
    public abstract boolean lend();
}
