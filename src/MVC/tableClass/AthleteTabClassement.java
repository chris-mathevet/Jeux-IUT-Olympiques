package MVC.tableClass;
import epreuves.Epreuve;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import participants.Athlete;
import participants.Equipe;
import participants.Participant;
import participants.Pays;

public class AthleteTabClassement{
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleIntegerProperty place;
    private SimpleDoubleProperty point;
    private SimpleStringProperty pays;

    public AthleteTabClassement(Participant participant, Epreuve<Participant> epreuve, int place) {
        
        
        this.pays = new SimpleStringProperty(participant.getPays().getNomPays());
        this.place = new SimpleIntegerProperty(place);
        if(epreuve.getSport().getNbParEquipe()!=1){
            this.prenom = new SimpleStringProperty("rien");
            Equipe eq = (Equipe) participant;
            this.nom = new SimpleStringProperty(eq.getNom());
        }
        else{
            Athlete at = (Athlete) participant;
            this.nom = new SimpleStringProperty(at.getNom());
            this.prenom = new SimpleStringProperty(at.getPrenom());
        }
        this.point = new SimpleDoubleProperty(epreuve.getResultatParticipant(participant));

    }

    public SimpleStringProperty nomProperty() { 
        return this.nom; 
    }

    public SimpleStringProperty prenomProperty(){
        return this.prenom;
    }

    public SimpleIntegerProperty placeProperty() { 
        return this.place; 
    }

    public SimpleStringProperty paysProperty() { 
        return this.pays; 
    }

    public SimpleDoubleProperty pointProperty() { 
        return this.point; 
    }
}
