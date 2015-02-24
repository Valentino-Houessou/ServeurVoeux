package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

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
        return TODO;
    }

    public static Result delete(Long id) {
        return TODO;
    }

    public static Result update(Long id){return TODO;}

    public static Result getPreferenceEnseignant(Long id){
        return TODO;
    }

    public static Result historiquePreferenceEnseignant(Long id, Long annee){
        return TODO;
    }

    public static Result PreferenceFiliere(Long id){
        return TODO;
    }

    /*public static Result add() {

        Project newProject = Project.create("New project", dynForm.bindFromRequest().get("folder"), request().username());
        return ok(views.html.projects.item.render(newProject));
    }*/
}
