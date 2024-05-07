import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestMatch_Collectif {
    @Test
    public void TestResultat(){
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
        equipe1.ajouter(baptiste);
        equipe1.ajouter(chris);
        equipe1.ajouter(julian);
        equipe1.ajouter(axel);
        equipe1.ajouter(shanka);
        equipe1.ajouter(bastien);
        Equipe equipe2 = new Equipe("2");
        equipe2.ajouter(riri);
        equipe2.ajouter(fifi);
        equipe2.ajouter(loulou);
        equipe2.ajouter(pixou);   
        equipe2.ajouter(donald);
        equipe2.ajouter(flagada);

        VoleyBall voley = new VoleyBall();

        Epreuve_Collective epreuve = new Epreuve_Collective("test", voley, 'M');
        epreuve.inscrire(equipe1);
        epreuve.inscrire(equipe2);

        Match_Collectif match = new Match_Collectif(1, "test", epreuve);

        assertTrue(match.resultat().size()==2);

    }
}
