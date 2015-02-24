package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import java.util.List;

/**
 * Created by BIGVAL on 22/02/2015.
 */
@Entity
public class Voeux extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id_voeux;
    public String libelle_voeux;
    public String commentaire;
    public EC ref_ec;
    public Double volume_horaire_cours_assure;
    public boolean priorite;
    public Double volume_horaire_TD_assure;
    @ManyToOne
    public Utilisateurs ref_utilisateurs;
    public Validation valide;

    public Voeux(String libelle_voeux, String commentaire, EC ref_ec,
                 Double volume_horaire_cours_assure, boolean priorite,
                 Double volume_horaire_TD_assure,Utilisateurs ref_utilisateurs ) {
        this.libelle_voeux = libelle_voeux;
        this.commentaire = commentaire;
        this.ref_ec = ref_ec;
        this.volume_horaire_cours_assure = volume_horaire_cours_assure;
        this.priorite = priorite;
        this.volume_horaire_TD_assure = volume_horaire_TD_assure;
        this.valide = Validation.ATTENTE;
        this.ref_utilisateurs = ref_utilisateurs;
    }

    public static Finder<Long,Voeux> find = new Finder<Long,Voeux>(
            Long.class, Voeux.class
    );

    public static Voeux create(String libelle_voeux, String commentaire, String ref_ec,
                               Double volume_horaire_cours_assure, boolean priorite,
                               Double volume_horaire_TD_assure,Long ref_utilisateurs ) {
        Voeux voeux = new Voeux(libelle_voeux, commentaire, EC.find.ref(ref_ec),
                                volume_horaire_cours_assure, priorite,volume_horaire_TD_assure,
                                Utilisateurs.find.ref(ref_utilisateurs));
        voeux.save();
        //Utilisateurs.find.ref(ref_utilisateurs).list_voeux.add(voeux);
        return voeux;
    }

    public static List<Voeux> findTodoInvolving(Long utilisateurs) {
        return find.fetch("ref_Utilisateurs").where()
                .eq("ref_Utilisateurs.id_Utilisateurs", utilisateurs)
                .findList();
    }

}
