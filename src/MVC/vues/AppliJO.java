package MVC.vues;

import java.io.File;
import java.net.URL;
import java.util.List;
import MVC.modele.*;
import MVC.modele.ModeleJO.Tris;
import MVC.tableClass.*;
import epreuves.Epreuve;
import MVC.controleur.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import participants.Participant;
import participants.Pays;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppliJO extends Application {

    private ModeleJO modele;
    private ModeleConnexion modeleConnexion;
    private Scene laScene;

    private StackPane racineConnexion;
    private BorderPane racineAppli;
    private Stage stage;

    private Button boutonConnexion;

    private TableView<PaysTableau> classement;
    private Button boutonClassement;
    private Button boutonEpreuve;
    private Button boutonParticipants;
    private Button boutonParametre;

    private String utilisateur;
  
    private BorderPane modeleEpreuve;

    @Override
    public void init(){
        this.modele = new ModeleJO();
        this.modeleConnexion = new ModeleConnexion();
        this.laScene = new Scene(new Pane());
        this.boutonClassement = new Button();
        this.boutonEpreuve = new Button();
        this.boutonParametre = new Button();
        this.boutonParticipants = new Button();
        this.modeleEpreuve = new BorderPane();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.modeAccueil();
        this.stage.show();
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getUtilisateur() {
        return this.utilisateur;
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

        this.boutonConnexion = (Button) this.laScene.lookup("#boutonConnexion");
        VBox conditionConnexion = (VBox) this.laScene.lookup("#conditionConnexion");
        boutonConnexion.setOnAction(new ControleurBoutonConnexion(this, modeleConnexion, conditionConnexion));
        this.boutonConnexion.setDisable(true);

        Button boutonSwitch = (Button) this.laScene.lookup("#switchPage");
        boutonSwitch.setOnAction(new ControleurSwitchConnexion(this, modeleConnexion));

        VBox conditionIdentifiant = (VBox) this.laScene.lookup("#conditionPseudo");
        TextField identifiant = (TextField) this.laScene.lookup("#textFieldPseudo");
        identifiant.textProperty().addListener(new ControleurIdentifiant(this.modeleConnexion,this,identifiant,conditionIdentifiant));

        VBox conditionMDP = (VBox) this.laScene.lookup("#conditionMDP");
        TextField motDePasse = (TextField) this.laScene.lookup("#textFieldMotDePasse");
        motDePasse.textProperty().addListener(new ControleurMDP(this.modeleConnexion,this,motDePasse,conditionMDP));

    }

    public void modeInscription() throws Exception {
        // new Connexion(this.laScene);
        URL url = new File("FXML/PageInscription.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        this.racineConnexion = loader.load();
        this.laScene.setRoot(this.racineConnexion);
        
        this.stage.setTitle("Jeux IUT'Olympiques");
        this.stage.setScene(this.laScene);

        this.boutonConnexion = (Button) this.laScene.lookup("#boutonConnexion");
        VBox conditionInscrire = (VBox) this.laScene.lookup("#conditionInscrire");
        this.boutonConnexion.setOnAction(new ControleurBoutonConnexion(this, modeleConnexion,conditionInscrire));
        this.boutonConnexion.setDisable(true);

        Button boutonSwitch = (Button) this.laScene.lookup("#switchPage");
        boutonSwitch.setOnAction(new ControleurSwitchConnexion(this, modeleConnexion));

        VBox conditionIdentifiant = (VBox) this.laScene.lookup("#conditionPseudo");
        TextField identifiant = (TextField) this.laScene.lookup("#textFieldPseudo");
        identifiant.textProperty().addListener(new ControleurIdentifiant(this.modeleConnexion,this,identifiant,conditionIdentifiant));

        VBox conditionMail = (VBox) this.laScene.lookup("#conditionMail");
        TextField mail = (TextField) this.laScene.lookup("#textFieldMail");
        mail.textProperty().addListener(new ControleurMail(this.modeleConnexion,this,mail,conditionMail));

        VBox conditionMDP = (VBox) this.laScene.lookup("#conditionMDP");
        TextField motDePasse = (TextField) this.laScene.lookup("#textFieldMotDePasse");
        motDePasse.textProperty().addListener(new ControleurMDP(this.modeleConnexion,this,motDePasse,conditionMDP));

        VBox conditionMDPVerif = (VBox) this.laScene.lookup("#conditionMDPVerif");
        TextField motDePasseVerif = (TextField) this.laScene.lookup("#textFieldVerifMDP");
        ControleurMDPVerif controleurMDPVerif = new ControleurMDPVerif(this.modeleConnexion,this,motDePasseVerif,conditionMDPVerif);
        motDePasseVerif.textProperty().addListener(controleurMDPVerif);
        motDePasse.textProperty().addListener(controleurMDPVerif);

    }

    public void majBoutonCo(){
        if(this.modeleConnexion.getEstConnexion()){
            if(this.modeleConnexion.peutSeConnecter()){
                this.boutonConnexion.setDisable(false);
            }
            else{
                this.boutonConnexion.setDisable(true);
            }
        }
        else{
            if(this.modeleConnexion.peutSinscrire()){
                this.boutonConnexion.setDisable(false);
            }
            else{
                this.boutonConnexion.setDisable(true);
            }
        }
    }

    // MODE APPLI

    public void modeAppli() throws Exception {
        URL url = new File("FXML/PageAppli.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        this.racineAppli = loader.load();
        this.laScene.setRoot(this.racineAppli);
        this.stage.show();

        this.boutonClassement = (Button) laScene.lookup("#boutonClassement");
        this.boutonClassement.setOnAction(new BoutonAppliControleur(this, modeleConnexion));

        this.boutonEpreuve = (Button) laScene.lookup("#boutonEpreuve");
        this.boutonEpreuve.setOnAction(new BoutonAppliControleur(this, modeleConnexion));

        this.boutonParticipants = (Button) laScene.lookup("#boutonParticipants");
        this.boutonParticipants.setOnAction(new BoutonAppliControleur(this, modeleConnexion));

        this.boutonParametre = (Button) laScene.lookup("#boutonParametre");
        this.boutonParametre.setOnAction(new BoutonAppliControleur(this, modeleConnexion));


        this.modeClassement();

    }


    public void modeClassement() throws Exception {
        URL url = new File("FXML/PageClassement.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane centre = loader.load();
        BorderPane.setMargin(centre, new Insets(20));

        this.racineAppli.setCenter(centre);

        this.boutonClassement.setDisable(true);
        this.boutonEpreuve.setDisable(false);
        this.boutonParametre.setDisable(false);
        this.boutonParticipants.setDisable(false);

        ComboBox<String> filtre = (ComboBox<String>) laScene.lookup("#filtre");
        filtre.getItems().addAll("Médailles","Naturel","Total");
        filtre.setValue("Médailles");
        filtre.setOnAction(new ControleurFiltreClassement(this));

        this.classement = new TableView<>();
        this.classement.setId("tableauClassement");
        System.out.println(this.classement);
        this.leClassement(Tris.MEDAILLES);

        centre.setCenter(this.classement);
    }

    public void updateClassement(Tris tri){
        this.classement.getItems().clear();
        List<Pays> lesPays2 = this.modele.getLesPays(tri);
        for (Pays pays : lesPays2){
            this.classement.getItems().add(new PaysTableau(lesPays2.indexOf(pays)+1, pays));
        }
    }

    private void leClassement(Tris tri){
        this.classement.getItems().clear();
        List<Pays> lesPays = this.modele.getLesPays(tri);
        for (Pays pays : lesPays){
            this.classement.getItems().add(new PaysTableau(lesPays.indexOf(pays)+1, pays));
        }

        // Colones

        TableColumn<PaysTableau,Integer> placeColumn = new TableColumn<>("Place");
        placeColumn.setCellValueFactory(new PropertyValueFactory("place"));

        TableColumn<PaysTableau,String> nomColumn = new TableColumn<>("Pays");
        nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));

        TableColumn<PaysTableau,Integer> orColumn = new TableColumn<>("");
        orColumn.setCellValueFactory(new PropertyValueFactory("medailleOr"));
        orColumn.setId("medailleOr");

        TableColumn<PaysTableau,Integer> argentColumn = new TableColumn<>("");
        argentColumn.setCellValueFactory(new PropertyValueFactory("medailleArgent"));
        argentColumn.setId("medailleArgent");

        TableColumn<PaysTableau,Integer> bronzeColumn = new TableColumn<>("");
        bronzeColumn.setCellValueFactory(new PropertyValueFactory("medailleBronze"));
        bronzeColumn.setId("medailleBronze");

        TableColumn<PaysTableau,Integer> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(new PropertyValueFactory("totalMedailles"));

        this.classement.getColumns().addAll(placeColumn,nomColumn,orColumn,argentColumn,bronzeColumn,totalColumn);

        this.classement.setOpacity(0.9);

        this.classement.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        for(TableColumn<PaysTableau,?> col : this.classement.getColumns()){
            col.setSortable(false);
            col.setReorderable(false);
            // col.setResizable(false);
        }
    }

    public void modeEpreuve() throws Exception {
        URL url = new File("FXML/PageEpreuve.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        ScrollPane centre = loader.load();
        BorderPane.setMargin(centre, new Insets(20));
        this.racineAppli.setCenter(centre);
        this.boutonClassement.setDisable(false);
        this.boutonEpreuve.setDisable(true);
        this.boutonParametre.setDisable(false);
        this.boutonParticipants.setDisable(false);
    }

    public void modeParticipants() throws Exception {
        URL url = new File("FXML/PageParticipants.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        ScrollPane centre = loader.load();
        BorderPane.setMargin(centre, new Insets(20));
        this.racineAppli.setCenter(centre);
        this.boutonClassement.setDisable(false);
        this.boutonEpreuve.setDisable(false);
        this.boutonParametre.setDisable(false);
        this.boutonParticipants.setDisable(true);
    }

    public void modeParametre() throws Exception {
        URL url = new File("FXML/PageClassement.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane centre = loader.load();
        BorderPane.setMargin(centre, new Insets(20));
        this.racineAppli.setCenter(centre);
        this.boutonClassement.setDisable(true);
        this.boutonEpreuve.setDisable(false);
        this.boutonParametre.setDisable(false);
        this.boutonParticipants.setDisable(false);
    }

    public BorderPane creationEpreuve(Epreuve<Participant> epreuve) throws Exception{
        URL url = new File("FXML/Epreuve.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane modeleEpreuve = loader.load();

        return modeleEpreuve;
    }

    public static void main(String[] args) {
        launch(args);
    }   
}
