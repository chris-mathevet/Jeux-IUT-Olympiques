import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestEpreuveMatch {
    @Test
    public void testMatchAthlete(){
        Athletisme athletisme = new Athletisme();

        Athlete baptiste = new Athlete("RICHARD","Baptiste",'M', 11, 18, 16);
        Athlete chris = new Athlete("MATEHEVET","Chris",'F', 13,17, 15);
        Athlete julian = new Athlete("MARQUES","Julian",'M', 15,10, 19);
        Athlete axel = new Athlete("MEUNIER","Axel",'M', 19,18, 9);
        Athlete michelle = new Athlete("MICHMICH","Michelle",'F', 12,16, 14);

        Epreuve<Athlete> mettre110H = new Epreuve<>("110 m haies hommes", athletisme, 'M');
        Epreuve<Athlete> mettre110F = new Epreuve<>("110 m haies femmes", athletisme, 'F');

        mettre110F.inscrire(chris);
        mettre110F.inscrire(michelle);

        mettre110H.inscrire(baptiste);
        mettre110H.inscrire(julian);
        mettre110H.inscrire(axel);

        Match<Athlete> matchsTestH = new Match<>(0, "Tour test", mettre110H);
        Match<Athlete> matchsTestF = new Match<>(0, "Tour test", mettre110F);

        matchsTestH.resultat();
        matchsTestF.resultat();

        List<Integer> resH = matchsTestH.getResultats();
        List<Integer> resF = matchsTestF.getResultats();

        for(Integer temps1 : resH){
            assertTrue(temps1>=0);
            assertTrue(temps1<=200);
        }
        for(Integer temps2 : resF){
            assertTrue(temps2>=0);
            assertTrue(temps2<=200);
        }
    }
    @Test
    public void testMatchEquipe(){
        Athlete baptiste = new Athlete("RICHARD","Baptiste",'M', 11, 18, 16);
        Athlete chris = new Athlete("MATEHEVET","Chris",'M', 13,17, 15);
        Athlete julian = new Athlete("MARQUES","Julian",'M', 15,10, 19);
        Athlete axel = new Athlete("MEUNIER","Axel",'M', 19,18, 9);
        Athlete shanka = new Athlete("ClERMONT","shanka",'M', 19,18, 9);
        Athlete bastien = new Athlete("MONET","bastien",'M', 19,18, 9);

        Athlete riri = new Athlete("Duck","riri",'M', 1,8, 19);
        Athlete fifi = new Athlete("Duck","fifi",'M', 12,5, 9);
        Athlete loulou = new Athlete("Duck","loulou",'M', 19,8, 9);
        Athlete pixou = new Athlete("Duck","pixou",'M', 19,12, 9);
        Athlete donald = new Athlete("Duck","donald",'M', 19,18, 19);
        Athlete flagada = new Athlete("Jones","Flagada",'M', 19,18, 19);

        Equipe equipe1 = new Equipe("1");
        equipe1.add(baptiste);
        equipe1.add(chris);
        equipe1.add(julian);
        equipe1.add(axel);
        equipe1.add(shanka);
        equipe1.add(bastien);
        Equipe equipe2 = new Equipe("2");
        equipe2.add(riri);
        equipe2.add(fifi);
        equipe2.add(loulou);
        equipe2.add(pixou);   
        equipe2.add(donald);
        equipe2.add(flagada);

        VoleyBall voley = new VoleyBall();

        Epreuve<Equipe> epreuve = new Epreuve<>("test", voley, 'M');
        epreuve.inscrire(equipe1);
        epreuve.inscrire(equipe2);

        Match<Equipe> match = new Match<>(1, "test", epreuve);

        assertTrue(match.getResultats()!=null);

    }
}