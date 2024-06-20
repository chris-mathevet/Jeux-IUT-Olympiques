package MVC.vues;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.swing.text.html.ImageView;

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
import javafx.scene.text.*;

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
    private VBox contenus;

    private Button boutonConnexion;

    private TableView<PaysTableau> classement;
    private Button boutonClassement;
    private Button boutonEpreuve;
    private Button boutonParticipants;
    private Button boutonParametre;
    private Button boutonAjouterEpreuve;

    private String utilisateur;
  
    private BorderPane modeleEpreuve;
    private ComboBox menuSportEpreuve;
    private ComboBox menuSexeEpreuve;
    private TextField txtFieldDesc;

    private Text txtNomModeleEpreuve;
    private ImageView imgSexeModeleEpreuve;
    private ImageView imgSportModeleEpreuve;
    

    @Override
    public void init(){
        this.modele = new ModeleJO();
        this.modeleConnexion = new ModeleConnexion();
        this.laScene = new Scene(new Pane());
        this.boutonClassement = new Button();
        this.boutonEpreuve = new Button();
        this.boutonParametre = new Button();
        this.boutonParticipants = new Button();
        this.boutonAjouterEpreuve = new Button();
        this.modeleEpreuve = new BorderPane();
        this.contenus = new VBox();
        
        this.menuSportEpreuve = new ComboBox();
        this.menuSexeEpreuve = new ComboBox();
        this.txtFieldDesc = new TextField();
        this.txtNomModeleEpreuve = new Text();
        this.imgSexeModeleEpreuve = new ImageView(null);
        this.imgSportModeleEpreuve = new ImageView(null);
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

        this.boutonClassement.setOnAction(new BoutonAppliControleur(this, modele));

        this.boutonEpreuve = (Button) laScene.lookup("#boutonEpreuve");
        this.boutonEpreuve.setOnAction(new BoutonAppliControleur(this, modele));

        this.boutonParticipants = (Button) laScene.lookup("#boutonParticipants");
        this.boutonParticipants.setOnAction(new BoutonAppliControleur(this, modele));

        this.boutonParametre = (Button) laScene.lookup("#boutonParametre");
        this.boutonParametre.setOnAction(new BoutonAppliControleur(this, modele));


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
        filtre.getItems().addAll("Médailles","Alphabétique","Total");
        filtre.setValue("Médailles");
        filtre.setOnAction(new ControleurFiltreClassement(this));

        TextField fieldPays = (TextField) laScene.lookup("#fieldPays");
        Button boutonAjoutPays = (Button) laScene.lookup("#ajouterPays");
        boutonAjoutPays.setDisable(true);

        VBox boxErreur = (VBox) laScene.lookup("#boxErreur");
        boutonAjoutPays.setOnAction(new ControleurAjoutPays(this, this.modele, fieldPays,filtre,boxErreur));
        fieldPays.textProperty().addListener(new ControleurTFAjoutPays(this,fieldPays,boxErreur,boutonAjoutPays));


        // this.classement = new TableView<>();
        // this.classement.setId("tableauClassement");
        // this.leClassement(Tris.MEDAILLES);

        centre.setCenter(this.classement);
    }

    public void updateClassement(Tris tri){
        this.classement.getItems().clear();
        List<Pays> lesPays2 = this.modele.getLesPays(tri);
        for (Pays pays : lesPays2){
            this.classement.getItems().add(new PaysTableau(lesPays2.indexOf(pays)+1, pays));
        }
    }

    // private void leClassement(Tris tri){
    //     this.classement.getItems().clear();
    //     List<Pays> lesPays = this.modele.getLesPays(tri);
    //     for (Pays pays : lesPays){
    //         this.classement.getItems().add(new PaysTableau(lesPays.indexOf(pays)+1, pays));
    //     }

    //     // Colones

    //     TableColumn<PaysTableau,Integer> placeColumn = new TableColumn<>("Place");
    //     placeColumn.setCellValueFactory(new PropertyValueFactory("place"));

    //     TableColumn<PaysTableau,String> nomColumn = new TableColumn<>("Pays");
    //     nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));

    //     TableColumn<PaysTableau,Integer> orColumn = new TableColumn<>("");
    //     orColumn.setCellValueFactory(new PropertyValueFactory("medailleOr"));
    //     orColumn.setId("medailleOr");

    //     TableColumn<PaysTableau,Integer> argentColumn = new TableColumn<>("");
    //     argentColumn.setCellValueFactory(new PropertyValueFactory("medailleArgent"));
    //     argentColumn.setId("medailleArgent");

    //     TableColumn<PaysTableau,Integer> bronzeColumn = new TableColumn<>("");
    //     bronzeColumn.setCellValueFactory(new PropertyValueFactory("medailleBronze"));
    //     bronzeColumn.setId("medailleBronze");

    //     TableColumn<PaysTableau,Integer> totalColumn = new TableColumn<>("Total");
    //     totalColumn.setCellValueFactory(new PropertyValueFactory("totalMedailles"));

    //     this.classement.getColumns().addAll(placeColumn,nomColumn,orColumn,argentColumn,bronzeColumn,totalColumn);

    //     this.classement.setOpacity(0.9);


    //     double[] sceneWidth = {0.0};

    //     classement.widthProperty().addListener((observable, oldValue, newValue) -> {
    //             sceneWidth[0] = newValue.doubleValue(); 
        
            
    //         int nbCol = classement.getColumns().size();
    //         for(TableColumn<PaysTableau,?> col : this.classement.getColumns()){

    //             col.setSortable(false);
    //             col.setReorderable(false);
    //             col.setResizable(false);
    //             // col.setEditable(false);
    //             sceneWidth[0]*=0.99; // prendre 99% de la largeur
    //             col.setPrefWidth((sceneWidth[0]/nbCol));
    //         }
    //     });   

    // }

    public void modeEpreuve() throws Exception {
        URL url = new File("FXML/PageEpreuve.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        ScrollPane centre = loader.load();
    
        this.racineAppli.setCenter(centre);
        BorderPane.setMargin(centre, new Insets(20));
        VBox grosContenu = (VBox) centre.getContent();

        contenus = (VBox) grosContenu.lookup("#vboxEpreuve");
        System.out.println(contenus.getChildren());

        this.boutonClassement.setDisable(false);
        this.boutonEpreuve.setDisable(true);
        this.boutonParametre.setDisable(false);
        this.boutonParticipants.setDisable(false);
        this.boutonAjouterEpreuve = (Button) grosContenu.lookup("#boutonAjouter");
        this.boutonAjouterEpreuve.setOnAction(new ControleurAjouter(this, modele));

        this.menuSportEpreuve = (ComboBox) grosContenu.lookup("#menuSportEpreuve");
        this.menuSexeEpreuve = (ComboBox) grosContenu.lookup("#menuPaysEpreuve");

        this.menuSexeEpreuve.getItems().addAll("Homme", "Femme");
        this.menuSportEpreuve.getItems().addAll("VolleyBall", "HandBall", "Athletisme", "Escrime", "Natation");
        



      
        this.txtFieldDesc = (TextField) grosContenu.lookup("#txtFieldDesc");

       
    }

    public ComboBox getComboSexe() {
        return this.menuSexeEpreuve;
    }

    public ComboBox getComboSport() {
        return this.menuSportEpreuve;
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


    public void ajoutEpreuve(Epreuve epreuve) throws Exception {
        BorderPane ep = modeleCreationEpreuve();
        this.contenus.getChildren().add(ep);
        
        // Assurez-vous que l'ID du Label dans votre FXML est bien "modeleEpreuveNom"
        Text test = (Text) ep.lookup("#modeleEpreuveNom");
        if (test != null) {
            test.setText(epreuve.getDescription());
        }

    }

    public void majEpreuve(List<Epreuve<Participant>> lesEpreuves) throws Exception{
        if (!(lesEpreuves.isEmpty())) {
            this.contenus.getChildren().clear();
            for (Epreuve ep : lesEpreuves) {
                try {
                    ajoutEpreuve(ep);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        System.out.println(lesEpreuves);

    }

     public BorderPane modeleCreationEpreuve() throws Exception {
         URL url = new File("FXML/Epreuve.fxml").toURI().toURL();
         FXMLLoader loader = new FXMLLoader(url);
         BorderPane modeleEpreuve = loader.load();
         return modeleEpreuve;
     }


    public String getStringDescription() {
        return this.txtFieldDesc.getText();
    }



    public static void main(String[] args) {
        launch(args);
    }   
}
