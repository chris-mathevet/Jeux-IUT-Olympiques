package MVC.controleur;

import MVC.modele.ModeleJO;
import MVC.modele.ModeleJO.Tris;
import MVC.vues.AppliJO;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ControleurRefresh implements EventHandler<ActionEvent>{
    ModeleJO modele;
    AppliJO app;

    public ControleurRefresh(ModeleJO modele, AppliJO app){
        this.modele = modele;
        this.app = app;
    }

    // @Override
    // public void handle(ActionEvent arg0) {
    //     Button bouton = (Button) arg0.getSource();
    //     // bouton.setDisable(true);
    //     RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), bouton);
    //     rotateTransition.setByAngle(360);
    //     rotateTransition.play();

    //     this.modele.reload();
    //     this.app.majAffichage();
    //     // bouton.setDisable(false);
    // }

    public void handle(ActionEvent arg0) {
        Button bouton = (Button) arg0.getSource();
        bouton.setDisable(true);  // Désactiver le bouton
    
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), bouton);
        rotateTransition.setByAngle(360);
        rotateTransition.setOnFinished(event -> {
            this.modele.reload();  // Recharger le modèle
            this.app.majAffichage();  // Mettre à jour l'affichage
            bouton.setDisable(false);  // Réactiver le bouton
        });
        rotateTransition.play();
    }
}
