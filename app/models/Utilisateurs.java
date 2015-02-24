package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BIGVAL on 22/02/2015.
 */
@Entity
public class Utilisateurs extends Model {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id_utilisateur;
    public String nom;
    public String prenom;
    public String email;
    public String telephone;
    public String adresse;
    public String mdp;
    public Poste poste;
    @ManyToMany(cascade = CascadeType.REMOVE)
    public ArrayList<Profil> profil;
    public ArrayList<Voeux> list_voeux;
    //On pourra représenter la liste des préférence sous forme d'un calendrier
    public ArrayList<Preferences> contraintes;

    public Utilisateurs(String nom, String prenom,
                        String email, String telephone,String adresse,
                        String mdp, Poste poste, ArrayList<Profil> profil) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.mdp = mdp;
        this.poste = poste;
        this.profil = profil;
        this.list_voeux = new ArrayList<Voeux>();
        this.contraintes = new ArrayList<Preferences>();
    }

    public static Finder<Long,Utilisateurs> find = new Finder<Long,Utilisateurs>(
            Long.class, Utilisateurs.class
    );

    public static Utilisateurs create(String nom, String prenom,
                                      String email, String telephone,String adresse,
                                      String mdp, Poste poste, ArrayList<Profil> profil) {
        Utilisateurs user = new Utilisateurs(nom, prenom, email, telephone, adresse,
                                               mdp, poste, profil);
        user.save();
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateurs)) return false;
        if (!super.equals(o)) return false;

        Utilisateurs that = (Utilisateurs) o;

        if (id_utilisateur != that.id_utilisateur) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id_utilisateur ^ (id_utilisateur >>> 32));
        return result;
    }
}
