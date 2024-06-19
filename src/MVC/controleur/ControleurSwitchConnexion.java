package MVC.controleur;

import MVC.modele.ModeleConnexion;
import MVC.vues.AppliJO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurSwitchConnexion implements EventHandler<ActionEvent>{
    private AppliJO vue;

    private ModeleConnexion modele;
    
    public ControleurSwitchConnexion(AppliJO vue, ModeleConnexion modele) {
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(modele.getEstConnexion()){
            try {
                this.vue.modeInscription();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else{
            try {                
                this.vue.modeConnexion();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        this.modele.changementMode();
    }
}
