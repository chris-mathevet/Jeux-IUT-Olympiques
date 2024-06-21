package MVC.controleur;

import MVC.vues.AppliJO;
import MVC.modele.ModeleJO;
import epreuves.Epreuve;
import sports.*;

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
    }
}
