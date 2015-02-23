package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;

import java.util.ArrayList;

import static play.test.Helpers.*;

/**
 * Created by BIGVAL on 23/02/2015.
 */
public class ModelsTest extends WithApplication {

    @Test
    public void createAndRetrievePreferences() {
        ArrayList<Profil> prof = new  ArrayList<Profil>();
        prof.add(Profil.ADMINISTRATEUR);
        prof.add(Profil.ENSEIGNANT);

        Utilisateurs userz = new Utilisateurs("Jabea", "Andy",
                "bryan.jabea@gmail.com", "0664622523","Rue de la JAbCorp 92 Boulogne",
                "BryJab", Poste.PROFESSEUR, prof);
        userz.save();

        new Preferences(new  Creneau(), "Lundi",userz).save();
        Preferences p = Preferences.find.where().eq("idPreference", new Long(1)).findUnique();
        assertNotNull(p);
        assertEquals("Lundi", p.jour);
    }

    @Test
    public void findPreferencesInvolving() {
        /*User bob = new User("bob@gmail.com", "Bob", "secret");
        bob.save();

        Project project = Project.create("Play 2", "play", "bob@gmail.com");
        Task t1 = new Task();
        t1.title = "Write tutorial";
        t1.assignedTo = bob;
        t1.done = true;
        t1.save();

        Task t2 = new Task();
        t2.title = "Release next version";
        t2.project = project;
        t2.save();

        List<Task> results = Task.findTodoInvolving("bob@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Release next version", results.get(0).title);*/
    }

}
