package MVC.modele;

import epreuves.*;
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
    
import executable.LibCreation;
import javax.xml.crypto.AlgorithmMethod;

public class ModeleJO {
    private List<Sport> lesSports;
    private List<Athlete> lesAthletes;
    private List<Equipe> lesEquipes;
    private List<Epreuve<Participant>> lesEpreuves;
    private List<Pays> lesPays;

    public enum Tris{NATUREL, MEDAILLES, TOTAL}

    private VoleyBall voley;
    private HandBall hand;
    private Escrime escr;
    private Natation nat;
    private Athletisme athle;

    public ModeleJO(){
        this.init();
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
        this.lesPays = new ArrayList<>();
        this.lesEpreuves = new ArrayList<>(Arrays.asList(
            new Epreuve<>("Natation 100 brasse",nat,'H'),
            new Epreuve<>("Natation 100 brasse",nat,'F'),
            new Epreuve<>("Natation relais libre",nat,'H'),
            new Epreuve<>("Natation relais libre",nat,'F'),
            new Epreuve<>("Handball",hand,'H'),
            new Epreuve<>("Handball",hand,'F'),
            new Epreuve<>("Volley-Ball",voley,'H'),
            new Epreuve<>("Volley-Ball",voley,'F'),
            new Epreuve<>("Escrime fleuret",escr,'H'),
            new Epreuve<>("Escrime fleuret",escr,'F'),
            new Epreuve<>("Escrime épée",escr,'H'),
            new Epreuve<>("Escrime épée",escr,'F'),
            new Epreuve<>("Athétisme 110 haies",athle,'H'),
            new Epreuve<>("Athétisme 110 haies",athle,'F'),
            new Epreuve<>("Athlétisme relais 400m",athle,'H'),
            new Epreuve<>("Athlétisme relais 400m",athle,'F')
        ));

        for (Epreuve<Participant> epreuve : this.lesEpreuves){
            for (int i = 1; i<8; ++i){
                new Match<Participant>(i, "Manche ", epreuve); 
            }
        }
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

    public Epreuve<? extends Participant> getEpreuve(String epreuve, char sexe) throws DoesntExistException{
        
        for (Epreuve<? extends Participant> epreuve2: this.lesEpreuves){
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

    // CSV

    public void csvToListe(String chemin){
        String ligne;
        String split =",";
        Epreuve<Athlete> vraiEpreuve;
        Epreuve<Equipe> vraiEpreuveEquipe;
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
                                    vraiEpreuve = (Epreuve<Athlete>) this.getEpreuve(epreuve, mich.getSexe());
                                    vraiEpreuve.inscrire(mich);    
                    
                                } catch (DoesntExistException doesntExistException) {
                                    System.err.println("\n" + doesntExistException.getMessage() +"epreuve: "+ ((Epreuve<Athlete>) this.getEpreuve(epreuve, mich.getSexe())).getDescription() + "\n");
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
                                        vraiEpreuveEquipe = (Epreuve<Equipe>) this.getEpreuve(epreuve, equipe.getSexe());
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
                                    vraiEpreuve = (Epreuve<Athlete>) this.getEpreuve(epreuve, mich.getSexe());
                                    vraiEpreuve.inscrire(mich);    
                    
                                } catch (DoesntExistException doesntExistException) {
                                    System.err.println("\n" + doesntExistException.getMessage() +"epreuve: "+ ((Epreuve<Athlete>) this.getEpreuve(epreuve, mich.getSexe())).getDescription() + "\n");
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
                                        vraiEpreuveEquipe = (Epreuve<Equipe>) this.getEpreuve(epreuve, equipe.getSexe());
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

    public void creerAthlete(Athlete athlete)
    throws AlreadyExistException{
        if(!this.lesAthletes.contains(athlete)){
            this.lesAthletes.add(athlete);
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
        }
        else{
            throw new AlreadyExistException("Ce pays existe déjà");
        }
    }

    public void creerEpreuve(Epreuve<Participant> uneEpreuve)
    throws AlreadyExistException{
        if(!this.lesEpreuves.contains(uneEpreuve)){
            this.lesEpreuves.add(uneEpreuve);
        }
        else{
            throw new AlreadyExistException("Cette Epreuve existe déjà");
        }
    }

    public void ajoutAthleteEquipe(Equipe equipe, Athlete athlete) throws AlreadyInException, NotSameCountryException, NotSameGenderException{
        equipe.ajouter(athlete);
    }

    public void inscrireEpreuve(Participant participant, Epreuve<Participant> epreuve) throws AlreadyInException, CanNotRegisterException, NotSameGenderException{
        epreuve.inscrire(participant);
    }

    public List<Participant> lesInscrits(Epreuve<Participant> epreuve){
        return epreuve.getLesParticipants();
    }

    public List<Match<Participant>> lesMatchs(Epreuve<Participant> epreuve){
        return epreuve.getLesMatchs();
    }

    public List<Participant> leClassement(Epreuve<Participant> epreuve){
        return epreuve.getLeClassement();
    }

    public void lancerEpreuve(Epreuve<Participant> epreuve){
        epreuve.getLeClassement();
    }

    public void lancerToutEpreuves(){
        for (Epreuve<? extends Participant> epreuve : this.lesEpreuves){
            epreuve.getLeClassement();
        }
    }
}
