package executable;

import participants.Athlete;

public class Executable {
    private static JO jo = new JO();

    public enum Modes{
        QUITTER,ACCUEIL,ATHLETE,EQUIPE,PAYS
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
            
                default:
                    System.out.println("\033[H\033[2J");
                    break;
            }
        }
        System.out.println("\033[H\033[2J");
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
            " │  H - Retour accueil              │\n" + 
            " │  Q - Quitter                     │\n" + 
            " └──────────────────────────────────┘");
            switch (System.console().readLine().strip().toUpperCase()) {
                case "A":
                    System.out.println("\033[H\033[2J");
                    Executable.jo.creerAthelete();
                    break;

                case "V":
                    System.out.println("\033[H\033[2J");
                    System.out.println("\nLes athlètes");
                    for (Athlete athlete : Executable.jo.getLesAthletes()){
                        System.out.println(athlete);
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
                    System.out.println("\033[H\033[2J");
                    break;
            }
        }
        System.out.println("\033[H\033[2J");
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
            " │  Q - Retour accueil              │\n" + 
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
            
                default:
                    System.out.println("\033[H\033[2J");
                    break;
            }
        }
        System.out.println("\033[H\033[2J");
        return returnedMode;
    }

    private static Modes modePays(){
        System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │               PAYS               │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Ajouter un athlete          │\n" + 
            " └──────────────────────────────────┘");
        return Modes.QUITTER;
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

                default:
                    break;
            }
        }
    }
}
