package MVC.controleur;

import MVC.modele.ModeleJO;
import MVC.vues.AppliJO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ControleurTFAjoutPays implements ChangeListener<String>{

    private AppliJO vue;
    private TextField nomPays;
    private VBox boxErreur;
    private Button boutonAjout;
    
    public ControleurTFAjoutPays(AppliJO vue, TextField nomPays, VBox boxErreur, Button boutonAjout){
        this.vue = vue;
        this.nomPays = nomPays;
        this.boxErreur = boxErreur;
        this.boutonAjout = boutonAjout;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
        if (! newValue.equals(oldValue)){
            this.boxErreur.getChildren().clear();
        }
        if (newValue.equals("")){
            this.boutonAjout.setDisable(true);
        }
        else{
            this.boutonAjout.setDisable(false);
        }
    }
}
