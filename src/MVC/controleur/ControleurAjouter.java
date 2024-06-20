package MVC.controleur;

import MVC.vues.AppliJO;
import MVC.modele.ModeleJO;
import epreuves.Epreuve;
import sports.*;
import participants.*;
import exceptions.*;

import javafx.scene.control.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurAjouter implements EventHandler<ActionEvent> {

    private AppliJO vue;
    private ModeleJO modele;

    public ControleurAjouter(AppliJO vue, ModeleJO modele) {
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        Button current = (Button) actionEvent.getSource();
        String id = current.getId();

        switch (id) {
            case "boutonAjouter":
                String combo1 = (String) this.vue.getComboSexe().getValue();
        String combo2 = (String) this.vue.getComboSport().getValue();
        char sexe = 'F';
        Sport sport = null;
        Epreuve ep = null;
        String desc = this.vue.getStringDescription();
        System.out.println(combo2);

        if (combo1 != null && combo2 != null) {
            if (combo1.equals("Male")) {
                sexe = 'M';
            }

            if (combo2.equals("VolleyBall")) {
                sport = new VoleyBall();
                System.out.println(true);
            } else if (combo2.equals("HandBall")) {
                sport = new HandBall();
            } else if (combo2.equals("Natation")) {
                sport = new Natation();
            } else if (combo2.equals("Escrime")) {
                sport = new Escrime();
            } else if (combo2.equals("Athletisme")) {
                sport = new Athletisme();
            }

            try {
                if (sport != null) {
                    ep = new Epreuve(desc, sport, sexe);
                    this.modele.creerEpreuve(ep);
                } else {
                    System.out.println("Veuillez sélectionner un sport valide");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("Veuillez sélectionner un sport et un sexe");
        }
        try {
            this.vue.majEpreuve(this.modele.getLesEpreuves());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
                break;
        
            case "boutonAjouterParticipants":
            
                String nomAthlete = this.vue.getStringNomAthlete();
                String prenomAthlete = this.vue.getStringPrenomAthlete();
                String comboSexeAthlete = (String) this.vue.getComboSexeAthlete().getValue();
                int forceAthlete = this.vue.getIntForceAthlete();
                int enduranceAthlete = this.vue.getIntEnduranceAthlete();
                int agiliteAthlete = this.vue.getIntAgiliteAthlete();
                String paysAthlete = this.vue.getStringPaysAthlete();
                System.out.println(paysAthlete);
                System.out.println(this.modele.getLesAthletes());
                if (this.modele.getLesPays().contains(new Pays(paysAthlete))) {
                    try {
                        this.modele.creerAthlete(nomAthlete, prenomAthlete,comboSexeAthlete, forceAthlete, enduranceAthlete, agiliteAthlete, paysAthlete);
                    } catch (AlreadyExistException e) {
                        System.err.println(e.getMessage());
                    }
                }
                else {
                    System.out.println("Veuillez selectionner ou créer un pays existant");
                }

                
                System.out.println(this.modele.getLesAthletes());
                break;
        }
        
        
    }
}
