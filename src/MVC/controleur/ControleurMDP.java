package MVC.controleur;

import MVC.modele.ModeleConnexion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ControleurMDP implements ChangeListener<Boolean>{
    ModeleConnexion modele;
    TextField motDePasse;
    VBox conditionBox;
    Text conditionNB;
    Text conditionMajMin;
    Text conditionChifre;
    Text conditionSpecial;


    public ControleurMDP(ModeleConnexion modele, TextField mdp, VBox condition){
        this.modele = modele;
        this.motDePasse = mdp;
        this.conditionBox = condition;

        this.conditionNB = new Text("Au moins 8 caractères");
        this.conditionMajMin = new Text("Au moins 1 lettre majuscule et minuscule");
        this.conditionChifre = new Text("Au moins 1 chiffre");
        this.conditionSpecial = new Text("Au moins 1 caractère spécial (&,@...)");

        this.conditionNB.setFill(Color.web("#EB5252"));        
        this.conditionMajMin.setFill(Color.web("#EB5252"));        
        this.conditionChifre.setFill(Color.web("#EB5252"));        
        this.conditionSpecial.setFill(Color.web("#EB5252"));        
    }

    
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
    { 
        if (!newValue) { // si on a perdu le focus
            this.modele.setMdp(this.motDePasse.getText());
            if (this.modele.getEstConnexion()){

            }
            else{
                if(! this.modele.mdpCorecteInscription()){
                    this.motDePasse.setStyle("-fx-border-color: #EB5252");
                    if(!this.modele.mdpContient8()){
                        this.conditionBox.getChildren().add(this.conditionNB);
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionNB);
                    }

                    if(!this.modele.mdpContientChifre()){
                        this.conditionBox.getChildren().add(this.conditionChifre);
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionChifre);
                    }

                    if(!(this.modele.mdpContientMaj()) || !(this.modele.mdpContientMin())){
                        this.conditionBox.getChildren().add(this.conditionMajMin);
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionMajMin);
                    }

                    if(!this.modele.mdpContientSpecial()){
                        this.conditionBox.getChildren().add(this.conditionSpecial);
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionSpecial);
                    }
                }
                else{
                    this.motDePasse.setStyle("-fx-border-color: #3AD365");
                    this.conditionBox.getChildren().removeAll(this.conditionChifre,this.conditionMajMin,this.conditionNB,this.conditionSpecial);
                }
            }
        }
    }
}
