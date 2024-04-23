import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestEpreuveMatchIndividuelle {
    @Test
    public void testResultats(){
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

        Match_individuel matchsTestH = new Match_individuel(0, "Tour test", mettre110H);
        Match_individuel matchsTestF = new Match_individuel(0, "Tour test", mettre110F);

        List<Integer> resH = matchsTestH.resultat();
        List<Integer> resF = matchsTestF.resultat();

        for(Integer temps1 : resH){
            assertTrue(temps1>=0);
            assertTrue(temps1<=200);
        }
        for(Integer temps2 : resF){
            assertTrue(temps2>=0);
            assertTrue(temps2<=200);
        }
    }
}
