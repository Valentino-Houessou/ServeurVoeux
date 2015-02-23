package models;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

/**
 * Created by BIGVAL on 22/02/2015.
 */
@Entity
public class EC extends Model {

    @Id
    public String codeEc;
    public UE ref_ue;
    public String libelle;
    public Double volume_horaire_cours;
    public Double volume_horaire_td;

    public static Model.Finder<String,EC> find = new Model.Finder(
            String.class, EC.class
    );

}
