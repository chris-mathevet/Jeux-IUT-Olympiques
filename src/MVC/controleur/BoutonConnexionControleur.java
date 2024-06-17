package MVC.controleur;


import MVC.vues.AppliJO;
import MVC.modele.ModeleJO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BoutonConnexionControleur implements EventHandler<ActionEvent>{
    
    private AppliJO vue;

    private ModeleJO modele;
    
    public BoutonConnexionControleur(AppliJO vue, ModeleJO modele) {
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("COUCOU");

    }
}
