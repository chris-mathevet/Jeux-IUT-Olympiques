package executable;

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

import javax.xml.crypto.AlgorithmMethod;

public class JO {
    private List<Sport> lesSports;
    private List<Athlete> lesAthletes;
    private List<Equipe> lesEquipes;
    private List<Epreuve<Participant>> lesEpreuves;
    private List<Pays> lesPays;
    private List<Participant> lesParticipants;

    public enum Tris{NATUREL, MEDAILLES, TOTAL}

    private VoleyBall voley;
    private HandBall hand;
    private Escrime escr;
    private Natation nat;
    private Athletisme athle;

    public JO(){
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
            // epreuves = ["Natation 100 brasse", "Natation relais libre", "Handball", "Volley-Ball", "Escrime fleuret", "Escrime épée", "Athétisme 110 haies", "Athlétisme relais 400m"]

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
                new Match<Participant>(i, "Tour ", epreuve); 
            }
        }
        
        for (Epreuve<Participant> epreuve : this.lesEpreuves){
            System.out.println(epreuve);
            for (Match<Participant> match : epreuve.getLesMatchs()){
                System.out.println(match);
            }
            System.out.println();
        }
    }   

    // Geteurs 

    public List<Athlete> getLesAthletes() {
        return lesAthletes;
    }

    public List<Equipe> getLesEquipes() {
        return lesEquipes;
    }

    public List<Epreuve<Participant>> getLesEpreuves() {
        return this.lesEpreuves;
    }

    public List<Sport> getLesSports() {
        return lesSports;
    }

    public List<Pays> getLesPays() {
        return lesPays;
    }

    private Pays getPays(String nom) throws DoesntExistException{   
        for (Pays pays : this.lesPays){
            if(pays.getNomPays().toUpperCase().equals(nom.toUpperCase())){
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
        Athlete entree = new Athlete(nom, prenom, sexe, 0, 0, 0, this.getPays(pays));
        for (Athlete athlete : this.lesAthletes){
            if(athlete.equals(entree)){
                return athlete;
            }
        }
        throw new DoesntExistException("Cet athlete n'existe pas");
    }

    private Epreuve<? extends Participant> getEpreuve(String epreuve, char sexe) throws DoesntExistException{
        
        for (Epreuve<? extends Participant> epreuve2: this.lesEpreuves){
            if(epreuve2.getDescription().equals(epreuve) && epreuve2.getSexe() == sexe){
                return epreuve2;
            }
        }
        throw new DoesntExistException("Cette Epreuve n'existe pas");
    }

    public Sport getSport(String nom)throws DoesntExistException{
        // Sport entree = new Sport<>();

        for (Sport unSport : this.lesSports){
            if(unSport.getSport().equals(nom)){
                return unSport;
            }
        }
        throw new DoesntExistException("Cette equipe n'existe pas");
    }

    // CSV

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
                            vraiEpreuve = (Epreuve<Athlete>) this.getEpreuve(epreuve, mich.getSexe());
                            vraiEpreuve.inscrire(mich);    
               
                        } catch (DoesntExistException doesntExistException) {
                            System.err.println("\n" + doesntExistException.getMessage() +"epreuve: "+ ((Epreuve<Athlete>) this.getEpreuve(epreuve, mich.getSexe())).getDescription() + "\n");
                        } catch (CanNotRegisterException canNotRegisterException) {
                            System.err.println("\n" + canNotRegisterException.getMessage() + "\n");
                        } catch (AlreadyInException alreadyInException){
                            System.err.println("\n" + alreadyInException.getMessage() + "\n");
                        }catch (NotSameGenderException notSameGenderException){
                            System.err.println("\n" + notSameGenderException.getMessage() + "\n");
                        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                            System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.");
                        } 
                        
                    }
                    else{
                        addPays(pays);
                        addSport(sport);
                        try {
                            vraiEpreuve = (Epreuve<Athlete>) this.getEpreuve(epreuve, mich.getSexe());
                            vraiEpreuve.inscrire(mich);    
               
                        } catch (DoesntExistException doesntExistException) {
                            System.err.println("\n" + doesntExistException.getMessage() +"epreuve: "+ ((Epreuve<Athlete>) this.getEpreuve(epreuve, mich.getSexe())).getDescription() + "\n");
                        } catch (CanNotRegisterException canNotRegisterException) {
                            System.err.println("\n" + canNotRegisterException.getMessage() + "\n");
                        } catch (AlreadyInException alreadyInException){
                            System.err.println("\n" + alreadyInException.getMessage() + "\n");
                        }catch (NotSameGenderException notSameGenderException){
                            System.err.println("\n" + notSameGenderException.getMessage() + "\n");
                        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                            System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.");
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


    // Ajouts

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

    public void creerPays(){
        boolean condition = true;
        String commandePays;
        while (condition) {
            System.out.println("Vous allez créer un pays\nEntrez un nom de Pays\n(Ecrivez 0000 pour revenir en arrière)");
            commandePays = System.console().readLine().strip();
            condition = ! (commandePays.equals("0000"));
            if(condition){
                try {
                    System.out.println("\nEtes vous sur de vouloir créer le pays: " + commandePays + " ? (O/N)");
                    if(System.console().readLine().strip().toUpperCase().equals("O")){
                        LibCreation.creerPays(lesPays, commandePays);
                        System.out.println("\nPays ajouté avec succès.\n");
                    }
                    else{System.out.println("\nAnnulation de la création.\n");}
                    condition = false;
                } catch (AlreadyExistException e) {
                    System.err.println("\n" + e.getMessage() + "\n");
                }
            }
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
                    Athlete athlete = new Athlete(entree[0], entree[1], entree[2].charAt(0), Integer.valueOf(entree[3]), Integer.valueOf(entree[4]), Integer.valueOf(entree[5]),this.getPays(entree[6]));
                    System.out.println("\nEtes vous sur de vouloir créer cet athlete ? (O/N)\n" + athlete);
                    if(System.console().readLine().strip().toUpperCase().equals("O")){
                        LibCreation.creerAthlete(this.lesAthletes,athlete);
                        System.out.println("\nAthlete créé avec succès\n");
                    }
                    else{System.out.println("\nAnnulation de la création.\n");}                    
                    condition = false;
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.\n");
                } catch (NumberFormatException e) {
                    System.err.println("\nValeur incorecte pour les capacités, veillez recommencer.\n");
                } catch (AlreadyExistException e){
                    System.err.println("\n" + e.getMessage() + "\n");
                    condition = false;
                } catch (DoesntExistException e){
                    System.out.println("\nCe pays n'existe pas, voulez vous le créer ? (O/N)");
                    if ((System.console().readLine().strip().toUpperCase()).equals("O")){
                        try {
                            LibCreation.creerPays(this.lesPays, entree[6]);
                            System.out.println("\nPays créé avec succès, réessayer de créer l'athlete.\n");
                        } catch (AlreadyExistException e2) {
                            System.err.println("Ce message n'est pas censé apparaître");
                        }
                    }
                }
            }      
        }
    }

    public void creerEquipe(){
        boolean condition = true;
        String nom;
        while (condition) {
            System.out.println("Vous allez créer une Equipe\nEntrez un nom d'Equipe.\n(Ecrivez 0000 pour revenir en arrière)");
            nom = System.console().readLine().strip();
            condition = ! (nom.equals("0000"));
            if(condition){
                try {
                    System.out.println("Etes vous sur de vouloir créer l'équipe : " + nom + " (O/N)");
                    if(System.console().readLine().strip().toUpperCase().equals("O")){
                        LibCreation.creerEquipe(this.lesEquipes, nom);
                        System.out.println("\nEquipe créé avec succès\n");
                    }
                    else{
                        System.out.println("\nAnnulation de la création.\n");
                    }
                    condition = false;
                } catch (AlreadyExistException e) {
                    System.err.println("\n" + e.getMessage() + "\n");
                }
            }            
        }
    }

    public void ajoutAthleteEquipe(){
        boolean condition = true;
        boolean condition2 = true;
        String nomEquipe = ""; 
        Equipe equipe;
        String[] entreeAthlete;
        while (condition) {
            try {
                System.out.println("Vous allez ajouter un athlete à une équipe\nEntrez un nom d'Equipe.\n(Ecrivez 0000 pour revenir en arrière)");
                nomEquipe = System.console().readLine().strip();
                condition = ! (nomEquipe.equals("0000"));
                if(condition){
                    equipe = this.getEquipe(nomEquipe);
                    while (condition2) {
                        System.out.println("\nVous avez sélectionnez l'équipe " + nomEquipe +"\nEntrez les différents chants dans l'ordre en les espaçant par des \",\" \n" + 
                                                    "Nom,prenom,sexe(H/F),pays\n" +
                                                    "(Ecrivez 0000 pour revenir en arrière)");
                        entreeAthlete = System.console().readLine().strip().split(",");
                        condition2 = ! (entreeAthlete[0].equals("0000"));
                        if (condition2){
                            try {
                                equipe.ajouter(this.getAthlete(entreeAthlete[0], entreeAthlete[1], entreeAthlete[2].charAt(0), entreeAthlete[3]));
                                System.out.println("\nAthlete ajouté à l'équipe avec succès, continuer ? (O/N)");
                                condition2 = System.console().readLine().strip().toUpperCase().equals("O");
                            } catch (DoesntExistException doesntExistException) {
                                System.err.println("\n" + doesntExistException.getMessage() + "\n");
                            } catch (AlreadyInException alreadyInException){
                                System.err.println("\n" + alreadyInException.getMessage() + "\n");
                            } catch (NotSameCountryException notSameCountryException){
                                System.err.println("\n" + notSameCountryException.getMessage() + "\n");
                            } catch (NotSameGenderException notSameGenderException){
                                System.err.println("\n" + notSameGenderException.getMessage() + "\n");
                            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                                System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.");
                            } 
                        }
                    }
                    condition = false;
                }
            } catch (DoesntExistException e) {
                System.out.println("\nL'équipe " + nomEquipe + " n'existe pas, voulez vous la créer ? (O/N)");
                if(System.console().readLine().strip().toUpperCase().equals("O")){
                    try {
                        LibCreation.creerEquipe(this.lesEquipes, nomEquipe);
                        System.out.println("\nEquipe créé avec succès, veuillez réessayer d'ajouter l'athlete.\n");
                    } catch (AlreadyExistException e2) {
                        System.err.println("Ce message n'est pas censé apparaître");
                    }
                }
            }
        }
    }

    public void inscrireEpreuve(){
        // String description, Sport sport, char sexe
        // quel epreuve > collectif ou non > demandé equipe / athlete selon le collect true ou pas
        boolean condition = true;
        boolean condition2 = true;
        String[] nomEpreuve; 
        Equipe equipe; 
        Epreuve<? extends Participant> epreuve;
        Epreuve<Athlete> epreuveAthlete;
        Epreuve<Equipe> epreuveEquipe;
        String[] entreeEpreuve;
        Athlete unAthlete;
        while (condition) {
            try {
                System.out.println("Quelle epreuve?\nEntrez un nom d'Epreuve et son sexe séparé par des \",\".\n(Ecrivez 0000 pour revenir en arrière)");
                nomEpreuve = System.console().readLine().strip().split(",");
                condition = ! (nomEpreuve[0].equals("0000"));
                if(condition){
                    epreuve = this.getEpreuve(nomEpreuve[0],nomEpreuve[1].charAt(0));
                    if (epreuve.getSport().getNbParEquipe()>1){
                        epreuveEquipe = (Epreuve<Equipe>)epreuve;
                        while (condition2) {
                            System.out.println("\nEQUIPE\nEntrez le nom de votre equipe \",\" \n" +
                                                        "(Ecrivez 0000 pour revenir en arrière)");
                            entreeEpreuve = System.console().readLine().strip().split(",");
                            condition2 = ! (entreeEpreuve[0].equals("0000"));
                            if (condition2){
                                try {
                                    equipe = getEquipe(entreeEpreuve[0]);
                                    epreuveEquipe.inscrire(equipe);

                                    
                                    // equipe.ajouter(this.getAthlete(entreeAthlete[0], entreeAthlete[1], entreeAthlete[2].charAt(0), entreeAthlete[3]));
                                    System.out.println("\nAthlete ajouté à l'équipe avec succès, continuer ? (O/N)");
                                    condition2 = System.console().readLine().strip().toUpperCase().equals("O");
                                } catch (DoesntExistException doesntExistException) {
                                    System.err.println("\n" + doesntExistException.getMessage() + "\n");
                                } catch (CanNotRegisterException canNotRegisterException) {
                                    System.err.println("\n" + canNotRegisterException.getMessage() + "\n");
                                } catch (AlreadyInException alreadyInException){
                                    System.err.println("\n" + alreadyInException.getMessage() + "\n");
                                } catch (NotSameGenderException notSameGenderException){
                                    System.err.println("\n" + notSameGenderException.getMessage() + "\n");
                                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                                    System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.");
                                } 
                            }
                        }
                    }else{
                        
                        epreuveAthlete = (Epreuve<Athlete>)epreuve;

                        while (condition2) {
                            System.out.println("\nATHLETE\nEntrez le nom de votre Athlete Entrez les différents chants dans l'ordre en les espaçant par des \",\" \n" + 
                                                                "nom,prenom,sexe(H/F),Pays \n" +
                                                        "(Ecrivez 0000 pour revenir en arrière)");
                            entreeEpreuve = System.console().readLine().strip().split(",");
                            condition2 = ! (entreeEpreuve[0].equals("0000"));
                            if (condition2){
                                try {
                                    unAthlete = this.getAthlete(entreeEpreuve[0],entreeEpreuve[1],entreeEpreuve[2].charAt(0),entreeEpreuve[3]);
                                    epreuveAthlete.inscrire(unAthlete); 

                                    
                                    // equipe.ajouter(this.getAthlete(entreeAthlete[0], entreeAthlete[1], entreeAthlete[2].charAt(0), entreeAthlete[3]));
                                    System.out.println("\nAthlete ajouté à l'équipe avec succès, continuer ? (O/N)");
                                    condition2 = System.console().readLine().strip().toUpperCase().equals("O");
                                } catch (DoesntExistException doesntExistException) {
                                    System.err.println("\n" + doesntExistException.getMessage() + "\n");
                                } catch (CanNotRegisterException canNotRegisterException) {
                                    System.err.println("\n" + canNotRegisterException.getMessage() + "\n");
                                } catch (AlreadyInException alreadyInException){
                                    System.err.println("\n" + alreadyInException.getMessage() + "\n");
                                }catch (NotSameGenderException notSameGenderException){
                                    System.err.println("\n" + notSameGenderException.getMessage() + "\n");
                                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                                    System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.");
                                } 
                            }
                        }

                    }
                    
                    condition = false;
                }
            } catch (DoesntExistException e) {
                System.out.println("\nl'Epreuve n'existe pas, voulez vous la créer ? (O/N)");
                if(System.console().readLine().strip().toUpperCase().equals("O")){
                    creerEpreuve();
                    System.out.println("\nEpreuve créé avec succès, veuillez réessayer d'ajouter l'athlete.\n");

                }
            } catch (ArrayIndexOutOfBoundsException e3){
                System.err.println("\nVeuillez entrer les informations demandées.\n");
            }
        }
    }
    
    public void creerEpreuve(){
        boolean condition = true;
        String[] entree;
        
        while (condition) {
            System.out.println("Vous allez créer une Epreuve\nEntrez les différents chants dans l'ordre en les espaçant par des \",\" \ndescription,sport,sexe(H/F)\n(Ecrivez 0000 pour revenir en arrière)");
            entree = System.console().readLine().strip().split(",");
            condition = ! (entree[0].equals("0000"));
            if(condition){
                try {
                    Epreuve<Participant> uneEpreuve = new Epreuve<Participant>(entree[0], this.getSport(entree[1]), entree[2].charAt(0));
                    System.out.println("\nEtes vous sur de vouloir créer cette Epreuve ? (O/N)\n" + uneEpreuve);
                    if(System.console().readLine().strip().toUpperCase().equals("O")){
                        LibCreation.creerEpreuve(this.lesEpreuves,uneEpreuve);
                        System.out.println("\nEpreuve créé avec succès\n");
                    }
                    else{System.out.println("\nAnnulation de la création.\n");}                    
                    condition = false;
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.err.println("\nVous n'avez pas rentré assès de valeur, veillez recommencer.\n");
                } catch (AlreadyExistException e){
                    System.err.println("\n" + e.getMessage() + "\n");
                    condition = false;
                    
                } catch (DoesntExistException e){
                    System.out.println("\nCette Epreuve n'existe pas, voulez vous le créer ? (O/N)");
                    if ((System.console().readLine().strip().toUpperCase()).equals("O")){
                        try {
                            Epreuve<Participant> uneEpreuve = new Epreuve<Participant>(entree[0], this.getSport(entree[1]), entree[2].charAt(0));

                            LibCreation.creerEpreuve(this.lesEpreuves, uneEpreuve);
                            System.out.println("\nEpreuve créé avec succès, réessayer de créer l'athlete.\n");
                        } catch (AlreadyExistException e2) {
                            System.err.println("Ce message n'est pas censé apparaître");
                        }
                        catch(DoesntExistException e3){
                            System.err.println(e3.getMessage());
                        }
                    }
                }
            }      
        }
    }

    public void demandeClassement(){
        boolean condition = true;
        String commande;
        int tri;
        while (condition) {
            System.out.println("Choississez le classement à voir\n1 - Par médailles (Or, Argent, Bronze)\n2 - Par total de médailles");
            commande = System.console().readLine().strip();
            condition = ! (commande.equals("0000"));
            if (condition){
                try {
                    tri = Integer.valueOf(commande);

                    if (tri == 1){
                        System.out.println("\nTriage en fonction des médailles.\n");
                        this.triPays(Tris.MEDAILLES);
                        condition = false;
                    }
                    else if (tri == 2){
                        System.out.println("\nTriage en fonction du total de médailles.\n");
                        this.triPays(Tris.TOTAL);
                        condition = false;
                    }
                    else{
                        System.out.println("\nEntrez un chiffre valide.\n");
                    }
                    
                } catch (NumberFormatException e) {
                    System.err.println("\nVeuillez entrer un chiffre.\n");
                }
            }
        }
    }

    public void csvImport(){
        boolean condition = true;
        String chemin = ""; 

        while (condition) {
            System.out.println("Vous allez Importer un fichier d'athlete \nEntrez un chemin vers se fichier.\n(Ecrivez 0000 pour revenir en arrière)");
            chemin = System.console().readLine().strip();
            condition = ! (chemin.equals("0000"));
            if(condition){
                csvToListe(chemin);
            }    
            condition = false;
        }
    }

    public void voirInscritEpreuve(){
        boolean condition = true;
        String[] nomEpreuve; 
        Epreuve<? extends Participant> epreuve;
        while (condition) {
            try {
                System.out.println("Quelle epreuve?\nEntrez un nom d'Epreuve et son sexe séparé par des \",\".\n(Ecrivez 0000 pour revenir en arrière)");
                nomEpreuve = System.console().readLine().strip().split(",");
                condition = ! (nomEpreuve[0].equals("0000"));
                if(condition){
                    epreuve = this.getEpreuve(nomEpreuve[0],nomEpreuve[1].charAt(0));
                    System.out.println("\nLes participants :\n");
                    for (Participant participant : epreuve.getLesParticipants()){
                        System.out.println(participant);
                    }
                    condition = false;
                }
            } catch (DoesntExistException e) {
                System.out.println("\nl'Epreuve n'existe pas, voulez vous la créer ? (O/N)");
                if(System.console().readLine().strip().toUpperCase().equals("O")){
                    this.creerEpreuve();
                }
            } catch (ArrayIndexOutOfBoundsException e3){
                System.err.println("\nVeuillez entrer les informations demandées.\n");
            }
        }
    }

    public void voirMatchs(){
        boolean condition = true;
        String[] nomEpreuve; 
        Epreuve<? extends Participant> epreuve;
        while (condition) {
            try {
                System.out.println("Quelle epreuve?\nEntrez un nom d'Epreuve et son sexe séparé par des \",\".\n(Ecrivez 0000 pour revenir en arrière)");
                nomEpreuve = System.console().readLine().strip().split(",");
                condition = ! (nomEpreuve[0].equals("0000"));
                if(condition){
                    epreuve = this.getEpreuve(nomEpreuve[0], nomEpreuve[1].charAt(0));
                    System.out.println("\nLes matchs :\n");
                    for (Match<? extends Participant> match : epreuve.getLesMatchs()){
                        System.out.println(match);
                    }
                    condition = false;
                }
            } catch (DoesntExistException e) {
                System.out.println("\nl'Epreuve n'existe pas, voulez vous la créer ? (O/N)");
                if(System.console().readLine().strip().toUpperCase().equals("O")){
                    this.creerEpreuve();
                }
            } catch (ArrayIndexOutOfBoundsException e3){
                System.err.println("\nVeuillez entrer les informations demandées.\n");
            }
        }
    }

    public void voirResultatsEpreuve(){
        boolean condition = true;
        String[] nomEpreuve; 
        Epreuve<? extends Participant> epreuve;
        while (condition) {
            try {
                System.out.println("Quelle epreuve?\nEntrez un nom d'Epreuve et son sexe séparé par des \",\".\n(Ecrivez 0000 pour revenir en arrière)");
                nomEpreuve = System.console().readLine().strip().split(",");
                condition = ! (nomEpreuve[0].equals("0000"));
                if(condition){
                    epreuve = this.getEpreuve(nomEpreuve[0], nomEpreuve[1].charAt(0));
                    System.out.println("\nLes matchs :\n");
                    System.out.println(epreuve.getLeClassement());
                    condition = false;
                }
            } catch (DoesntExistException e) {
                System.out.println("\nl'Epreuve n'existe pas, voulez vous la créer ? (O/N)");
                if(System.console().readLine().strip().toUpperCase().equals("O")){
                    this.creerEpreuve();
                }
            } catch (ArrayIndexOutOfBoundsException e3){
                System.err.println("\nVeuillez entrer les informations demandées.\n");
            }
        }
    }

    public void lancerToutEpreuves(){
        for (Epreuve<? extends Participant> epreuve : this.lesEpreuves){
            epreuve.getLeClassement();
        }
    }
}