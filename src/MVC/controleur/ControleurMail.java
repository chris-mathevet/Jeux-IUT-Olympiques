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

public class ControleurMail implements ChangeListener<Boolean>{
    ModeleConnexion modele;
    TextField mail;
    VBox conditionBox;
    AppliJO vue;

    HBox conditionNonExist;
    HBox conditionMail;

    public ControleurMail(ModeleConnexion modele, AppliJO vue, TextField mdp, VBox condition){
        this.modele = modele;
        this.mail = mdp;
        this.conditionBox = condition;
        this.vue = vue;

        this.conditionMail = new HBox();
        this.conditionNonExist = new HBox();

        this.conditionMail.setSpacing(4);

        Text conditionMailText = new Text("Veillez entrer un mail correcte");
        Text conditionNonExistText = new Text("Ce mail est déjà attribué");

        ImageView imageErreur = new ImageView("erreur.png");

        conditionMailText.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());        
        conditionMailText.setFill(Color.web("#EB5252"));        
        conditionMailText.setFont(new Font("System", 8));    

        conditionNonExistText.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());        
        conditionNonExistText.setFill(Color.web("#EB5252"));        
        conditionNonExistText.setFont(new Font("System", 8));    
        
        this.conditionNonExist.getChildren().addAll(new ImageView("erreur.png"), conditionNonExistText);
        this.conditionMail.getChildren().addAll(new ImageView("erreur.png"), conditionMailText);
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){ 
        if (!newValue) { // si on a perdu le focus
            this.modele.setMail(this.mail.getText());
            if (this.modele.getEstConnexion()){

            }
            else{
                if(! this.modele.mailCorecte()){
                    this.mail.setStyle("-fx-border-color: #EB5252");
                    if(!this.modele.mailNonExistant()){
                        if(! this.conditionBox.getChildren().contains(this.conditionNonExist)){
                            this.conditionBox.getChildren().add(this.conditionNonExist);
                        }
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionNonExist);
                    }

                    if(!this.modele.mailVerif()){
                        if(! this.conditionBox.getChildren().contains(this.conditionMail)){
                            this.conditionBox.getChildren().add(this.conditionMail);
                        }
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionMail);
                    }
                }
                else{
                    this.mail.setStyle("-fx-border-color: #3AD365");
                    this.conditionBox.getChildren().removeAll(this.conditionMail,this.conditionNonExist);
                }
            }
        this.vue.majBoutonCo();
        }
    }
}
