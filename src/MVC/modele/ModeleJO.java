package MVC.modele;

import epreuves.*;
import exceptions.*;
import participants.*;
import sports.*;
import comparateurs.*;
import database.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Collections;
import java.util.List;

import MVC.user.User;
import executable.LibCreation;

public class ModeleJO {
    private List<Sport> lesSports;
    private List<Athlete> lesAthletes;
    private List<Equipe> lesEquipes;
    private List<Epreuve<Participant>> lesEpreuves;
    private List<Pays> lesPays;
    private List<User> lesUsers;

    public enum Tris{NATUREL, MEDAILLES, TOTAL}

    private VoleyBall voley;
    private HandBall hand;
    private Escrime escr;
    private Natation nat;
    private Athletisme athle;
    private Requete requete;


    private ConnexionMySql co;

    public ModeleJO(){
        try {
            this.co = new ConnexionMySql();
            this.requete = new Requete(this.co,this);
            
        } catch (Exception e) {
            System.out.println("ca marche passs" + e);
        }
        this.init();
    }

    public void reload(){
        try {
            this.co.close();
            this.co = new ConnexionMySql();
            this.requete = new Requete(this.co,this);
        } catch (Exception e) {
            System.out.println("ca marche passs" + e);
        }
        this.init();
    }

    public void init(){
        this.voley = new VoleyBall();
        this.hand = new HandBall();
        this.escr = new Escrime();
        this.nat = new Natation();
        this.athle = new Athletisme();
        this.lesSports = Arrays.asList(voley,hand,escr,nat,athle);

        try {
            this.lesPays = requete.selectPays();  
        } catch (Exception e) {
            this.lesPays = new ArrayList<>();
        }
        try {
            this.lesUsers = requete.selectUsers();  
        } catch (Exception e) {
            this.lesUsers = new ArrayList<>();
        }
        try {
            this.lesAthletes = requete.selectAthlete();             
        } catch (Exception e) {
            this.lesAthletes = new ArrayList<>();
        }
        try {
            this.lesEquipes = requete.selectEquipe();               
        } catch (Exception e) {
            this.lesEquipes = new ArrayList<>();
        }
        try {
            this.lesEpreuves = requete.selectEpreuves();                
        } catch (Exception e) {
            this.lesEpreuves = new ArrayList<>();
        }
        for (Epreuve<Participant> epreuve : this.lesEpreuves){
            for (int i = 1; i<8; ++i){
                new Manche<Participant>(i, "Manche ", epreuve); 
            }
        }
        // this.creerTest();
    }   

