package MVC.vues;

import java.io.File;
import java.net.URL;
import java.util.List;

import javafx.util.Duration;


import MVC.modele.*;
import MVC.modele.ModeleJO.Tris;
import MVC.tableClass.*;
import epreuves.Epreuve;
import MVC.controleur.*;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import participants.Athlete;
import participants.Equipe;
import participants.Participant;
import javafx.scene.text.*;

import participants.Pays;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AppliJO extends Application {

    private ModeleJO modele;
    private ModeleConnexion modeleConnexion;
    private Scene laScene;

    private StackPane racineConnexion;
    private BorderPane racineAppli;
    private Stage stage;
    private VBox contenus;
    private VBox contenusParticipants;
    private TitledPane contenusParticipantsEquipe2;
    private TitledPane contenusParticipantsEquipe1;

    private Button boutonConnexion;

    private TableView<PaysTableau> classement;
    private TableView<AthletesTableau> ath;
    private TableView<EquipeTableau> equ;
    private Button boutonClassement;
    private Button boutonEpreuve;
    private Button boutonParticipants;
    private Button boutonParametre;
    private ComboBox<String> filtre;
    private Button boutonAjouterEpreuve;
    private Button boutonAjouterAthlete;
    private Button boutonAjouterEquipe;

    private String utilisateur;
    private Roles role;
    private ComboBox<String> menuSportEpreuve;
    private ComboBox<String> menuSexeEpreuve;
    private TextField txtFieldDesc;
    private TextField txtFieldNomAthlete;
    private TextField txtFieldPrenomAthlete;
    private TextField txtFieldForceAthlete;
    private TextField txtFieldEnduranceAthlete;
    private TextField txtFieldAgiliteAthlete;
    private TextField txtFieldPaysAthlete;
    private ComboBox<String> comboBoxSexeAthlete;

    private TextField txtFieldNomEquipe;
    private TextField txtFieldPaysEquipe;
    private ComboBox<String> comboBoxSexeEquipe;


    private TextField fieldnomDansEpreuve;
    private TextField fieldPrenomEpreuve;
    private Button boutonAddEpreuve;

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
        this.utilisateur = "User";
        this.role = Roles.VISITEUR;
        this.contenus = new VBox();
        this.contenusParticipants = new VBox();
        
        this.menuSportEpreuve = new ComboBox<>();
        this.menuSexeEpreuve = new ComboBox<>();
        this.txtFieldDesc = new TextField();
        this.txtFieldNomAthlete = new TextField();
        this.txtFieldPrenomAthlete = new TextField();
        this.txtFieldForceAthlete = new TextField();
        this.txtFieldEnduranceAthlete = new TextField();
        this.txtFieldAgiliteAthlete = new TextField();
        this.txtFieldPaysAthlete = new TextField();
        this.comboBoxSexeAthlete = new ComboBox<>();
        this.txtFieldNomEquipe = new TextField();
        this.txtFieldPaysEquipe = new TextField();
        this.comboBoxSexeEquipe = new ComboBox<>();
        
        this.boutonAjouterAthlete = new Button();
        this.boutonAjouterEquipe = new Button();
        this.contenusParticipantsEquipe2 = new TitledPane();
        this.contenusParticipantsEquipe1 = new TitledPane();
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

    public void setRole(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return this.role;
    }

    public String getUtilisateur() {
        return this.utilisateur;
    }

    // MODE CONNEXION

    public void modeAccueil() throws Exception{
        this.modeleConnexion = new ModeleConnexion();
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

        // Roles
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
        this.modele.reload();
        URL url = new File("FXML/PageAppli.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        this.racineAppli = loader.load();
        this.laScene.setRoot(this.racineAppli);
        this.stage.show();

        this.boutonClassement = (Button) laScene.lookup("#boutonClassement");

        this.boutonClassement.setOnAction(new ControleurBoutonAppli(this, modele));

        this.boutonEpreuve = (Button) laScene.lookup("#boutonEpreuve");
        this.boutonEpreuve.setOnAction(new ControleurBoutonAppli(this, modele));

        this.boutonParticipants = (Button) laScene.lookup("#boutonParticipants");
        this.boutonParticipants.setOnAction(new ControleurBoutonAppli(this, modele));

        this.boutonParametre = (Button) laScene.lookup("#boutonParametre");
        this.boutonParametre.setOnAction(new ControleurBoutonAppli(this, modele));

        Button boutonDeco = (Button) laScene.lookup("#boutonDeconnexion");
        boutonDeco.setOnAction(new ControleurBoutonDeco(this));

        Button boutonRefresh = (Button) laScene.lookup("#refresh");
        ImageView imageRefresh = new ImageView("refresh.png");
        imageRefresh.setFitHeight(22);
        imageRefresh.setPreserveRatio(true);
        boutonRefresh.setOnAction(new ControleurRefresh(this.modele,this));
        // boutonRefresh.setOnAction(event -> {
            
        // });
        boutonRefresh.setGraphic(imageRefresh);

        Text textUser = (Text) laScene.lookup("#userName");
        Text textRole = (Text) laScene.lookup("#role");
        textRole.setText(this.role.getRoleStr());
        textUser.setText(this.utilisateur);

        this.modeClassement();

    }

    public void majAffichage(){
        Tris tri;
        switch (this.filtre.getValue()) {
            case "Alphabétique":
                tri = Tris.NATUREL;
                break;

            case "Total":
                tri = Tris.TOTAL;
                break;
        
            default:
                tri = Tris.MEDAILLES;
                break;
        }
        this.updateClassement(tri);
        this.updateAthlete();
        this.updateEquipe();
        try {
            this.majEpreuve();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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

        this.filtre = (ComboBox<String>) laScene.lookup("#filtre");
        this.filtre.getItems().addAll("Médailles","Alphabétique","Total");
        this.filtre.setValue("Médailles");
        this.filtre.setOnAction(new ControleurFiltreClassement(this));

        TextField fieldPays = (TextField) laScene.lookup("#fieldPays");
        Button boutonAjoutPays = (Button) laScene.lookup("#ajouterPays");
        boutonAjoutPays.setDisable(true);

        VBox boxErreur = (VBox) laScene.lookup("#boxErreur");
        boutonAjoutPays.setOnAction(new ControleurAjoutPays(this, this.modele, fieldPays,this.filtre,boxErreur));
        fieldPays.textProperty().addListener(new ControleurTFAjoutPays(this,fieldPays,boxErreur,boutonAjoutPays));


        this.classement = new TableView<>();
        this.classement.setId("tableauClassement");
        this.leClassement(Tris.MEDAILLES);

        centre.setCenter(this.classement);
        
        if(this.role == Roles.ADMIN){
            boutonAjoutPays.setVisible(true);
            fieldPays.setVisible(true);
        }
    }

    public void updateClassement(Tris tri){
        this.classement.getItems().clear();
        List<Pays> lesPays2 = this.modele.getLesPays(tri);
        for (Pays pays : lesPays2){
            this.classement.getItems().add(new PaysTableau(lesPays2.indexOf(pays)+1, pays));
        }
    }

    public void updateAthlete(){
        this.ath.getItems().clear();
        List<Athlete> lesAthletes2 = this.modele.getLesAthletes();
        for (Athlete athletess : lesAthletes2){
            this.ath.getItems().add(new AthletesTableau(athletess));
        }
    }

    public void updateEquipe(){
        this.equ.getItems().clear();
        List<Equipe> lesEquipes2 = this.modele.getLesEquipes();
        for (Equipe equipesss : lesEquipes2){
            this.equ.getItems().add(new EquipeTableau(equipesss));
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


        double[] sceneWidth = {0.0};

        classement.widthProperty().addListener((observable, oldValue, newValue) -> {
                sceneWidth[0] = newValue.doubleValue(); 
        
            
            int nbCol = classement.getColumns().size();
            for(TableColumn<PaysTableau,?> col : this.classement.getColumns()){

                col.setSortable(false);
                col.setReorderable(false);
                col.setResizable(false);
                // col.setEditable(false);
                sceneWidth[0]*=0.99; // prendre 99% de la largeur
                col.setPrefWidth((sceneWidth[0]/nbCol));
            }
        });   

    }

    public void modeEpreuve() throws Exception {
        URL url = new File("FXML/PageEpreuve.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        ScrollPane centre = loader.load();
    
        this.racineAppli.setCenter(centre);
        BorderPane.setMargin(centre, new Insets(20));
        VBox grosContenu = (VBox) centre.getContent();

        this.contenus = (VBox) grosContenu.lookup("#vboxEpreuve");

        this.boutonClassement.setDisable(false);
        this.boutonEpreuve.setDisable(true);
        this.boutonParametre.setDisable(false);
        this.boutonParticipants.setDisable(false);
        this.boutonAjouterEpreuve = (Button) grosContenu.lookup("#boutonAjouter");
        this.boutonAjouterEpreuve.setOnAction(new ControleurAjouter(this, modele));

        this.menuSportEpreuve = (ComboBox<String>) grosContenu.lookup("#menuSportEpreuve");
        this.menuSexeEpreuve = (ComboBox<String>) grosContenu.lookup("#menuPaysEpreuve");

        this.menuSexeEpreuve.getItems().addAll("Homme", "Femme");
        this.menuSportEpreuve.getItems().addAll("VolleyBall", "HandBall", "Athletisme", "Escrime", "Natation");
              
        this.txtFieldDesc = (TextField) grosContenu.lookup("#txtFieldDesc");   
        if(this.role == Roles.ADMIN){
            this.txtFieldDesc.setVisible(true);
            this.menuSportEpreuve.setVisible(true);
            this.menuSexeEpreuve.setVisible(true);
            this.boutonAjouterEpreuve.setVisible(true);
        }
    }

    public ComboBox<String> getComboSexe() {
        return this.menuSexeEpreuve;
    }

    public ComboBox<String> getComboSport() {
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

        VBox parentParticipants = (VBox) centre.getContent();

        this.contenusParticipantsEquipe1 = (TitledPane) parentParticipants.lookup("#titledPane1");


        BorderPane enfantParticipants1 = (BorderPane) this.contenusParticipantsEquipe1.getContent();

        
        this.boutonAjouterAthlete = (Button) enfantParticipants1.lookup("#boutonAjouterParticipants");
        this.boutonAjouterAthlete.setOnAction(new ControleurAjouter(this, modele));
        
        this.txtFieldNomAthlete = (TextField) enfantParticipants1.lookup("#txtFldNomAthlete");
        this.txtFieldPrenomAthlete = (TextField) enfantParticipants1.lookup("#txtFldPrenomAthlete");
        this.txtFieldForceAthlete = (TextField) enfantParticipants1.lookup("#txtFldForceAthlete");
        this.txtFieldEnduranceAthlete = (TextField) enfantParticipants1.lookup("#txtFldEnduranceAthlete");
        this.txtFieldAgiliteAthlete = (TextField) enfantParticipants1.lookup("#txtFldAgiliteAthlete");
        this.txtFieldPaysAthlete = (TextField) enfantParticipants1.lookup("#txtFldPaysAthlete");
        this.comboBoxSexeAthlete = (ComboBox<String>) enfantParticipants1.lookup("#comboBoxSexeAthlete");

        this.comboBoxSexeAthlete.getItems().addAll("Homme", "Femme");

        System.out.println(this.comboBoxSexeAthlete);

        this.ath = new TableView<>();
        this.ath.setId("tableauAthlete");
        this.lesAthletes();

        enfantParticipants1.setCenter(this.ath);


        this.contenusParticipantsEquipe2 = (TitledPane) parentParticipants.lookup("#titledPane2");

        BorderPane enfantParticipants2 = (BorderPane) this.contenusParticipantsEquipe2.getContent();

        this.boutonAjouterEquipe = (Button) enfantParticipants2.lookup("#boutonAjouterParticipants2 ");
        this.boutonAjouterEquipe.setOnAction(new ControleurAjouter(this, modele));
        
        this.txtFieldNomEquipe = (TextField) enfantParticipants2.lookup("#txtFldNomEquipe");
        this.txtFieldPaysEquipe = (TextField) enfantParticipants2.lookup("#txtFldPaysEquipe");
        this.comboBoxSexeEquipe = (ComboBox<String>) enfantParticipants2.lookup("#comboBoxSexeEquipe");

        this.comboBoxSexeEquipe.getItems().addAll("Homme", "Femme");

        this.equ = new TableView<>();
        this.equ.setId("tableauEquipe");
        this.lesEquipes();

        enfantParticipants2.setCenter(this.equ);
      
        if(this.role == Roles.ADMIN){
            this.boutonAjouterAthlete.setVisible(true);
            this.txtFieldNomAthlete.setVisible(true);
            this.txtFieldPrenomAthlete.setVisible(true);
            this.txtFieldForceAthlete.setVisible(true);
            this.txtFieldEnduranceAthlete.setVisible(true);
            this.txtFieldAgiliteAthlete.setVisible(true);
            this.txtFieldPaysAthlete.setVisible(true);
            this.comboBoxSexeAthlete.setVisible(true);
            
            this.boutonAjouterEquipe.setVisible(true);
            this.txtFieldNomEquipe.setVisible(true);
            this.txtFieldPaysEquipe.setVisible(true);
            this.comboBoxSexeEquipe.setVisible(true);
        }
    }

    public ComboBox<String> getComboSexeAthlete() {
        return this.comboBoxSexeAthlete;
    }

    public ComboBox<String> getComboSexeEquipe() {
        return this.comboBoxSexeEquipe;
    }

    private void lesAthletes(){
        this.ath.getItems().clear();
        List<Athlete> lesParticipants = this.modele.getLesAthletes();
        for (Athlete athlet : lesParticipants){
            this.ath.getItems().add(new AthletesTableau(athlet));
        }

        // Colones

        TableColumn<AthletesTableau,Integer> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));

        TableColumn<AthletesTableau,String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory("prenom"));

        TableColumn<AthletesTableau,String> sexeColumn = new TableColumn<>("Sexe");
        sexeColumn.setCellValueFactory(new PropertyValueFactory("sexe"));


        TableColumn<AthletesTableau,Integer> forceColumn = new TableColumn<>("Force");
        forceColumn.setCellValueFactory(new PropertyValueFactory("force"));


        TableColumn<AthletesTableau,Integer> enduranceColumn = new TableColumn<>("Endurance");
        enduranceColumn.setCellValueFactory(new PropertyValueFactory("endurance"));


        TableColumn<AthletesTableau,Integer> agiliteColumn = new TableColumn<>("Agilite");
        agiliteColumn.setCellValueFactory(new PropertyValueFactory("agilite"));

        TableColumn<AthletesTableau,String> paysColumn = new TableColumn<>("Pays");
        paysColumn.setCellValueFactory(new PropertyValueFactory("pays"));

        this.ath.getColumns().addAll(nomColumn,prenomColumn,sexeColumn,forceColumn,enduranceColumn,agiliteColumn, paysColumn);

        this.ath.setOpacity(0.9);

        double[] sceneWidth = {0.0};

        ath.widthProperty().addListener((observable, oldValue, newValue) -> {
                sceneWidth[0] = newValue.doubleValue(); 
            
            int nbCol = ath.getColumns().size();
            for(TableColumn<AthletesTableau,?> col : this.ath.getColumns()){

                col.setSortable(false);
                col.setReorderable(false);
                col.setResizable(false);
                // col.setEditable(false);
                sceneWidth[0]*=0.99; // prendre 99% de la largeur
                col.setPrefWidth((sceneWidth[0]/nbCol));
            }
        });   

    }

    private void lesEquipes(){
        this.equ.getItems().clear();
        List<Equipe> lesParticipants = this.modele.getLesEquipes();
        for (Equipe equipes : lesParticipants){
            this.equ.getItems().add(new EquipeTableau(equipes));
        }

        // Colones

        TableColumn<EquipeTableau,String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));

        TableColumn<EquipeTableau,String> sexeColumn = new TableColumn<>("Sexe");
        sexeColumn.setCellValueFactory(new PropertyValueFactory("sexe"));

        TableColumn<EquipeTableau,String> paysColumn = new TableColumn<>("Pays");
        paysColumn.setCellValueFactory(new PropertyValueFactory("pays"));


        this.equ.getColumns().addAll(nomColumn,sexeColumn, paysColumn);

        this.equ.setOpacity(0.9);


        double[] sceneWidth = {0.0};

        equ.widthProperty().addListener((observable, oldValue, newValue) -> {
                sceneWidth[0] = newValue.doubleValue(); 
        
            
            int nbCol = equ.getColumns().size();
            for(TableColumn<EquipeTableau,?> col : this.equ.getColumns()){

                col.setSortable(false);
                col.setReorderable(false);
                col.setResizable(false);
                // col.setEditable(false);
                sceneWidth[0]*=0.99; // prendre 99% de la largeur
                col.setPrefWidth((sceneWidth[0]/nbCol));
            }
        });   

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


    public void ajoutEpreuve(Epreuve<Participant> epreuve) throws Exception {
        BorderPane ep = modeleCreationEpreuve();
        this.contenus.getChildren().add(ep);     
    
        ImageView imageSport = (ImageView) ep.lookup("#imageSport");
        Image imageSportTemp;
        ImageView imageSexe = (ImageView) ep.lookup("#imageSexe");
        Image imageSexeTemp;

        switch (epreuve.getSport().getSport()) {
            case "Athletisme":
                imageSportTemp = new Image("Athletisme.png");
                break;
            
            case "Escrime":
                imageSportTemp = new Image("Escrime.png");
                break;

            case "Handball":
                imageSportTemp = new Image("HandBall.png");
                break;

            case "Natation":
                imageSportTemp =new Image("Natation.png");
                break;
        
            default:
                imageSportTemp = new Image("VoleyBall.png");
                break;
        }
        imageSport.setImage(imageSportTemp);
        imageSport.setFitHeight(30);
        imageSport.setPreserveRatio(true);

        if(epreuve.getSexe() == 'H'){
            imageSexeTemp = new Image("logoMale2.png");
        }
        else{
            imageSexeTemp = new Image("logoFemale2.png");
        }

        imageSexe.setImage(imageSexeTemp);
        imageSexe.setFitHeight(30);
        imageSexe.setFitWidth(30);
        
        Text test = (Text) ep.lookup("#modeleEpreuveNom");
        if (test != null) {
            test.setText(epreuve.getDescription());
        }
        this.fieldnomDansEpreuve = (TextField) ep.lookup("#fieldnomEpreuve");   
        this.fieldPrenomEpreuve = (TextField) ep.lookup("#fieldPrenomEpreuve");   
        this.boutonAddEpreuve = (Button) ep.lookup("#BoutonAddEpreuve");   

        if(this.role == Roles.ADMIN){
            boutonAddEpreuve.setVisible(true);
            fieldPrenomEpreuve.setVisible(true);
            // truc.setVisible(true); // a changer pour le menu bouton 
            fieldnomDansEpreuve.setVisible(true);
        }
    }

    public void majEpreuve() throws Exception{
        List<Epreuve<Participant>> lesEpreuves = this.modele.getLesEpreuves().reversed();
        if (!(lesEpreuves.isEmpty())) {
            this.contenus.getChildren().clear();
            for (Epreuve<Participant> ep : lesEpreuves) {
                try {
                    ajoutEpreuve(ep);
   
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
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

    public String getStringNomAthlete() {
        return this.txtFieldNomAthlete.getText();
    }

    public String getStringPrenomAthlete() {
        return this.txtFieldPrenomAthlete.getText();
    }


    public int getIntForceAthlete() {
        try {
            int valeur = Integer.parseInt(this.txtFieldForceAthlete.getText());
            return valeur;
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
        return 0;
    }

    public int getIntEnduranceAthlete() {
        try {
            int valeur = Integer.parseInt(this.txtFieldEnduranceAthlete.getText());
            return valeur;
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
        return 0;
    }

    public int getIntAgiliteAthlete() {
        try {
            int valeur = Integer.parseInt(this.txtFieldAgiliteAthlete.getText());
            return valeur;
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
        return 0;
    }

    public String getStringPaysAthlete() {
        return this.txtFieldPaysAthlete.getText();
    }

    public String getStringNomEquipe() {
        return this.txtFieldNomEquipe.getText();
    }

    public String getStringPaysEquipe() {
        return this.txtFieldPaysEquipe.getText();
    }



    public static void main(String[] args) {
        launch(args);
    }   
}
