package MVC.controleur;

import MVC.modele.ModeleJO;
import MVC.modele.ModeleJO.Tris;
import MVC.vues.AppliJO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurRefresh implements EventHandler<ActionEvent>{
    ModeleJO modele;
    AppliJO app;

    public ControleurRefresh(ModeleJO modele, AppliJO app){
        this.modele = modele;
        this.app = app;
    }

    @Override
    public void handle(ActionEvent arg0) {
        System.out.println("refresh");
        this.modele.reload();
        this.app.updateClassement(Tris.NATUREL);
        System.out.println("fin refresh");
    }
}
