package MVC.controleur;


import MVC.vues.AppliJO;
import MVC.vues.Connexion;
import MVC.vues.Roles;

import java.sql.SQLException;

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


public class ControleurBoutonConnexion implements EventHandler<ActionEvent>{
    
    private AppliJO vue;

    private ModeleConnexion modele;

    private VBox conditionBox;

    private HBox boxErreur;
    private Text erreurText;
    
    public ControleurBoutonConnexion(AppliJO vue, ModeleConnexion modele, VBox conditionBox) {
        this.vue = vue;
        this.modele = modele;
        this.conditionBox = conditionBox;
        this.boxErreur = new HBox();
        this.boxErreur.setSpacing(4);
        this.erreurText = new Text("Au moins 8 caractères");
        ImageView imageErreur = new ImageView("erreur.png");

        this.erreurText.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());        
        this.erreurText.setFill(Color.web("#EB5252"));        
        this.erreurText.setFont(new Font("System", 8));  
        
        this.boxErreur.getChildren().addAll(imageErreur,this.erreurText);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button bouton = (Button) actionEvent.getSource();
        int req = 0;
        String roleCo = "";
        this.conditionBox.getChildren().remove(this.boxErreur);
        Node erreur = null;
        if(this.modele.getEstConnexion()){
            roleCo = this.modele.connexion();
            if(roleCo == "-1"){ // Identifiant n'existe pas
                this.erreurText.setText("Ce nom d'utilisateur n'existe pas");
                erreur = this.boxErreur;
                bouton.setDisable(true);
            }
            else if(roleCo == "-2"){  // Problème connexion
                this.erreurText.setText("Problème de connexion");
                erreur = this.boxErreur;
                bouton.setDisable(true);
            }
            else{ // Connexion réussi
                this.vue.setUtilisateur(this.modele.getIdentifiant());
                this.vue.setRole(Roles.getRole(roleCo));
                try {
                    this.vue.setMail(this.modele.getReq().getMailUser(this.modele.getIdentifiant()));
                    
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
                try {
                    this.vue.modeAppli();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        else{
            req = this.modele.inscrire();
            if(req == -1){ // Identifiant existant
                this.erreurText.setText("Ce nom d'utilisateur existe déjà");
                erreur = this.boxErreur;
                bouton.setDisable(true);
            }
            else if(req == -2){ // Mail existant
                this.erreurText.setText("Ce mail existe déjà");
                erreur = this.boxErreur;
                bouton.setDisable(true);
            }
            else if (req == -3){ // Problème inscription
                this.erreurText.setText("Problème inscription");
                erreur = this.boxErreur;
                bouton.setDisable(true);
            }
            else{ // Connexion réussi
                this.vue.setUtilisateur(this.modele.getIdentifiant());
                this.vue.setRole(Roles.getRole("visiteur"));
                try {
                    this.vue.modeAppli();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        if (erreur != null) {
            this.conditionBox.getChildren().add(erreur);
        }
    }
}
