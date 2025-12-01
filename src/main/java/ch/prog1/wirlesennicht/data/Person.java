package ch.prog1.wirlesennicht.data;

import java.util.HashMap;
import java.util.Map;

public class Person {
    Map<String, Media> medias = new HashMap<>();
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Map<String, Media> getMedias() {
        return medias;
    }

    public void addMedia(Media media) {

        medias.put(media.getId(), media);
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
