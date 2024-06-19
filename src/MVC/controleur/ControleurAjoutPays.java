package MVC.controleur;

import MVC.modele.ModeleJO;
import MVC.modele.ModeleJO.Tris;
import MVC.vues.AppliJO;
import exceptions.AlreadyExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControleurAjoutPays implements EventHandler<ActionEvent>{
    private AppliJO vue;
    private TextField nomPays;
    private ModeleJO modele;
    private ComboBox<String> combo;

    public ControleurAjoutPays(AppliJO vue, ModeleJO modele, TextField nomPays, ComboBox<String> combo){
        this.vue = vue;
        this.modele = modele;
        this.nomPays = nomPays;
        this.combo = combo;
    }

    @Override
    public void handle(ActionEvent arg0) {
        String nom = this.nomPays.getText();
        String stringTri = this.combo.getValue();
        Tris tri;
        switch (stringTri) {
            case "Naturel":
                tri = Tris.NATUREL;
                break;
            
            case "Total":
                tri = Tris.TOTAL;
                break;
        
            default:
                tri = Tris.MEDAILLES;
                break;
        }
        try {
            this.modele.creerPays(nom);
            this.vue.updateClassement(tri);
        } catch (AlreadyExistException e) {
            System.out.println("Existe déjà");
        }
    }
}
