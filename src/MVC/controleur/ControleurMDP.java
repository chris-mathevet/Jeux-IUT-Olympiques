package MVC.controleur;

import MVC.modele.ModeleConnexion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControleurMDP implements ChangeListener<Boolean>{
    ModeleConnexion modele;
    TextField motDePasse;
    VBox conditionBox;

    HBox conditionNB;
    HBox conditionMajMin;
    HBox conditionChifre;
    HBox conditionSpecial;


    public ControleurMDP(ModeleConnexion modele, TextField mdp, VBox condition){
        this.modele = modele;
        this.motDePasse = mdp;
        this.conditionBox = condition;

        this.conditionChifre = new HBox();
        this.conditionNB = new HBox();
        this.conditionMajMin = new HBox();
        this.conditionSpecial = new HBox();

        this.conditionChifre.setSpacing(4);
        this.conditionNB.setSpacing(4);
        this.conditionMajMin.setSpacing(4);
        this.conditionSpecial.setSpacing(4);


        Text conditionNBTxt = new Text("Au moins 8 caractères");
        Text conditionMajMinTxt = new Text("Au moins 1 lettre majuscule et minuscule");
        Text conditionChifreTxt = new Text("Au moins 1 chiffre");
        Text conditionSpecialTxt = new Text("Au moins 1 caractère spécial (&,@...)");

        ImageView imageErreur = new ImageView("erreur.png");

        conditionNBTxt.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());        
        conditionMajMinTxt.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());     
        conditionChifreTxt.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());      
        conditionSpecialTxt.setWrappingWidth(conditionBox.getMaxWidth() - imageErreur.getFitWidth() - conditionBox.getSpacing());      

        conditionNBTxt.setFill(Color.web("#EB5252"));        
        conditionMajMinTxt.setFill(Color.web("#EB5252"));        
        conditionChifreTxt.setFill(Color.web("#EB5252"));        
        conditionSpecialTxt.setFill(Color.web("#EB5252"));

        Font font = new Font("System", 8);
        conditionNBTxt.setFont(font);    
        conditionMajMinTxt.setFont(font);     
        conditionChifreTxt.setFont(font);       
        conditionSpecialTxt.setFont(font);

        
        this.conditionChifre.getChildren().addAll(new ImageView("erreur.png"), conditionChifreTxt);
        this.conditionNB.getChildren().addAll(new ImageView("erreur.png"), conditionNBTxt);
        this.conditionMajMin.getChildren().addAll(new ImageView("erreur.png"), conditionMajMinTxt);
        this.conditionSpecial.getChildren().addAll(new ImageView("erreur.png"), conditionSpecialTxt);

    }

    
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){ 
        if (!newValue) { // si on a perdu le focus
            this.modele.setMdp(this.motDePasse.getText());
            if (this.modele.getEstConnexion()){

            }
            else{
                if(! this.modele.mdpCorecteInscription()){
                    this.motDePasse.setStyle("-fx-border-color: #EB5252");
                    if(!this.modele.mdpContient8()){
                        if(! this.conditionBox.getChildren().contains(this.conditionNB)){
                            this.conditionBox.getChildren().add(this.conditionNB);
                        }
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionNB);
                    }

                    if(!this.modele.mdpContientChifre()){
                        if(! this.conditionBox.getChildren().contains(this.conditionChifre)){
                            this.conditionBox.getChildren().add(this.conditionChifre);
                        }
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionChifre);
                    }

                    if((!(this.modele.mdpContientMaj()) || !(this.modele.mdpContientMin()))){
                        if(! this.conditionBox.getChildren().contains(this.conditionMajMin)){
                            this.conditionBox.getChildren().add(this.conditionMajMin);
                        }
                    }
                    else{
                        this.conditionBox.getChildren().remove(this.conditionMajMin);
                    }

                    if(!this.modele.mdpContientSpecial()){
                        if(! this.conditionBox.getChildren().contains(this.conditionSpecial)){
                            this.conditionBox.getChildren().add(this.conditionSpecial);
                        }
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
