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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Node;


public class AppliJO extends Application {

    private ModeleJO modele;
    private ModeleConnexion modeleConnexion;
    private Scene laScene;

    private StackPane racineConnexion;
    private BorderPane racineAppli;
    private Stage stage;

    private VBox conditionMDP;

    @Override
    public void init(){
        this.modele = new ModeleJO();
        this.modeleConnexion = new ModeleConnexion();
        this.laScene = new Scene(new Pane());
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.modeAccueil();
        this.stage.show();
    }

    // MODE CONNEXION

    public void modeAccueil() throws Exception{
        this.modeConnexion();
    }

    public void modeConnexion() throws Exception {
        // new Connexion(this.laScene);
        URL url = new File("FXML/PageConnexion.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        this.racineConnexion = loader.load();
        this.laScene.setRoot(this.racineConnexion);
        
        this.stage.setTitle("Jeux IUT'Olympiques");
        this.stage.setScene(this.laScene);


        Button boutonConnexion = (Button) this.laScene.lookup("#boutonConnexion");
        boutonConnexion.setOnAction(new BoutonConnexionControleur(this, modeleConnexion));
        Button boutonSwitch = (Button) this.laScene.lookup("#switchPage");
        boutonSwitch.setOnAction(new ControleurSwitchConnexion(this, modeleConnexion));
    }

    public void modeInscription() throws Exception {
        // new Connexion(this.laScene);
        URL url = new File("FXML/PageInscription.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        this.racineConnexion = loader.load();
        this.laScene.setRoot(this.racineConnexion);
        
        this.stage.setTitle("Jeux IUT'Olympiques");
        this.stage.setScene(this.laScene);

        Button boutonConnexion = (Button) this.laScene.lookup("#boutonConnexion");
        boutonConnexion.setOnAction(new BoutonConnexionControleur(this, modeleConnexion));
        Button boutonSwitch = (Button) this.laScene.lookup("#switchPage");
        boutonSwitch.setOnAction(new ControleurSwitchConnexion(this, modeleConnexion));

        VBox conditionMDP = (VBox) this.laScene.lookup("#conditionMDP");
        TextField motDePasse = (TextField) this.laScene.lookup("#textFieldMotDePasse");
        motDePasse.focusedProperty().addListener(new ControleurMDP(this.modeleConnexion,motDePasse,conditionMDP));

        VBox conditionMDPVerif = (VBox) this.laScene.lookup("#conditionMDPVerif");
        TextField motDePasseVerif = (TextField) this.laScene.lookup("#textFieldVerifMDP");
        motDePasseVerif.focusedProperty().addListener(new ControleurMDPVerif(this.modeleConnexion,motDePasseVerif,conditionMDPVerif));

        VBox conditionMail = (VBox) this.laScene.lookup("#conditionMail");
        TextField mail = (TextField) this.laScene.lookup("#textFieldMail");
        mail.focusedProperty().addListener(new ControleurMail(this.modeleConnexion,mail,conditionMail));

        VBox conditionIdentifiant = (VBox) this.laScene.lookup("#conditionPseudo");
        TextField identifiant = (TextField) this.laScene.lookup("#textFieldPseudo");
        identifiant.focusedProperty().addListener(new ControleurIdentifiant(this.modeleConnexion,identifiant,conditionIdentifiant));
    }

    // MODE APPLI

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
