package MVC.controleur;

import MVC.modele.ModeleConnexion;
import MVC.vues.AppliJO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControleurIdentifiant implements ChangeListener<String>{
    ModeleConnexion modele;
    TextField identifiant;
    VBox conditionBox;
    AppliJO vue;

    HBox conditionNonExist;
    HBox conditionIdentifiant;

    public ControleurIdentifiant(ModeleConnexion modele, AppliJO vue, TextField mdp, VBox condition){
        this.modele = modele;
        this.identifiant = mdp;
        this.conditionBox = condition;
        this.vue = vue;

        this.conditionIdentifiant = new HBox();
        this.conditionNonExist = new HBox();

        this.conditionIdentifiant.setSpacing(4);

        Text conditionIdentifiantText = new Text("Au moins 8 caractères");
        Text conditionNonExistText = new Text("Cet identifiant est déjà attribué");

        ImageView imageErreur = new ImageView("erreur.png");

        conditionIdentifiantText.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());        
        conditionIdentifiantText.setFill(Color.web("#EB5252"));        
        conditionIdentifiantText.setFont(new Font("System", 8));    

        conditionNonExistText.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());        
        conditionNonExistText.setFill(Color.web("#EB5252"));        
        conditionNonExistText.setFont(new Font("System", 8));    
        
        this.conditionNonExist.getChildren().addAll(new ImageView("erreur.png"), conditionNonExistText);
        this.conditionIdentifiant.getChildren().addAll(new ImageView("erreur.png"), conditionIdentifiantText);
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){ 
        if (! newValue.equals(oldValue)) {
            this.modele.setIdentifiant(this.identifiant.getText());
            if (this.modele.getEstConnexion()){

            }
            else{
                if(! this.modele.identifiantCorect()){
                    this.identifiant.setStyle("-fx-border-color: #EB5252");
                    if(!this.modele.identifiantNonExistant()){
                        if(! this.conditionBox.getChildren().contains(this.conditionNonExist)){
                            this.conditionBox.getChildren().add(this.conditionNonExist);
                        }
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionNonExist);
                    }

                    if(!this.modele.identifiantContient8()){
                        if(! this.conditionBox.getChildren().contains(this.conditionIdentifiant)){
                            this.conditionBox.getChildren().add(this.conditionIdentifiant);
                        }
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionIdentifiant);
                    }
                }
                else{
                    this.identifiant.setStyle("-fx-border-color: #3AD365");
                    this.conditionBox.getChildren().removeAll(this.conditionIdentifiant,this.conditionNonExist);
                }
            }
        this.vue.majBoutonCo();
        }
    }
}