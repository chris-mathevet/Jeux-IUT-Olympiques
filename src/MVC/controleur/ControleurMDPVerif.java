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

public class ControleurMDPVerif implements ChangeListener<String>{
    ModeleConnexion modele;
    TextField motDePasseVerif;
    VBox conditionBox;
    AppliJO vue;

    HBox conditionVerif;

    public ControleurMDPVerif(ModeleConnexion modele, AppliJO vue, TextField mdp, VBox condition){
        this.modele = modele;
        this.motDePasseVerif = mdp;
        this.conditionBox = condition;
        this.vue = vue;

        this.conditionVerif = new HBox();

        this.conditionVerif.setSpacing(4);

        Text conditionVerifText = new Text("Les mots de passes sont différents");

        ImageView imageErreur = new ImageView("erreur.png");

        conditionVerifText.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());        

        conditionVerifText.setFill(Color.web("#EB5252"));        

        conditionVerifText.setFont(new Font("System", 8));    
        
        this.conditionVerif.getChildren().addAll(imageErreur, conditionVerifText);
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){ 
        if (! newValue.equals(oldValue)) {
            this.conditionBox.getChildren().remove(this.conditionVerif);
            this.modele.setMdpVerif(this.motDePasseVerif.getText());
            if (this.modele.getEstConnexion()){

            }
            else{
                if(! this.modele.mdpVerif()){
                    this.motDePasseVerif.setStyle("-fx-border-color: #EB5252");
                    if(!this.modele.mdpContient8()){
                        if(! this.conditionBox.getChildren().contains(this.conditionVerif)){
                            this.conditionBox.getChildren().add(this.conditionVerif);
                        }
                    }
                }
                else{
                    this.motDePasseVerif.setStyle("-fx-border-color: #3AD365");
                }
            }
        this.vue.majBoutonCo();
        }
    }
}
