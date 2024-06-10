package Executable;

public class Executable {
    public enum Modes{
        QUITTER,ACCUEIL,ATHLETE,EQUIPE,PAYS
    }

    private static Modes modeAccueil(){
        System.out.println(""+ 
            " ┌──────────────────────────────────┐\n" + 
            " │              ACCUEIL             │\n" +
            " ├──────────────────────────────────┤\n" +
            " │  A - Ajouter un athlete          │\n" + 
            " └──────────────────────────────────┘");
        return Modes.QUITTER;
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
