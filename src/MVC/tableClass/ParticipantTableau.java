package MVC.tableClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import participants.Athlete;

public class ParticipantTableau {
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty sexe;
    private SimpleIntegerProperty force;
    private SimpleIntegerProperty endurance;
    private SimpleIntegerProperty agilite;
    private SimpleStringProperty pays;

    public ParticipantTableau(Athlete athlete) {
        this.nom = new SimpleStringProperty(athlete.getNom());
        this.prenom = new SimpleStringProperty(athlete.getPrenom());
        this.sexe = new SimpleStringProperty(Character.toString(athlete.getSexe()));
        this.force = new SimpleIntegerProperty(athlete.getForce());
        this.endurance = new SimpleIntegerProperty(athlete.getEndurance());
        this.agilite = new SimpleIntegerProperty(athlete.getAgilite());
        this.pays = new SimpleStringProperty(athlete.getPays().getNomPays());
    }

    public SimpleStringProperty nomProperty() { 
        return this.nom; 
    }

    public SimpleStringProperty prenomProperty(){
        return this.prenom;
    }

    public SimpleStringProperty sexeProperty() { 
        return this.sexe; 
    }

    public SimpleIntegerProperty forceProperty() { 
        return this.force; 
    }

    public SimpleIntegerProperty enduranceProperty() { 
        return this.endurance; 
    }

    public SimpleIntegerProperty agiliteProperty() { 
        return this.agilite; 
    }

    public SimpleStringProperty paysProperty() { 
        return this.pays; 
    }
}
