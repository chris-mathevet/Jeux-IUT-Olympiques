package MVC.controleur;


import MVC.vues.AppliJO;
import MVC.vues.Roles;
import MVC.modele.ModeleConnexion;
import MVC.modele.ModeleJO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ControleurBoutonDeco implements EventHandler<ActionEvent>{
    
    private AppliJO vue;

    public ControleurBoutonDeco(AppliJO vue) {
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            this.vue.modeAccueil();    
        } catch (Exception e) {
            System.out.println("Problème déconnexion");
        }
    }
}
