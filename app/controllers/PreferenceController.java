package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Creneau;
import models.Preferences;
import play.mvc.*;
import play.libs.Json;
import views.html.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BIGVAL on 21/02/2015.
 */
public class PreferenceController extends Controller {
    public static Result index() {
        return ok(index.render("Preferences index"));
    }

    public static Result preferences() {
        return TODO;
    }

    public static Result add() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        JsonNode json = request().body().asJson();
        long user;
        Creneau creneau;
        java.sql.Date jour;
        Preferences pref;
        try {
            if (json == null) {
                return badRequest("Expecting Json data");
            }
            else {

                if (request().username() == null) {
                    return badRequest("Missing parameter [user]");
                }
                else{
                    user = Long.parseLong(request().username());
                }

                String creneau_1 = json.findPath("creneau1").textValue();
                String creneau_2 = json.findPath("creneau2").textValue();
                String creneau_3 = json.findPath("creneau3").textValue();
                String creneau_4 = json.findPath("creneau4").textValue();

                if((creneau_1 == null) && (creneau_2 == null) &&
                        (creneau_3 == null) && (creneau_4 == null) ){
                    return badRequest("Missing parameters [creneau...]");
                }
                else{
                    boolean c1 = (creneau_1 == "true") ? true: false;
                    boolean c2 = (creneau_2 == "true") ? true: false;
                    boolean c3 = (creneau_3 == "true") ? true: false;
                    boolean c4 = (creneau_4 == "true") ? true: false;
                    creneau = new Creneau(c1,c2,c3,c4);
                }
                String jourS = json.findPath("jour").textValue();
                if (jourS == null) {
                    return badRequest("Missing parameter [jour]");
                }
                else {
                    Date parsed = format.parse(jourS);
                    jour = new java.sql.Date(parsed.getTime());
                }
            }
            pref = Preferences.create(creneau,jour,user);
            return ok(Json.toJson(pref));
        }
        catch (ParseException e) {
            return badRequest("Can't parsed [jour]");
        }
        catch (NumberFormatException nFE) {
            return badRequest("Bad format for user id");
        }
        return TODO;
    }

    public static Result delete(Long id) {

        return TODO;
    }

    public static Result update(Long id){return TODO;}

    public static Result getPreferenceEnseignant(Long id){
        return TODO;
    }

    public static Result getPreferenceEnseignantCourant(){
        return TODO;
    }

    public static Result historiquePreferenceEnseignant(Long id, Long annee){
        return TODO;
    }

    public static Result PreferenceFiliere(Long id){
        return TODO;
    }

}
