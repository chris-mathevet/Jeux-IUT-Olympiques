package MVC.controleur;

import MVC.vues.AppliJO;
import MVC.modele.ModeleJO;
import epreuves.Epreuve;
import sports.*;

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
            // TODO: handle exception
        }
    }
}
