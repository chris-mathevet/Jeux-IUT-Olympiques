package Executable;

import epreuves.*;
import database.*;
import exceptions.*;
import participants.*;
import sports.*;
import comparateurs.*;

import java.io.BufferedReader;
import java.io.FileReader;
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

    private VoleyBall voley;
    private HandBall hand;
    private Escrime escr;
    private Natation nat;
    private Athletisme athle;

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
        this.voley = new VoleyBall();
        this.hand = new HandBall();
        this.escr = new Escrime();
        this.nat = new Natation();
        this.athle = new Athletisme();

        this.lesSports = Arrays.asList(voley,hand,escr,nat,athle);
        this.lesAthletes = new ArrayList<>();
        this.lesEquipes = new ArrayList<>();
        this.lesEpreuves = Arrays.asList(
            // epreuves = ["Natation 100 brasse", "Natation relais libre", "Handball", "Volley-Ball", "Escrime fleuret", "Escrime épée", "Athétisme 110 haies", "Athlétisme relais 400m"]

            new Epreuve<>("Natation 100 brasse",nat,'H'),
            new Epreuve<>("Natation 100 brasse",nat,'F'),
            new Epreuve<>("Natation relais libre",nat,'H'),
            new Epreuve<>("Natation relais libre",nat,'F'),
            new Epreuve<>("Handball",hand,'H'),
            new Epreuve<>("Handball",hand,'F'),
            new Epreuve<>("Voley-Ball",voley,'H'),
            new Epreuve<>("Voley-Ball",voley,'F'),
            new Epreuve<>("Escrime fleuret",escr,'H'),
            new Epreuve<>("Escrime fleuret",escr,'F'),
            new Epreuve<>("Escrime épée",escr,'H'),
            new Epreuve<>("Escrime épée",escr,'F'),
            new Epreuve<>("Athétisme 110 haies",athle,'H'),
            new Epreuve<>("Athétisme 110 haies",athle,'F'),
            new Epreuve<>("Athlétisme relais 400m",athle,'H'),
            new Epreuve<>("Athlétisme relais 400m",athle,'F')
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


    public void csvToListe(String chemin){
            // List<Athlete> listeAthletes = new ArrayList<>();

            String ligne;
            String split =",";
            Epreuve<Athlete> vraiEpreuve;
            
            try (BufferedReader line = new BufferedReader(new FileReader(chemin))){
                line.readLine();
                while ((ligne = line.readLine())!= null) {
                    
                    // tableau de String => String []
                    String[] ligneElems = ligne.split(split);
                    if(ligneElems.length >=9){
                        try {
                            
                        String nom= ligneElems[0];
                        String prenom= ligneElems[1];
                        char sexe= ligneElems[2].charAt(0);
                        String nomPays = ligneElems[3];
                        Pays pays =  new Pays(nomPays);
                        String sport= ligneElems[4];
                        String epreuve = ligneElems[5];
                        int force=  Integer.parseInt(ligneElems[6]);
                        int endurance = Integer.parseInt(ligneElems[7]);
                        int agilite=  Integer.parseInt(ligneElems[8]);
                        
                        Athlete mich = new Athlete(nom,prenom,sexe,force,endurance,agilite,pays);
                        
                        // si athlete pas creee le cree, sinon l'add a une epreuve
                        // incrire()
                        if(!(this.lesAthletes.contains(mich))){
                            this.lesAthletes.add(mich);
                            addPays(pays);
                            addSport(sport);
                            try {
                                vraiEpreuve = (Epreuve<Athlete>) this.getEpreuve(epreuve);
                                vraiEpreuve.inscrire(mich);    
                            } catch (Exception e) {
                                System.err.println("erreur inscription");
                            }
                            
                        }
                        else{
                            addPays(pays);
                            addSport(sport);
                            try {
                                vraiEpreuve = (Epreuve<Athlete>) this.getEpreuve(epreuve);
                                vraiEpreuve.inscrire(mich);    
                            } catch (Exception e) {
                                System.err.println("erreur inscription");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("erreur format ligne : "+ligne);
                    }
                    
                    }
                    
                }
            }catch (Exception e) {
                e.printStackTrace();  
            }
    }
    public boolean addPays(Pays p){
        if(!(this.lesPays.contains(p))){
            this.lesPays.add(p);   
            return true; // ajout du pays a la liste ? oui => true
        }
        return false; // ajout du pays a la liste ? non => false
    }
    
    public boolean addSport(String s){

        if (s.equals(this.athle.getSport()) 
            || s.equals(this.athle.getSport())
            || s.equals(this.escr.getSport()) 
            || s.equals(this.hand.getSport())
            || s.equals(this.voley.getSport())
            || s.equals(this.nat.getSport())
            ){

                System.out.println("Sport existe deja");
            
            
        }
        switch (s) {
            case "Athletisme":
                this.athle = new Athletisme();
                break;

            case "Escrime":
                this.escr = new Escrime();
                break;

            case "Handball":
                this.hand = new HandBall();
                break;

            case "Voley-Ball":
            case "Voley":
                this.voley = new VoleyBall();
                break;

            case "Natation":
                this.nat = new Natation();  
                break;
        
            default:
            System.err.println("Erreur lors de la creation du sport");
                break;
        }
        return true;
    }

    private Epreuve<? extends Participant> getEpreuve(String epreuve){
        
        for (Epreuve<? extends Participant> epreuve2: this.lesEpreuves){
            if(epreuve2.getDescription().equals(epreuve)){
                return epreuve2;
            }
        }
        return null;
    }
}