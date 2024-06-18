package MVC.controleur;

import javafx.scene.control.*;
import MVC.vues.AppliJO;
import MVC.vues.Connexion;
import MVC.modele.ModeleConnexion;
import MVC.modele.ModeleJO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BoutonAppliControleur implements EventHandler<ActionEvent>{
    
    private AppliJO vue;

    private ModeleConnexion modele;
    
    public BoutonAppliControleur(AppliJO vue, ModeleConnexion modele) {
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button current = (Button) actionEvent.getSource();
        String lettre = current.getText();
        if (lettre.equals("Classement")) {
            try {
                this.vue.modeClassement();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else if (lettre.equals("Epreuves")) {
            try {
                this.vue.modeEpreuve();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else if (lettre.equals("Participants")) {
            try {
                this.vue.modeParticipants();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else if (lettre.equals("Paramètres")) {
            System.out.println("d");
        }
}
}