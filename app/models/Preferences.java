package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Calendar;
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
    public java.sql.Date jour;
    //Variable permettant de choisir les créneaux d'une journée
    public Creneau cre;
    //Utilisateur quià créé le voeux
    @ManyToOne
    public Utilisateurs ref_utilisateurs;
    public java.sql.Date date;
    public int year;

    public static List<Preferences> all() {
        return new ArrayList<Preferences>();
    }

    public static void delete(Long id) {
    }

    public Preferences(Creneau cre, java.sql.Date jour, Utilisateurs ref_utilisateurs) {
        this.cre = cre;
        this.jour = jour;
        this.ref_utilisateurs = ref_utilisateurs;
        this.date = new java.sql.Date((new java.util.Date()).getTime());
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get( Calendar.YEAR );
    }

    public static Finder<Long,Preferences> find = new Finder<Long,Preferences>(
            Long.class, Preferences.class
    );

    public static Preferences create(Creneau cre, java.sql.Date jour, Long ref_ut) {
        Preferences pref = new Preferences(cre, jour, Utilisateurs.find.ref(ref_ut));
        pref.save();
        return pref;
    }

    public static List<Preferences> findInvolving(Long ut) {
        Calendar c = Calendar.getInstance();
        int tYear = c.get(Calendar.YEAR);
        return find.fetch("ref_utilisateurs").where()
                .eq("year",tYear)
                .eq("ref_utilisateurs.id_utilisateur", ut)
                .findList();
    }

    public static boolean isMember(Long preference, long user) {
        return find.where()
                .eq("ref_utilisateurs.id_utilisateur", user)
                .eq("idPreference", preference)
                .findRowCount() > 0;
    }

}
