import org.junit.Test;

public class TestComparateurs {
    @Test
    public void TestComparateurTemps(){
        Athletisme athletisme = new Athletisme();
        Athlete baptiste = new Athlete("RICHARD","Baptiste",'M', 11, 18, 16);
        Athlete chris = new Athlete("MATEHEVET","Chris",'F', 13,17, 15);
        Athlete julian = new Athlete("MARQUES","Julian",'M', 15,10, 19);
        Athlete axel = new Athlete("MEUNIER","Axel",'M', 19,18, 9);
        Athlete michelle = new Athlete("MICHMICH","Michelle",'F', 12,16, 14);
        Epreuve_Individuelle mettre110H = new Epreuve_Individuelle("110 m haies hommes", athletisme, 'M');
        Epreuve_Individuelle mettre110F = new Epreuve_Individuelle("110 m haies femmes", athletisme, 'F');
        mettre110F.inscrire(chris);
        mettre110F.inscrire(michelle);
        mettre110H.inscrire(baptiste);
        mettre110H.inscrire(julian);
        mettre110H.inscrire(axel);
    
        


    }

    @Test
    public void TestComparateurNbVictoires(){

    }
}
