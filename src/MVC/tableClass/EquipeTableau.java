package MVC.tableClass;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import participants.*;


public class EquipeTableau{
    private SimpleStringProperty nom;
    private SimpleStringProperty sexe;
    private SimpleStringProperty pays;

    public EquipeTableau(Equipe equipe) {
        this.nom = new SimpleStringProperty(equipe.getNom());
        String s = equipe.getSexe() + "";
        if (s.equals(" ")) {
            s = "non défini";
        }
        this.sexe = new SimpleStringProperty(s);
        Pays p = equipe.getPays();
        String nomPays = null;

        if (p == null) {
            nomPays = "non défini";
        }
        else {
            nomPays = p.getNomPays();
        }
        this.pays = new SimpleStringProperty(nomPays);
    }

    public SimpleStringProperty nomProperty() { 
        return this.nom; 
    }

    public SimpleStringProperty sexeProperty() { 
        return this.sexe; 
    }


    public SimpleStringProperty paysProperty() { 
        return this.pays; 
    }
}
