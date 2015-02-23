package models;

import play.db.ebean.Model;

/**
 * Created by BIGVAL on 22/02/2015.
 */
public class Creneau extends Model {

    public Creneau() {
        this.creneau_1 = false;
        this.creneau_2 = false;
        this.creneau_3 = false;
        this.creneau_4 = false;
    }
    //créneau à 1 créneau occupé, créneau à 2 créneau libre
    public boolean creneau_1;
    public boolean creneau_2;
    public boolean creneau_3;
    public boolean creneau_4;

}
