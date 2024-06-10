package Executable;

public class Executable {
    public enum Modes{
        QUITTER,ACCUEIL,ATHLETE,EQUIPE,PAYS
    }

    private static Modes modeAccueil(){
        boolean menuStatement = true;
        Modes returnedMode = Modes.QUITTER;
        System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │              ACCUEIL             │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Menu Athlete                │\n" + 
            " │  E - Menu Equipe                 │\n" + 
            " │  P - Menu Pays                   │\n" + 
            " │  Q - Quitter                     │\n" + 
            " └──────────────────────────────────┘");
        while (menuStatement) {
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
                    break;
            }
        }
        return returnedMode;
    }

    private static Modes modeAthlete(){
        System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │              ATHLETE             │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Ajouter un athlete          │\n" + 
            " └──────────────────────────────────┘");
        return Modes.QUITTER;
    }

    private static Modes modeEquipe(){
        System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │              EQUIPE              │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Ajouter un athlete          │\n" + 
            " └──────────────────────────────────┘");
        return Modes.QUITTER;
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
        JO jo = new JO();
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
