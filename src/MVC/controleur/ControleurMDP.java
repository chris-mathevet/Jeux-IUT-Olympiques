package MVC.controleur;

import MVC.modele.ModeleConnexion;
import MVC.vues.AppliJO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControleurMDP implements ChangeListener<String>{
    ModeleConnexion modele;
    TextField motDePasse;
    VBox conditionBox;
    AppliJO vue;

    HBox conditionNB;
    HBox conditionMajMin;
    HBox conditionChifre;
    HBox conditionSpecial;


    public ControleurMDP(ModeleConnexion modele, AppliJO vue, TextField mdp, VBox condition){
        this.modele = modele;
        this.motDePasse = mdp;
        this.vue = vue;
        if(! this.modele.getEstConnexion()){
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
    }

    
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){ 
        if (!newValue.equals(oldValue)) { 
            this.modele.setMdp(this.motDePasse.getText());
            if (this.modele.getEstConnexion()){
                
            }
            else{
                this.conditionBox.getChildren().removeAll(this.conditionChifre,this.conditionMajMin,this.conditionNB,this.conditionSpecial);
                if(! this.modele.mdpCorecteInscription()){
                    this.motDePasse.setStyle("-fx-border-color: #EB5252");
                    Node erreur = null;
                    if(!this.modele.mdpContientSpecial()){
                        if(! this.conditionBox.getChildren().contains(this.conditionSpecial)){
                            erreur = this.conditionSpecial;
                        }
                    }  
                    if(!this.modele.mdpContientChifre()){
                        if(! this.conditionBox.getChildren().contains(this.conditionChifre)){
                            erreur = this.conditionChifre;
                        }
                    }
                    if((!(this.modele.mdpContientMaj()) || !(this.modele.mdpContientMin()))){
                        if(! this.conditionBox.getChildren().contains(this.conditionMajMin)){
                            erreur = this.conditionMajMin;
                        }
                    }
                    if(!this.modele.mdpContient8()){
                        if(! this.conditionBox.getChildren().contains(this.conditionNB)){
                            erreur = this.conditionNB;
                        }
                    }
                    this.conditionBox.getChildren().add(erreur);
                }
                else{
                    this.motDePasse.setStyle("-fx-border-color: #3AD365");
                }
            }
        }
        this.vue.majBoutonCo();
    }
}
