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

    private Pays getPays(String nom) throws DoesntExistException{
        for (Pays pays : this.lesPays){
            if(pays.getNomPays().equals(nom)){
                return pays;
            }
        }
        throw new DoesntExistException("Ce pays n'existe pas");
    }

    private Equipe getEquipe(String nom) throws DoesntExistException{
        for (Equipe equipe : this.lesEquipes){
            if(equipe.getNom().equals(nom)){
                return equipe;
            }
        }
        throw new DoesntExistException("Cette equipe n'existe pas");
    }

    private Athlete getAthlete(String nom, String prenom, char sexe, String pays) throws DoesntExistException{
        Athlete entree = new Athlete(nom, prenom, sexe, 0, 0, 0, getPays(pays));
        for (Athlete athlete : this.lesAthletes){
            if(athlete.equals(entree)){
                return athlete;
            }
        }
        throw new DoesntExistException("Cet athlete n'existe pas");
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
        System.out.println("Vous allez créer un pays\nEntrez un nom de Pays\n(Ecrivez 0000 pour revenir en arrière)");
        String commandePays = System.console().readLine().strip();
        try {
            LibCreation.creerPays(lesPays, commandePays);
            System.out.println("Le pays " + commandePays + " a bien été rajouté.");
        } catch (AlreadyExistException e) {
            System.err.println(e.getMessage());
        }
    }

    public void creerAthelete(){
        boolean condition = true;
        String[] entree;
        
        while (condition) {
            System.out.println("Vous allez créer un Athlete\nEntrez les différents chants dans l'ordre en les espaçant par des \",\" \nNom,prenom,sexe(H/F),force(1-20),agilité(1-20),endurance(1-20),pays\n(Ecrivez 0000 pour revenir en arrière)");
            entree = System.console().readLine().strip().split(",");
            condition = ! (entree[0].equals("0000"));
            if(condition){
                try {
                    LibCreation.creerAthlete(this.lesAthletes,entree[0], entree[1], entree[2].charAt(0), Integer.valueOf(entree[3]), Integer.valueOf(entree[4]), Integer.valueOf(entree[5]),this.getPays(entree[6]));
                    System.out.println("Athlete créé avec succès");
                    condition = false;
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.err.println("Vous n'avez pas rentré assès de valeur, veillez recommencer.");
                } catch (NumberFormatException e) {
                    System.err.println("Valeur incorecte pour les capacités, veillez recommencer.");
                } catch (AlreadyExistException e){
                    System.err.println(e.getMessage());
                    condition = false;
                } catch (DoesntExistException e){
                    System.out.println("Ce pays n'existe pas, voulez vous le créer ? (O/N)");
                    if ((System.console().readLine().strip().toUpperCase()).equals("O")){
                        try {
                            LibCreation.creerPays(this.lesPays, entree[6]);
                            System.out.println("Pays créé avec succès, réessayer de créer l'athlete.");
                        } catch (AlreadyExistException e2) {
                            System.err.println("Ce message n'est pas censé apparaître");
                        }
                    }
                }
            }      
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

    public void ajoutAthleteEquipe(){
        System.out.println("Vous allez ajouter un athlete à une équipe (existante ou non)\nEntrez un nom d'Equipe.");
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