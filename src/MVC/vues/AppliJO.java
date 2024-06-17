package MVC.vues;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import MVC.modele.*;
import MVC.controleur.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class AppliJO extends Application {

    private ModeleJO modele;
    private Scene laScene;

    @Override
    public void init(){
        this.modele = new ModeleJO();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("FXML/PageConnexion.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        StackPane racine = loader.load();
        this.laScene = new Scene(racine);
        stage.setTitle("Jeux IUT'Olympiques");
        stage.setScene(this.laScene);

        stage.show();

        Button boutonConnexion = (Button) laScene.lookup("#boutonConnexion"); 
        boutonConnexion.setOnAction(new BoutonConnexionControleur(this, modele));

    }



    public static void main(String[] args) {
        launch(args);
    }   
    
}
