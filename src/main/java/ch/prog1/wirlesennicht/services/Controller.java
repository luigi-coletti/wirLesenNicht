package ch.prog1.wirlesennicht.services;

import ch.prog1.wirlesennicht.data.Person;

import ch.prog1.wirlesennicht.data.Media;
import ch.prog1.wirlesennicht.data.Person;

import java.util.List;

public class Controller {
    public Controller() {
        //TODO: load sample media and create objects
        Person Peter = new Person("Peter", "Hans", "peter.hans@students.fhnw.ch");
        Person Max = new Person("Max", "Mustermann", "max.mustermann@studnets.fhnw.ch");
        Person Dieter = new Person("Dieter", "Fischer", "dieter.fischer@students.fhnw.ch");

    }

    public List<Media> getAllMedias(){
        return null;
    }

    public Media getMediaById(int id){
        return null;
    }

    public void lendMedia(Media media){

    }
    public List<Media> getMediasByPerson(String name){
        return null;
    }
    public List<Person> getUsers(){
        return null;
    }

    public void returnMedia(Media media){

    }
}
