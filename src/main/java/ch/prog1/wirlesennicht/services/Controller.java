package ch.prog1.wirlesennicht.services;

import ch.prog1.wirlesennicht.data.Buch;
import ch.prog1.wirlesennicht.data.Person;

import ch.prog1.wirlesennicht.data.Media;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    public Controller() {
        //TODO: load sample media and create objects
        Person Peter = new Person("Peter", "Hans", "peter.hans@students.fhnw.ch");
        Person Max = new Person("Max", "Mustermann", "max.mustermann@studnets.fhnw.ch");
        Person Dieter = new Person("Dieter", "Fischer", "dieter.fischer@students.fhnw.ch");

    }

    public List<Media> getAllMedias(){
        List<Media> medias = new ArrayList<>();
        Buch buch = new Buch("1234","Best book", "Iven", "Kleines Beispiel");
        medias.add(buch);
        return medias;
    }

    public Media getMediaById(String id){
        return new Buch("1234","Best book", "Iven", "Kleines Beispiel");
    }

    public void lendMedia(Media media, String fname, String lname){

    }

    public List<Media> getMediasByPerson(String fname, String lname){
        List<Media> medias = new ArrayList<>();
        Buch buch = new Buch("1234","Best book", "Iven", "Kleines Beispiel");
        buch.lend();
        medias.add(buch);
        return medias;
    }
    public List<Person> getUsers(){
        ArrayList<Person> users = new ArrayList<>();

        Person Peter = new Person("Peter", "Hans", "peter.hans@students.fhnw.ch");
        Person Max = new Person("Max", "Mustermann", "max.mustermann@studnets.fhnw.ch");
        Person Dieter = new Person("Dieter", "Fischer", "dieter.fischer@students.fhnw.ch");

        users.add(Peter);
        users.add(Max);
        users.add(Dieter);
        return users;

    }

    public void returnMedia(Media media){
        //TODO bitte do s media in de liste vo media in de person au entferne
    }
}
