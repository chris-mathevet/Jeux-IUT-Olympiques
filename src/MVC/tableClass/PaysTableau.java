package MVC.tableClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import participants.Pays;

public class PaysTableau{
    private SimpleIntegerProperty place;
    private SimpleStringProperty nom;
    private SimpleIntegerProperty medailleOr;
    private SimpleIntegerProperty medailleArgent;
    private SimpleIntegerProperty medailleBronze;
    private SimpleIntegerProperty totalMedailles;

    public PaysTableau(int place, Pays pays){
        this.place = new SimpleIntegerProperty(place);
        this.nom = new SimpleStringProperty(pays.getNomPays());
        this.medailleOr = new SimpleIntegerProperty(pays.getMedailleOr());
        this.medailleArgent = new SimpleIntegerProperty(pays.getMedailleArgent());
        this.medailleBronze = new SimpleIntegerProperty(pays.getMedailleBronze());
        this.totalMedailles = new SimpleIntegerProperty(pays.getMedailleOr() + pays.getMedailleArgent() + pays.getMedailleBronze());
    }

    public void setPlace(int value) { 
        this.place.set(value); 
    }

    public SimpleIntegerProperty placeProperty() { 
        return this.place; 
    }

    public SimpleStringProperty nomProperty(){
        return this.nom;
    }

    public SimpleIntegerProperty medailleOrProperty() { 
        return this.medailleOr; 
    }

    public SimpleIntegerProperty medailleArgentProperty() { 
        return this.medailleArgent; 
    }

    public SimpleIntegerProperty medailleBronzeProperty() { 
        return this.medailleBronze; 
    }

    public SimpleIntegerProperty totalMedaillesProperty() { 
        return this.totalMedailles; 
    }
}
