package MVC.controleur;

import java.util.List;

import MVC.tableClass.AthleteTabClassement;
import MVC.vues.AppliJO;
import epreuves.Epreuve;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import participants.Participant;

public class ControleurLancerEpreuve implements EventHandler<ActionEvent>{
    private Epreuve<Participant> epreuve;
    private AppliJO app;
    TableView<AthleteTabClassement> tab;
    
    public ControleurLancerEpreuve(Epreuve<Participant> epreuve, AppliJO app, TableView<AthleteTabClassement> tab){
        this.epreuve = epreuve;
        this.app = app;
        this.tab = tab;
    }

    @Override
    public void handle(ActionEvent arg0) {
        List<Participant> liste = this.epreuve.getLeClassement();
        System.out.println("appuie");
        System.out.println(liste);
        if(liste != null){
            this.app.majClassementEpreuve(epreuve,tab);
            System.out.println("lancer");
            Button bouton = (Button) arg0.getSource();
        }   
        else{
            System.err.println("Pas assès de participants");
        }
    }
}
