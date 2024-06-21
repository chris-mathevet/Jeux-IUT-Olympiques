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
import participants.Participant;

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
        Sport sport;
        String desc = this.vue.getStringDescription();

        if (combo1 != null && combo2 != null  & !(desc.equals(""))) {
            if (combo1.equals("Homme")) {
                sexe = 'M';
            }
            
            switch (combo2) {
                case "VolleyBall":
                    sport = new VoleyBall();
                    break;

                case "HandBall":
                    sport = new HandBall();
                    break;

                case "Natation":
                    sport = new Natation();
                    break;

                case "Escrime":
                    sport = new Escrime();
                    break;
            
                default:
                    sport = new Athletisme();
                    break;
            }

            try {
                if (sport != null) {
                    this.modele.creerEpreuve(new Epreuve<Participant>(desc, sport, sexe));
                    this.vue.majEpreuve();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("Veuillez sélectionner un sport et un sexe");
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
                if (this.modele.getLesPays().contains(new Pays(paysAthlete))) {
                    try {
                        this.modele.creerAthlete(nomAthlete, prenomAthlete,comboSexeAthlete, forceAthlete, enduranceAthlete, agiliteAthlete, paysAthlete);
                        this.vue.updateAthlete();
                    } catch (AlreadyExistException e) {
                        System.err.println(e.getMessage());
                    }
                }
                else {
                    System.out.println("Veuillez selectionner ou créer un pays existant");
                }

                break;

                case "boutonAjouterParticipants2":
                
                String nomEquipe = this.vue.getStringNomEquipe();
                String paysEquipe = this.vue.getStringPaysEquipe();
                String comboSexeEquipe = (String) this.vue.getComboSexeEquipe().getValue();

                if (this.modele.getLesPays().contains(new Pays(paysEquipe))) {
                    try {
                        this.modele.creerEquipe(nomEquipe);
                        this.vue.updateEquipe();
                    } catch (AlreadyExistException e) {
                        System.err.println(e.getMessage());
                    }
                }
                else {
                    System.out.println("Veuillez selectionner ou créer un pays existant");
                }

                
               
                break;
        }
    }
}
