package MVC.vues;

import java.io.File;
import java.net.URL;

import MVC.controleur.ControleurBoutonConnexion;
import MVC.modele.ModeleConnexion;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Connexion {
    private StackPane root;
    private ModeleConnexion modele;

    public Connexion(Scene scene){
        root = new StackPane();
        try{
            URL url = new File("FXML/PageConnexion.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            this.root = loader.load();
            scene.setRoot(root);
            Button boutonConnexion = (Button) scene.lookup("#boutonConnexion"); 
            //boutonConnexion.setOnAction(new BoutonConnexionControleur(this, modele));
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
        scene.setRoot(root);
    }
}
