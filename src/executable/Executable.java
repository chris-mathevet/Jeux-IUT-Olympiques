package executable;

import epreuves.Epreuve;
import executable.JO.Tris;
import participants.Athlete;
import participants.Equipe;
import participants.Participant;
import participants.Pays;

public class Executable {
    private static JO jo = new JO();

    public enum Modes{
        QUITTER,ACCUEIL,ATHLETE,EQUIPE,PAYS,EPREUVE
    }

    private static void clearConsole(){
        try {
            if(System.getProperty("os.name").startsWith("Linux")){
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
            else if(System.getProperty("os.name").startsWith("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else{
                System.out.println("\033[H\033[2J");
            }
        } catch (Exception e) {
            System.out.println("\033[H\033[2J");
        }
    }

    private static Modes modeAccueil(){
        boolean menuStatement = true;
        Modes returnedMode = Modes.QUITTER;
        while (menuStatement) {
            System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │              ACCUEIL             │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Menu Athlete                │\n" + 
            " │  E - Menu Equipe                 │\n" + 
            " │  P - Menu Pays                   │\n" + 
            " │  V - Menu Epreuve                │\n" + 
            " │  Q - Quitter                     │\n" + 
            " └──────────────────────────────────┘");
            switch (System.console().readLine().strip().toUpperCase()) {
                case "A":
                    returnedMode = Modes.ATHLETE;
                    menuStatement = false;
                    break;

                case "E":
                    returnedMode = Modes.EQUIPE;
                    menuStatement = false;
                    break;

                case "P":
                    returnedMode = Modes.PAYS;
                    menuStatement = false;
                    break;

                case "Q":
                    returnedMode = Modes.QUITTER;
                    menuStatement = false;
                    break;
            
                case "V":
                    returnedMode = Modes.EPREUVE;
                    menuStatement = false;
                    break;
            
                default:
                    clearConsole();
                    break;
            }
        }
        clearConsole();
        return returnedMode;
    }

    private static Modes modeAthlete(){
        boolean menuStatement = true;
        Modes returnedMode = Modes.QUITTER;
        while (menuStatement) {
            System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │              ATHLETE             │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Ajouter une athlete         │\n" + 
            " │  V - Voir les athletes           │\n" + 
            " │  I - Import CSV                  │\n" + 
            " │  H - Retour accueil              │\n" + 
            " │  Q - Quitter                     │\n" + 
            " └──────────────────────────────────┘");
            switch (System.console().readLine().strip().toUpperCase()) {
                case "A":
                    clearConsole();
                    Executable.jo.creerAthelete();
                    break;

                case "V":
                    clearConsole();
                    System.out.println("\nLes athlètes");
                    for (Athlete athlete : Executable.jo.getLesAthletes()){
                        System.out.println(athlete);
                    }
                    break;
                case "I":
                    clearConsole();
                    Executable.jo.csvImport();
                    break;

                case "H":
                    returnedMode = Modes.ACCUEIL;
                    menuStatement = false;
                    break;

                case "Q":
                    returnedMode = Modes.QUITTER;
                    menuStatement = false;
                    break;
            
                default:
                    clearConsole();
                    break;
            }
        }
        clearConsole();
        return returnedMode;
    }

    private static Modes modeEquipe(){
        boolean menuStatement = true;
        Modes returnedMode = Modes.QUITTER;
        while (menuStatement) {
            System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │              EQUIPE              │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  E - Ajouter une équipe          │\n" + 
            " │  A - Ajouter un athlete          │\n" +
            " │      a une équipe                │\n" + 
            " │  V - Voir les equipes            │\n" + 
            " │  H - Retour accueil              │\n" + 
            " │  Q - Quitter                     │\n" + 
            " └──────────────────────────────────┘");
            switch (System.console().readLine().strip().toUpperCase()) {
                case "E":
                    clearConsole();
                    Executable.jo.creerEquipe();
                    break;

                case "A":
                    clearConsole();
                    Executable.jo.ajoutAthleteEquipe();
                    break;

                case "V":
                    clearConsole();
                    System.out.println("\nLes équipes");
                    for (Equipe equipe : Executable.jo.getLesEquipes()){
                        System.out.println(equipe);
                    }
                    break;

                case "H":
                    returnedMode = Modes.ACCUEIL;
                    menuStatement = false;
                    break;

                case "Q":
                    returnedMode = Modes.QUITTER;
                    menuStatement = false;
                    break;
            
                default:
                    clearConsole();
                    break;
            }
        }
        clearConsole();
        return returnedMode;
    }

    private static Modes modePays(){
        boolean menuStatement = true;
        Modes returnedMode = Modes.QUITTER;
        while (menuStatement) {
            System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │               Pays               │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Ajouter un pays             │\n" + 
            " │  V - Voir les pays               │\n" + 
            " │  H - Retour accueil              │\n" + 
            " │  Q - Quitter                     │\n" + 
            " └──────────────────────────────────┘");
            switch (System.console().readLine().strip().toUpperCase()) {
                case "A":
                    clearConsole();
                    Executable.jo.creerPays();
                    break;

                case "V":
                    clearConsole();
                    System.out.println("\nLes pays");
                    Executable.jo.triPays(Tris.NATUREL);
                    for (Pays pays : Executable.jo.getLesPays()){
                        System.out.println(pays);
                    }
                    break;

                case "H":
                    returnedMode = Modes.ACCUEIL;
                    menuStatement = false;
                    break;

                case "Q":
                    returnedMode = Modes.QUITTER;
                    menuStatement = false;
                    break;
            
                default:
                    clearConsole();
                    break;
            }
        }
        clearConsole();
        return returnedMode;
    }

    public static void main(String[] args) {
        Modes appStatement = Modes.ACCUEIL;
        while (appStatement != Modes.QUITTER){
            switch (appStatement) {
                case ACCUEIL:
                    appStatement = modeAccueil();
                    break;
            
                case ATHLETE:
                    appStatement = modeAthlete();
                    break;

                case EQUIPE:
                    appStatement = modeEquipe();
                    break;

                case PAYS:
                    appStatement = modePays();
                    break;

                case EPREUVE:
                    appStatement = modeEpreuve();
                    break;

                default:
                    break;
            }
        }
    }


    private static Modes modeEpreuve(){
        boolean menuStatement = true;
        Modes returnedMode = Modes.QUITTER;
        while (menuStatement) {
            System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │              EPREUVE             │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Ajouter une Epreuve         │\n" + 
            " │  V - Voir les epreuves           │\n" + 
            " │  I - Inscrire Participant        │\n" + 
            " │  H - Retour accueil              │\n" + 
            " │  Q - Quitter                     │\n" + 
            " └──────────────────────────────────┘");
            switch (System.console().readLine().strip().toUpperCase()) {
                case "A":
                    clearConsole();
                    Executable.jo.creerEpreuve();
                    break;

                case "V":
                    clearConsole();
                    System.out.println("\nLes athlètes");
                    for (Epreuve<? extends Participant> uneEpreuve : Executable.jo.getLesEpreuves()){
                        System.out.println(uneEpreuve);
                    }
                    break;
                case "I":
                    clearConsole();
                    Executable.jo.inscrireEpreuve();
                    break;

                case "H":
                    returnedMode = Modes.ACCUEIL;
                    menuStatement = false;
                    break;

                case "Q":
                    returnedMode = Modes.QUITTER;
                    menuStatement = false;
                    break;
            
                default:
                    clearConsole();
                    break;
            }
        }
        clearConsole();
        return returnedMode;
    }

}
