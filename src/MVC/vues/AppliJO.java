package MVC.vues;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import MVC.modele.*;
import MVC.controleur.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Node;


public class AppliJO extends Application {

    private ModeleJO modele;
    private Scene laScene;

    private StackPane racineConnexion;
    private BorderPane racineAppli;
    private Stage stage;

    @Override
    public void init(){
        this.modele = new ModeleJO();
        this.laScene = new Scene(new Pane());
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.modeConnexion();
    }

    public void modeConnexion() throws Exception {
        URL url = new File("FXML/PageConnexion.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        this.racineConnexion = loader.load();
        this.laScene.setRoot(this.racineConnexion);
        
        this.stage.setTitle("Jeux IUT'Olympiques");
        this.stage.setScene(this.laScene);

        this.stage.show();

        Button boutonConnexion = (Button) laScene.lookup("#boutonConnexion"); 
        boutonConnexion.setOnAction(new BoutonConnexionControleur(this, modele));
    }

    public void modeAppli() throws Exception {
        URL url = new File("FXML/PageAppli.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        this.racineAppli = loader.load();
        this.laScene.setRoot(this.racineAppli);
        this.modeClassement();
        this.stage.show();
    }


    public void modeClassement() throws Exception {
        URL url = new File("FXML/PageClassement.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane centre = loader.load();
        BorderPane.setMargin(centre, new Insets(20));
        this.racineAppli.setCenter(centre);
    }


    public static void main(String[] args) {
        launch(args);
    }   
    
}
