package Executable;

import epreuves.*;
import database.*;
import exceptions.*;
import participants.*;
import sports.*;
import comparateurs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JO {
    private List<Sport> lesSports;
    private List<Athlete> lesAthletes;
    private List<Equipe> lesEquipes;
    private List<Epreuve<? extends Participant>> lesEpreuves;
    private List<Pays> lesPays;

    private enum tris{NATUREL, MEDAILLES, TOTAL}

    private void triPays(tris leTri){
        switch (leTri) {
            case MEDAILLES:
                Collections.sort(lesPays, new ComparateurMedailles());
                break;
            
            case TOTAL:
                Collections.sort(lesPays, new ComparateurTotal());
                break;
        
            default:
                Collections.sort(lesPays);
                break;
        }
    }

    private Pays getPays(String nom){
        for (Pays pays : this.lesPays){
            if(pays.getNomPays().equals(nom)){
                return pays;
            }
        }
        return null;
    }

    private Equipe getEquipe(String nom){
        for (Equipe equipe : this.lesEquipes){
            if(equipe.getNom().equals(nom)){
                return equipe;
            }
        }
        return null;
    }

    public void init(){
        VoleyBall voley = new VoleyBall();
        HandBall hand = new HandBall();
        Escrime escr = new Escrime();
        Natation nat = new Natation();
        Athletisme athle = new Athletisme();

        this.lesSports = Arrays.asList(voley,hand,escr,nat,athle);
        this.lesAthletes = new ArrayList<>();
        this.lesEquipes = new ArrayList<>();
        this.lesEpreuves = Arrays.asList(
            new Epreuve<>("100m brasse",nat,'H'),
            new Epreuve<>("100m brasse",nat,'F'),
            new Epreuve<>("4x100m nage libre",nat,'H'),
            new Epreuve<>("4x100m nage libre",nat,'F'),
            new Epreuve<>("Handball",hand,'H'),
            new Epreuve<>("Handball",hand,'F'),
            new Epreuve<>("Voley-Ball",voley,'H'),
            new Epreuve<>("Voley-Ball",voley,'F'),
            new Epreuve<>("Fleuret",escr,'H'),
            new Epreuve<>("Fleuret",escr,'F'),
            new Epreuve<>("Epée",escr,'H'),
            new Epreuve<>("Epée",escr,'F'),
            new Epreuve<>("110m haies",athle,'H'),
            new Epreuve<>("110m haies",athle,'F'),
            new Epreuve<>("4x110m haies",athle,'H'),
            new Epreuve<>("4x110m haies",athle,'F')
        );
        this.lesPays = new ArrayList<>();
        // Import de la BD
    }   

    public void creerPays(){
        System.out.println("Vous allez créer un pays\nEntrez un nom de Pays.");
        String commandePays = System.console().readLine();
        commandePays = commandePays.strip();
        System.out.println("Voulez vous vraiment créer le pays : " + commandePays + " (Y/N)");
        String commandeVerif = System.console().readLine();
        commandeVerif = commandeVerif.strip().toUpperCase();
        if(commandeVerif.equals("Y")){
            Pays pays = new Pays(commandePays);
            if( ! (this.lesPays.contains(pays))){
                this.lesPays.add(pays);
                System.out.println("Le pays " + commandePays + " a bien été rajouté.");
            }
            else{
                System.err.println("Ce pays existe déjà.");
            }
        }
        else{
            System.out.println("Le pays n'a pas été rajouté.");
        }
    }

    public void creerAthelete(){
        String nom;
        String prenom;
        char sexe;
        int force;
        int agilite;
        int endurance;
        Pays pays;

        System.out.println("Vous allez créer un Athlete\nEntrez un nom d'Athlete.");
        nom = System.console().readLine().strip();

        System.out.println("Entrez un prénom d'Athlete.");
        prenom = System.console().readLine().strip();

        System.out.println("Entrez son sexe.");
        sexe = System.console().readLine().toUpperCase().charAt(0);

        try {
            System.out.println("Entrez sa force.");
            force = Integer.valueOf(System.console().readLine().strip());

            System.out.println("Entrez son endurance.");
            endurance = Integer.valueOf(System.console().readLine().strip());

            System.out.println("Entrez son agilité.");
            agilite = Integer.valueOf(System.console().readLine().strip());

            System.out.println("Entrez son Pays.");
            pays = this.getPays(System.console().readLine().strip());

            if (pays == null){
                System.err.println("Ce pays n'existe pas, annulation.");
            }
            else{
                Athlete athlete = new Athlete(nom, prenom, sexe, force, endurance, agilite, pays);
                System.out.println("Voulez vous vraiment créer l'Athlete :\n" + athlete +"\n(Y/N)");
                String commandeVerif = System.console().readLine().strip().toUpperCase();
    
                if(commandeVerif.equals("Y")){
                    this.lesAthletes.add(athlete);
                }
                else{
                    System.out.println("L'Athlete n'a pas été rajouté.");
                }
            }            
        } catch (NumberFormatException e) {
            System.err.println("Vous n'avez pas entrer un nombre, annulation");
        }
    }

    public void creerEquipe(){
        System.out.println("Vous allez créer une Equipe\nEntrez un nom d'Equipe.");
        String nom = System.console().readLine().strip();
        System.out.println("Voulez vous créer l'équipe : " + nom + " (Y/N)");
        String commandeVerif = System.console().readLine();
        commandeVerif = commandeVerif.strip().toUpperCase();
        if(commandeVerif.equals("Y")){
            Equipe equipe = new Equipe(nom);
            if( ! (this.lesEquipes.contains(equipe))){
                this.lesEquipes.add(equipe);
                System.out.println("L'équipe " + nom + " a bien été rajouté.");
            }
            else{
                System.err.println("Cette équipe existe déjà.");
            }
        }
        else{
            System.out.println("L'équipe n'a pas été rajouté.");
        }
    }


}
