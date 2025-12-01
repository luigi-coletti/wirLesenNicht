package ch.prog1.wirlesennicht.services;

import ch.prog1.wirlesennicht.data.Person;

public class Controller {
    public Controller() {
        //TODO: load sample media and create objects
        Person Peter = new Person("Peter", "Hans", "peter.hans@students.fhnw.ch");
        Person Max = new Person("Max", "Mustermann", "max.mustermann@studnets.fhnw.ch");
        Person Dieter = new Person("Dieter", "Fischer", "dieter.fischer@students.fhnw.ch");

    }
}
