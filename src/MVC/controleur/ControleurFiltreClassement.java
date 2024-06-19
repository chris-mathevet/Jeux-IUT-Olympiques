package MVC.controleur;

import MVC.modele.ModeleJO.Tris;
import MVC.vues.AppliJO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControleurFiltreClassement implements EventHandler<ActionEvent>{
    private AppliJO vue;

    public ControleurFiltreClassement(AppliJO vue){
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event) {
        ComboBox<String> combo = (ComboBox<String>) event.getSource();
        switch (combo.getValue()) {
            case "Naturel":
                this.vue.updateClassement(Tris.NATUREL);
                break;

            case "Total":
                this.vue.updateClassement(Tris.TOTAL);
                break;
        
            default: // médailles
                this.vue.updateClassement(Tris.MEDAILLES);
                break;
        }        
    }
}
