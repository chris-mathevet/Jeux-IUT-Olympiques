package MVC.vues;

import java.io.File;
import java.net.URL;
import java.net.http.HttpResponse.BodyHandler;
import java.util.List;



import javafx.util.Duration;

import javafx.stage.FileChooser;

import MVC.modele.*;
import MVC.modele.ModeleJO.Tris;
import MVC.tableClass.*;
import MVC.user.User;
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
import participants.Participant;

import participants.Pays;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

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
    private ComboBox<String> filtre;
    private Button boutonAjouterEpreuve;

    private String utilisateur;
    private Roles role;
    private String mailUser;

  
    private BorderPane modeleEpreuve;
    private ComboBox<String> menuSportEpreuve;
    private ComboBox<String> menuSexeEpreuve;
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
        this.utilisateur = "User";
        this.mailUser = "Mail";
        this.role = Roles.VISITEUR;
        this.contenus = new VBox();
        
        this.menuSportEpreuve = new ComboBox<>();
        this.menuSexeEpreuve = new ComboBox<>();
        this.txtFieldDesc = new TextField();
        this.txtNomModeleEpreuve = new Text();
        this.imgSexeModeleEpreuve = new ImageView("logoMale2.png");
        this.imgSportModeleEpreuve = new ImageView("Athletisme.png");

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

    public void setMail(String m) {
        this.mailUser= m;
    }

    public Roles getRole() {
        return this.role;
    }
    public String getMail() {
        return this.mailUser;
    }

    public String getUtilisateur() {
        return this.utilisateur;
    }

    public Stage getStage(){
        return this.stage;
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
        System.out.println(lesPays);
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

    public ComboBox<?> getComboSexe() {
        return this.menuSexeEpreuve;
    }

    public ComboBox<?> getComboSport() {
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

    private Text labelPseudoParam;
    private Text labelPseudoRole;
    private Text labelPseudoMail;
    private Button boutonDeconnecterParametre;
    private Button boutonSaveParametre;
    private Button boutonFileChooserParametre;
    private HBox dragAndDropCsv;
    private TableView<UserTableau> tableUser;
    
    public void modeParametre() throws Exception {
        URL url = new File("FXML/PageParametre.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane centre = loader.load();
        this.racineAppli.setCenter(centre);
        BorderPane.setMargin(centre, new Insets(20));
     
        this.boutonClassement.setDisable(false);
        this.boutonEpreuve.setDisable(false);
        this.boutonParametre.setDisable(true);
        this.boutonParticipants.setDisable(false);


        this.labelPseudoParam = (Text)laScene.lookup("#LabelPseudoParam");
        this.labelPseudoMail = (Text)laScene.lookup("#LabelPseudoMail");
        this.labelPseudoRole = (Text)laScene.lookup("#LabelPseudoRole");
        this.boutonDeconnecterParametre = (Button)laScene.lookup("#BoutonDeconnecterParametre");
        this.boutonSaveParametre = (Button)laScene.lookup("#BoutonSaveParametre");
        this.boutonFileChooserParametre = (Button)laScene.lookup("#FileChooser");
        this.dragAndDropCsv = (HBox)laScene.lookup("#dragAndDropCsv");

        this.labelPseudoParam.setText(this.getUtilisateur());
        this.labelPseudoMail.setText(this.getMail());
        this.labelPseudoRole.setText(this.getRole().getRoleStr());
        
        this.boutonDeconnecterParametre.setOnAction(new ControleurBoutonDeco(this));
        this.boutonSaveParametre.setOnAction(new ControleurSaveParam(this)); // pour l'instant marche pas et se deco mdr
    
    
        File initialDirectory = new File("./");
        this.boutonFileChooserParametre.setOnAction(new ControleurFileChooser(this,this.modeleConnexion.getReq(),initialDirectory));
        this.tableUser = new TableView<>();        
        lesUsers();
    }


    public void lesUsers(){
        BorderPane PourTableauUser = (BorderPane)laScene.lookup("#BorderPaneParamTableau");
        List<User> lesUsersList = this.modele.getLesUsers();
        for (User u : lesUsersList) {
            this.tableUser.getItems().add(new UserTableau(u.getPseudo(),u.getMail(),u.getRole()));
        }


        TableColumn<UserTableau,String> nomColumn = new TableColumn<>("Pseudo");
        nomColumn.setCellValueFactory(new PropertyValueFactory("pseudo"));

        TableColumn<UserTableau,String> mailColumn = new TableColumn<>("Pseudo");
        mailColumn.setCellValueFactory(new PropertyValueFactory("mail"));

        TableColumn<UserTableau,String> roleColumn = new TableColumn<>("Pseudo");
        roleColumn.setCellValueFactory(new PropertyValueFactory("role"));

        this.tableUser.getColumns().addAll(nomColumn,mailColumn,roleColumn);


        

        double[] sceneWidth = {0.0};

        tableUser.widthProperty().addListener((observable, oldValue, newValue) -> {
                sceneWidth[0] = newValue.doubleValue(); 
        
            
            int nbCol = tableUser.getColumns().size();
            for(TableColumn<UserTableau,?> col : this.tableUser.getColumns()){

                col.setSortable(false);
                col.setReorderable(false);
                col.setResizable(false);
                // col.setEditable(false);
                sceneWidth[0]*=0.99; // prendre 99% de la largeur
                col.setPrefWidth((sceneWidth[0]/nbCol));
            }
        });
        PourTableauUser.setCenter(this.tableUser);

    }



    public void ajoutEpreuve(Epreuve<Participant> epreuve) throws Exception {
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
            for (Epreuve<Participant> ep : lesEpreuves) {
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
