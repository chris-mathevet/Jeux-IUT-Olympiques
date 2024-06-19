package MVC.controleur;


import MVC.vues.AppliJO;
import MVC.vues.Connexion;
import MVC.modele.ModeleConnexion;
import MVC.modele.ModeleJO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ControleurBoutonConnexion implements EventHandler<ActionEvent>{
    
    private AppliJO vue;

    private ModeleConnexion modele;
    
    public ControleurBoutonConnexion(AppliJO vue, ModeleConnexion modele) {
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        int req = 0;
        if(this.modele.getEstConnexion()){
            req = this.modele.connexion();
            if(req == -1){ // Identifiant n'existe pas
                System.err.println("Ce nom d'utilisateur n'existe pas");
            }
            else if(req == -2){  // Problème connexion
                System.err.println("Non trouvé");
            }
            else{ // Connexion réussi
                this.vue.setUtilisateur(this.modele.getIdentifiant());
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
                System.err.println("Ce nom d'utilisateur existe déjà");
            }
            else if(req == -2){ // Mail existant
                System.err.println("Ce mail existe déjà");
            }
            else if (req == -3){ // Problème inscription
                System.err.println("Problème inscription");
            }
            else{ // Connexion réussi
                this.vue.setUtilisateur(this.modele.getIdentifiant());
                try {
                    this.vue.modeAppli();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        
    }
}
