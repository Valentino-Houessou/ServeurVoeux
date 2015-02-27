package models;

import play.db.ebean.Model;

/**
 * Created by BIGVAL on 22/02/2015.
 */
public class Creneau extends Model {


    //créneau à 1 créneau occupé, créneau à 2 créneau libre
    public boolean creneau_1;

    public Creneau(boolean creneau_1, boolean creneau_2, boolean creneau_3, boolean creneau_4) {
        this.creneau_1 = creneau_1;
        this.creneau_2 = creneau_2;
        this.creneau_3 = creneau_3;
        this.creneau_4 = creneau_4;
    }

    public boolean creneau_2;
    public boolean creneau_3;
    public boolean creneau_4;

}
