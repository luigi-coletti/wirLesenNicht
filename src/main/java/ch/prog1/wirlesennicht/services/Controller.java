package ch.prog1.wirlesennicht.services;

import ch.prog1.wirlesennicht.data.*;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@org.springframework.stereotype.Controller
public class Controller {
    private final HashMap<String, Media> allMedia;
    private final List<Person> users;

    public Controller() {
        allMedia = new HashMap<>();
        users = new ArrayList<>();
        users.add(new Person("Peter", "Hans", "peter.hans@students.fhnw.ch"));
        users.add(new Person("Max", "Mustermann", "max.mustermann@studnets.fhnw.ch"));
        users.add(new Person("Dieter", "Fischer", "dieter.fischer@students.fhnw.ch"));

        try (InputStream is = getClass().getResourceAsStream("/books.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0];
                    String title = parts[1];
                    String description = parts[2];
                    String author = parts[3];
                    Buch book = new Buch(id, title, author, description);
                    allMedia.put(id, book);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        try (InputStream is = getClass().getResourceAsStream("/dvds.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0];
                    String title = parts[1];
                    String description = parts[2];
                    String director = parts[3];
                    Film dvd = new Film(id, title, director, description);
                    allMedia.put(id, dvd);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        try (InputStream is = getClass().getResourceAsStream("/magazines.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0];
                    String title = parts[1];
                    String description = parts[2];
                    String editor = parts[3];
                    Zeitschrift magazine = new Zeitschrift(id, title, editor, description);
                    allMedia.put(id, magazine);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Media> getAllMedias(){
        return new ArrayList<>(allMedia.values());
    }

    public Media getMediaById(String id){
        return allMedia.get(id);
    }

    public void lendMedia(Media media, String fname, String lname){
        Person foundPerson = null;
        for(Person user : users){
            if(user.getFirstName().equals(fname) && user.getLastName().equals(lname)){
                foundPerson = user;
                break;
            }
        }
        if(foundPerson != null){
            media.lend();
            foundPerson.addMedia(media);
        }
    }

    public List<Media> getMediasByPerson(String fname, String lname){
        Person foundPerson = null;
        for(Person user : users){
            if(user.getFirstName().equals(fname) && user.getLastName().equals(lname)){
                foundPerson = user;
                break;
            }
        }
        if(foundPerson != null){
            return new ArrayList<>(foundPerson.getMedias().values());
        }
        return null;
    }

    public List<Person> getUsers(){
        return new ArrayList<>(users);
    }

    public void returnMedia(Media media){
        Media actualMedia = allMedia.get(media.getId());
        if(actualMedia != null){
            actualMedia.lend();
        } else {
            return;
        }
        for(Person u : users){
            u.getMedias().values().removeIf(m -> m.getId() == media.getId());
        }
    }
}
