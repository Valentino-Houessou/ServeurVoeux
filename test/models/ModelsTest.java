package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.test.Helpers.*;

/**
 * Created by BIGVAL on 23/02/2015.
 */
public class ModelsTest extends WithApplication {

    @Test
    public void createUtilisateurs() {
        ArrayList<Profil> prof = new  ArrayList<Profil>();
        prof.add(Profil.ADMINISTRATEUR);
        prof.add(Profil.ENSEIGNANT);

        Utilisateurs userz = Utilisateurs.create("Jabea", "Andy",
                "bryan.jabea@gmail.com", "0615142697","Rue du Rhum 77 Combs La ville",
                "BryJab", Poste.PROFESSEUR, prof);
        userz.save();

        Utilisateurs user = Utilisateurs.find.where().eq("id_Utilisateur", new Long(1)).findUnique();
        assertNotNull(user);
        assertEquals("bryan.jabea@gmail.com", user.email);
        assertEquals(userz, Utilisateurs.find.ref(userz.id_utilisateur));
    }

    @Test
    public void createAndRetrievePreferences() throws ParseException {
        ArrayList<Profil> prof = new  ArrayList<Profil>();
        prof.add(Profil.ADMINISTRATEUR);
        prof.add(Profil.ENSEIGNANT);

        Utilisateurs userz = Utilisateurs.create("Dunnoyer", "Yoan",
                "yoan.dunnoyer@gmail.com", "0664622523","Rue de la JAbCorp 92 Boulogne",
                "YoaDun", Poste.MAITRE_DE_CONFERENCE, prof);
        userz.save();

        java.sql.Date jour = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd")).parse("2015-06-18").getTime());

        Preferences pp = Preferences.create(new Creneau(true,true,false,false),jour ,userz.id_utilisateur);
        pp.save();

        pp.save();
        Preferences p = Preferences.find.where().eq("idPreference", new Long(1)).findUnique();
        assertNotNull(p);
        assertEquals(userz.id_utilisateur, p.ref_utilisateurs.id_utilisateur);
        assertEquals(pp.idPreference, p.idPreference);
    }

    @Test
    public void findPreferencesInvolving() throws ParseException {
        ArrayList<Profil> prof = new  ArrayList<Profil>();
        prof.add(Profil.ADMINISTRATEUR);
        prof.add(Profil.ENSEIGNANT);

        Utilisateurs userz = new Utilisateurs("Kadri", "Saadi",
                "saadi.kadri@gmail.com", "0658512523","Rue de Bejaia 91 Bretigny",
                "SaaKad", Poste.DOCTORANT, prof);
        userz.save();
        java.sql.Date jour = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd")).parse("2015-06-17").getTime());
        java.sql.Date jour1 = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd")).parse("2015-06-18").getTime());

        Preferences pref1 = Preferences.create(new Creneau(true,true,false,false),jour ,userz.id_utilisateur);
        pref1.save();

        Preferences pref2 = Preferences.create(new Creneau(false,true,true,false),jour1,userz.id_utilisateur);
        pref2.save();

        assertNotNull(pref1);
        assertNotNull(pref2);

        List<Preferences> results = Preferences.findInvolving(userz.id_utilisateur);
        assertEquals(2, results.size());
    }

    @Test
    public void isMemberTest() throws ParseException {
        ArrayList<Profil> prof = new  ArrayList<Profil>();
        prof.add(Profil.ADMINISTRATEUR);
        prof.add(Profil.ENSEIGNANT);

        Utilisateurs userz = new Utilisateurs("Bonita", "Andy",
                "andy.bonita@gmail.com", "0645373892","Rue de Betclic 93 Saint Denis",
                "SaaKad", Poste.DOCTORANT, prof);
        userz.save();
        java.sql.Date jour = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd")).parse("2015-06-17").getTime());

        Preferences pref1 = Preferences.create(new Creneau(true,true,false,false),jour ,userz.id_utilisateur);
        pref1.save();

        assertNotNull(pref1);
        assertTrue(Preferences.isMember(pref1.idPreference,userz.id_utilisateur));
    }

}
