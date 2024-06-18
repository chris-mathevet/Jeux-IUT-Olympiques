package MVC.controleur;


import MVC.vues.AppliJO;
import MVC.vues.Connexion;
import MVC.modele.ModeleConnexion;
import MVC.modele.ModeleJO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BoutonConnexionControleur implements EventHandler<ActionEvent>{
    
    private AppliJO vue;

    private ModeleConnexion modele;
    
    public BoutonConnexionControleur(AppliJO vue, ModeleConnexion modele) {
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            this.vue.modeAppli();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
