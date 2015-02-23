package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BIGVAL on 22/02/2015.
 */
@Entity
public class Preferences extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long idPreference;
    //la date du jour
    public String jour;
    //Variable permettant de choisir les créneaux d'une journée
    public Creneau cre;
    //Utilisateur quià créé le voeux
    @ManyToOne
    public Utilisateurs ref_utilisateurs;
    //Variable permettant de juger de l'état d'une préférences
    public Validation valide;
    public Preferences() {

    }
    public static List<Preferences> all() {
        return new ArrayList<Preferences>();
    }

    public static void create(Preferences p) {
    }

    public static void delete(Long id) {
    }

    public Preferences(Creneau cre, String jour, Utilisateurs ref_utilisateurs) {
        this.cre = cre;
        this.jour = jour;
        this.valide = Validation.ATTENTE;
        this.ref_utilisateurs = ref_utilisateurs;
    }

    public static Finder<Long,Preferences> find = new Finder<Long,Preferences>(
            Long.class, Preferences.class
    );

    public static Preferences create(Creneau cre, String jour, Long ref_utilisateurs) {
        Preferences pref = new Preferences(cre, jour, Utilisateurs.find.ref(ref_utilisateurs));
        Utilisateurs.find.ref(ref_utilisateurs).contraintes.add(pref);
        pref.save();
        return pref;
    }

    public static List<Preferences> findInvolving(Long utilisateurs) {
        return find.fetch("ref_Utilisateurs").where()
                .eq("ref_Utilisateurs.id_Utilisateurs", utilisateurs)
                .findList();
    }

}
