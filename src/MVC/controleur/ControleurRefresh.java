package MVC.controleur;

import MVC.modele.ModeleJO;
import MVC.modele.ModeleJO.Tris;
import MVC.vues.AppliJO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurRefresh implements EventHandler<ActionEvent>{
    ModeleJO modele;
    AppliJO app;

    public ControleurRefresh(ModeleJO modele, AppliJO app){
        this.modele = modele;
        this.app = app;
    }

    @Override
    public void handle(ActionEvent arg0) {
        Button bouton = (Button) arg0.getSource();
        bouton.setDisable(true);
        this.modele.reload();
        this.app.majAffichage();
        bouton.setDisable(false);

    }
}