    private void creerTest(){
        try {
            // this.creerPays("France");
            // this.creerAthlete("Mathevet", "Chris", "H", 10, 15, 16, "France");
            // this.creerAthlete("Meunier", "Axel", "H", 10, 15, 16, "France");
            // this.creerAthlete("Marques", "Julian", "H", 10, 15, 16, "France");
            // this.creerAthlete("Richard", "Baptiste", "H", 10, 15, 16, "France");
            // this.creerEpreuve((new Epreuve<Participant>("TestEPREUVE", athle, 'H')));
            // Epreuve<Participant> epreuve = this.requete.getEpreuvebyDescpt("TestEPREUVE");
            // this.inscrireEpreuve(this.getAthlete("Mathevet", "Chris", 'H', "France"), epreuve);
            // this.inscrireEpreuve(this.getAthlete("Meunier", "Axel", 'H', "France"), epreuve);
            // this.inscrireEpreuve(this.getAthlete("Marques", "Julian", 'H', "France"), epreuve);
            // this.inscrireEpreuve(this.getAthlete("Richard", "Baptiste", 'H', "France"), epreuve);
            // Manche<Participant> manche = new Manche<>(1, "tour", epreuve);
            // System.out.println(epreuve.getLesManches());
            // System.out.println(epreuve.getLesParticipants());
            // for (Participant particp : epreuve.getLesParticipants()) {                
            //     this.inscrireEpreuve(particp, epreuve);
            // }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        System.out.println(this.getLesEpreuves());
      
    }

    // Geteurs 

    public List<Athlete> getLesAthletes() {
        return this.lesAthletes;
    }

    public List<Equipe> getLesEquipes() {
        return this.lesEquipes;
    }

    public List<Epreuve<Participant>> getLesEpreuves() {
        return this.lesEpreuves;
    }

    public List<Sport> getLesSports() {
        return this.lesSports;
    }

    public List<Pays> getLesPays() {
        return this.lesPays;
    }
    public List<User> getLesUsers() {
        return this.lesUsers;
    }

    public List<Pays> getLesPays(Tris tri) {
        this.triPays(tri);
        return this.lesPays;
    }

    public Pays getPays(String nom) throws DoesntExistException{   
        for (Pays pays : this.lesPays){
            if(pays.getNomPays().toUpperCase().equals(nom.toUpperCase())){
                return pays;
            }
        }
        throw new DoesntExistException("Ce pays n'existe pas");
    }

    public Equipe getEquipe(String nom) throws DoesntExistException{
        for (Equipe equipe : this.lesEquipes){
            if(equipe.getNom().equals(nom)){
                return equipe;
            }
        }
        throw new DoesntExistException("Cette equipe n'existe pas");
    }

    public Athlete getAthlete(String nom, String prenom, char sexe, String pays) throws DoesntExistException{
        Athlete entree = new Athlete(nom, prenom, sexe, 0, 0, 0, this.getPays(pays));
        for (Athlete athlete : this.lesAthletes){
            if(athlete.equals(entree)){
                return athlete;
            }
        }
        throw new DoesntExistException("Cet athlete n'existe pas");
    }

    public Epreuve<Participant> getEpreuve(String epreuve, char sexe) throws DoesntExistException{
        
        for (Epreuve<Participant> epreuve2: this.lesEpreuves){
            if(epreuve2.getDescription().equals(epreuve) && epreuve2.getSexe() == sexe){
                return epreuve2;
            }
        }
        throw new DoesntExistException("Cette Epreuve n'existe pas");
    }

    public Sport getSport(String nom)throws DoesntExistException{
        switch (nom) {
            case "Athletisme":
                return this.athle;

            case "Escrime":
                return this.escr;

            case "Handball":
                return this.hand;

            case "Voley-Ball":
            case "Voley":
                return this.voley;

            case "Natation":
                return this.nat;  
        
            default:
                throw new DoesntExistException("Ce sport n'existe pas");
        }
    }
    public Requete getRequete() {
        return requete;
    }
    // CSV

    public void csvToListe(String chemin){
        String ligne;
        String split =",";
        Epreuve<Participant> vraiEpreuve;
        Epreuve<Participant> vraiEpreuveEquipe;
        String[] ligneElems;
        Pays pays;
        String nom;
        String prenom;
        char sexe;
        String nomPays;
        String sport;
        String epreuve;
        int force;
        int endurance;
        int agilite;
        Athlete mich;
        String nomEquipe;
        Equipe equipe;
        
        try (BufferedReader line = new BufferedReader(new FileReader(chemin))){
            line.readLine();
            while ((ligne = line.readLine())!= null) {
                ligneElems = ligne.split(split);
                if(ligneElems.length >=9){
                    try {
                        nom= ligneElems[0];
                        prenom= ligneElems[1];
                        sexe= ligneElems[2].charAt(0);
                        nomPays = ligneElems[3];
                        try {
                            pays = getPays(nomPays);
                        } catch (DoesntExistException e) {
                            pays = new Pays(nomPays);
                            this.lesPays.add(pays);
                        }

                        sport = ligneElems[4];
                        epreuve = ligneElems[5];
                        try {
                            force =  Integer.parseInt(ligneElems[6]);
                            endurance = Integer.parseInt(ligneElems[7]);
                            agilite =  Integer.parseInt(ligneElems[8]);
                            mich = new Athlete(nom,prenom,sexe,force,endurance,agilite,pays);
                            try {
                                LibCreation.creerAthlete(this.lesAthletes, mich);
                                try {
                                    vraiEpreuve = this.getEpreuve(epreuve, mich.getSexe());
                                    vraiEpreuve.inscrire(mich);    
                    
                                } catch (DoesntExistException doesntExistException) {
                                    System.err.println("\n" + doesntExistException.getMessage() +"epreuve: "+ (this.getEpreuve(epreuve, mich.getSexe())).getDescription() + "\n");
                                } catch (CanNotRegisterException canNotRegisterException) {
                                    nomEquipe = pays.getNomPays() + epreuve + mich.getSexe();
                                    try {
                                        equipe = this.getEquipe(nomEquipe);
                                    } catch (DoesntExistException e) {
                                        LibCreation.creerEquipe(this.lesEquipes, nomEquipe);
                                        equipe = this.getEquipe(nomEquipe);
                                    }
                                    try {
                                        equipe.ajouter(mich);
                                        vraiEpreuveEquipe = this.getEpreuve(epreuve, equipe.getSexe());
                                        vraiEpreuveEquipe.inscrire(equipe);    
                                    } catch (AlreadyInException e2) {
                                        System.err.println("N'est pas censé s'afficher.");
                                    } catch (NotSameCountryException e2) {
                                        System.err.println("N'est pas censé s'afficher.");
                                    } catch (NotSameGenderException e2) {
                                        System.err.println("N'est pas censé s'afficher.");
                                    } catch (DoesntExistException e2) {
                                        System.err.println("Cette épreuve n'existe pas.");
                                    } catch (CanNotRegisterException e3){
                                        System.err.println(e3.getMessage());
                                    }
                                } catch (AlreadyInException alreadyInException){
                                    System.err.println("\n" + alreadyInException.getMessage() + "\n");
                                }catch (NotSameGenderException notSameGenderException){
                                    System.err.println("\n" + notSameGenderException.getMessage() + "\n");
                                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                                    System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.");
                                } 
                            } catch (AlreadyExistException e) {
                                try {
                                    vraiEpreuve = this.getEpreuve(epreuve, mich.getSexe());
                                    vraiEpreuve.inscrire(mich);    
                    
                                } catch (DoesntExistException doesntExistException) {
                                    System.err.println("\n" + doesntExistException.getMessage() +"epreuve: "+ (this.getEpreuve(epreuve, mich.getSexe())).getDescription() + "\n");
                                } catch (CanNotRegisterException canNotRegisterException) {
                                    nomEquipe = pays.getNomPays() + epreuve + mich.getSexe();
                                    try {
                                        equipe = this.getEquipe(nomEquipe);
                                    } catch (DoesntExistException e2) {
                                        LibCreation.creerEquipe(this.lesEquipes, nomEquipe);
                                        equipe = this.getEquipe(nomEquipe);
                                    }
                                    try {
                                        equipe.ajouter(mich);
                                        vraiEpreuveEquipe = this.getEpreuve(epreuve, equipe.getSexe());
                                        vraiEpreuveEquipe.inscrire(equipe);    
                                    } catch (AlreadyInException e2) {
                                        System.err.println("N'est pas censé s'afficher.");
                                    } catch (NotSameCountryException e2) {
                                        System.err.println("N'est pas censé s'afficher.");
                                    } catch (NotSameGenderException e2) {
                                        System.err.println("N'est pas censé s'afficher.");
                                    } catch (DoesntExistException e2) {
                                        System.err.println("Cette épreuve n'existe pas.");
                                    } catch (CanNotRegisterException e3){
                                        System.err.println(e3.getMessage());
                                    }
                                } catch (AlreadyInException alreadyInException){
                                    System.err.println("\n" + alreadyInException.getMessage() + "\n");
                                }catch (NotSameGenderException notSameGenderException){
                                    System.err.println("\n" + notSameGenderException.getMessage() + "\n");
                                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                                    System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.");
                                } 
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Problème format nombre, ligne : " + ligne);
                        }
                        // si athlete pas creee le cree, sinon l'add a une epreuve
                        // incrire()
                        
                    } catch (Exception e) {
                        System.out.println("erreur format ligne : "+ligne);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();  
        }
    }

    // TRI

    public void triPays(Tris leTri){
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

    // Demandes utilisateurs

    public void creerAthlete(String nom, String prenom, String sexe,int force, int agilite, int endurance, String pays )
    throws AlreadyExistException{
        Athlete athlete;
        try {
            athlete = new Athlete(nom, prenom, sexe.charAt(0), force, agilite, endurance, this.getPays(pays));
        } catch (DoesntExistException e) {
            e.printStackTrace();
            athlete = null;
        }
        if(!this.lesAthletes.contains(athlete)){
            this.lesAthletes.add(athlete);
            try {
                this.requete.insertAthlete(athlete);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new AlreadyExistException("Cet athlete existe déjà");
        }
    }

    public void creerEquipe(String nom)
    throws AlreadyExistException{
        Equipe equipe = new Equipe(nom);
        if(!this.lesEquipes.contains(equipe)){
            this.lesEquipes.add(equipe);
            try {
                this.requete.insertEquipe(equipe);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new AlreadyExistException("Cette equipe existe déjà");
        }
    }

    public void creerPays(String nom)
    throws AlreadyExistException{
        Pays pays = new Pays(nom);
        if(!this.lesPays.contains(pays)){
            this.lesPays.add(pays);
            try {
                this.requete.insertPays(pays);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new AlreadyExistException("Ce pays existe déjà");
        }
    }

    public void creerEpreuve(Epreuve<Participant> uneEpreuve)
    throws AlreadyExistException{
        if(!this.lesEpreuves.contains(uneEpreuve)){
            this.lesEpreuves.add(uneEpreuve);
            try {
                this.requete.insertEpreuve(uneEpreuve);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new AlreadyExistException("Cette Epreuve existe déjà");
        }
    }

    public void ajoutAthleteEquipe(Equipe equipe, Athlete athlete) throws AlreadyInException, NotSameCountryException, NotSameGenderException{
        equipe.ajouter(athlete);
        try {
			this.requete.insertToEquipe(equipe, athlete);
		} catch (SQLException e) {
			e.printStackTrace();
		}        
    }

    public void inscrireEpreuve(Participant participant, Epreuve<Participant> epreuve) throws AlreadyInException, CanNotRegisterException, NotSameGenderException{
        epreuve.inscrire(participant);
        try {
            this.requete.insertParticipantToEpreuve(participant, epreuve);
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    public List<Participant> lesInscrits(Epreuve<Participant> epreuve){
        return epreuve.getLesParticipants();
    }

    public List<Manche<Participant>> lesManches(Epreuve<Participant> epreuve){
        return epreuve.getLesManches();
    }

    public List<Participant> leClassement(Epreuve<Participant> epreuve){
        return epreuve.getLeClassement();
        // try {
        //     this.requete.insertResultat(epreuve);
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // } 
    }

    public void lancerEpreuve(Epreuve<Participant> epreuve){
        epreuve.getLeClassement();
        try {
            this.requete.insertResultat(epreuve);
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    public void lancerToutEpreuves(){
        for (Epreuve< Participant> epreuve : this.lesEpreuves){
            epreuve.getLeClassement();
            try {
                this.requete.insertResultat(epreuve);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }
}