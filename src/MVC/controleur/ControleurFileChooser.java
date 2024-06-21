package MVC.controleur;


import MVC.vues.AppliJO;
import MVC.vues.Connexion;
import MVC.vues.Roles;
import database.Requete;

import java.io.File;


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
import javafx.stage.FileChooser;


public class ControleurFileChooser implements EventHandler<ActionEvent>{
    
    private AppliJO vue;
    private ModeleJO modeleJO;
    private File initialDirectory;
    public ControleurFileChooser(AppliJO vue, ModeleJO modeleJO,File initialDirectory) {
        this.vue = vue;
        this.modeleJO = modeleJO;
        this.initialDirectory =initialDirectory;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("tgredqscxvfsfsf");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        if (initialDirectory != null && initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }
        File selectedFile = fileChooser.showOpenDialog(vue.getStage());

        if (selectedFile != null) {
            String absolutePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + absolutePath);
            // Perform further actions with the selected file path
            try {
                this.modeleJO.getRequete().csvToBd(absolutePath);
                
            } catch (Exception e) {
                System.err.println("probleme import lol");
            }
        } else {
            System.out.println("File selection cancelled.");
        }

        System.out.println("fsfsf");


    }
}
