package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
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
    public Date date;

    public static List<Preferences> all() {
        return new ArrayList<Preferences>();
    }

    public static void delete(Long id) {
    }

    public Preferences(Creneau cre, String jour, Utilisateurs ref_utilisateurs) {
        this.cre = cre;
        this.jour = jour;
        this.valide = Validation.ATTENTE;
        this.ref_utilisateurs = ref_utilisateurs;
        this.date = new java.util.Date();
    }

    public static Finder<Long,Preferences> find = new Finder<Long,Preferences>(
            Long.class, Preferences.class
    );

    public static Preferences create(Creneau cre, String jour, Long ref_ut) {
        Preferences pref = new Preferences(cre, jour, Utilisateurs.find.ref(ref_ut));
        pref.save();
        return pref;
    }

    public static List<Preferences> findInvolving(Long ut) {
        return find.fetch("ref_utilisateurs").where()
                .eq("ref_utilisateurs.id_utilisateur", ut)
                .findList();
    }

}
