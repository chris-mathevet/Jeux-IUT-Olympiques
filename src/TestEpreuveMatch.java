import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestEpreuveMatch {
    @Test
    public void testMatchAthlete(){
        Escrime escrime = new Escrime();
        Pays france = new Pays("France");

        Athlete baptiste = new Athlete("RICHARD","Baptiste",'M', 11, 18, 16,france);
        Athlete chris = new Athlete("MATEHEVET","Chris",'F', 13,17, 15,france);
        Athlete julian = new Athlete("MARQUES","Julian",'M', 15,10, 19,france);
        Athlete axel = new Athlete("MEUNIER","Axel",'M', 19,18, 9,france);
        Athlete michelle = new Athlete("MICHMICH","Michelle",'F', 12,16, 14,france);

        Epreuve<Athlete> mettre110H = new Epreuve<>("110 m haies hommes", escrime, 'M');
        Epreuve<Athlete> mettre110F = new Epreuve<>("110 m haies femmes", escrime, 'F');

        try {
            mettre110F.inscrire(chris);
            mettre110F.inscrire(michelle);

            mettre110H.inscrire(baptiste);
            mettre110H.inscrire(julian);
            mettre110H.inscrire(axel);
            
        } catch (AlreadyInException e) {
            System.err.println(e.getMessage());
        } catch(NePeuxPasInscrireException e){
            System.err.println(e.getMessage());
        }
        
        Match<Athlete> matchsTestH = new Match<>(0, "Tour test", mettre110H);
        Match<Athlete> matchsTestF = new Match<>(0, "Tour test", mettre110F);
        
        List<Double> resH = matchsTestH.getResultats();
        List<Double> resF = matchsTestF.getResultats();

        for(Double temps1 : resH){
            assertTrue(temps1>=0);
            assertTrue(temps1<=200);
        }
        for(Double temps2 : resF){
            assertTrue(temps2>=0);
            assertTrue(temps2<=200);
        }
    }
    
    @Test
    public void testMatchEquipe(){
        Pays france = new Pays("France");
        
        Athlete baptiste = new Athlete("RICHARD","Baptiste",'M', 11, 18, 16,france);
        Athlete chris = new Athlete("MATEHEVET","Chris",'M', 13,17, 15,france);
        Athlete julian = new Athlete("MARQUES","Julian",'M', 15,10, 19,france);
        Athlete axel = new Athlete("MEUNIER","Axel",'M', 19,18, 9,france);
        Athlete shanka = new Athlete("ClERMONT","shanka",'M', 19,18, 9,france);
        Athlete bastien = new Athlete("MONET","bastien",'M', 19,18, 9,france);

        Athlete riri = new Athlete("Duck","riri",'M', 1,8, 19,france);
        Athlete fifi = new Athlete("Duck","fifi",'M', 12,5, 9,france);
        Athlete loulou = new Athlete("Duck","loulou",'M', 19,8, 9,france);
        Athlete pixou = new Athlete("Duck","pixou",'M', 19,12, 9,france);
        Athlete donald = new Athlete("Duck","donald",'M', 19,18, 19,france);
        Athlete flagada = new Athlete("Jones","Flagada",'M', 19,18, 19,france);

        Equipe equipe1 = new Equipe("1");
        Equipe equipe2 = new Equipe("2");

        try {
            equipe1.ajouter(baptiste);
            equipe1.ajouter(chris);
            equipe1.ajouter(julian);
            equipe1.ajouter(axel);
            equipe1.ajouter(shanka);
            equipe1.ajouter(bastien);

            equipe2.ajouter(riri);
            equipe2.ajouter(fifi);
            equipe2.ajouter(loulou);
            equipe2.ajouter(pixou);   
            equipe2.ajouter(donald);
            equipe2.ajouter(flagada);
        } catch (AlreadyInException e) {
            System.err.println(e.getMessage());
        } catch(NotSameCountryException e){
            System.err.println(e.getMessage());
        }

        VoleyBall voley = new VoleyBall();

        Epreuve<Equipe> epreuve = new Epreuve<>("test", voley, 'M');

        try {
            epreuve.inscrire(equipe1);
            epreuve.inscrire(equipe2);
            
        } catch (AlreadyInException e) {
            System.err.println(e.getMessage());
        } catch(NePeuxPasInscrireException e){
            System.err.println(e.getMessage());
        }

        Match<Equipe> match = new Match<>(1, "test", epreuve);

        assertTrue(match.getResultats()!=null);
    }

    @Test
    public void testTemps(){
        Athletisme athletisme = new Athletisme();
        Natation natation = new Natation();
        Pays france = new Pays("France");

        Athlete baptiste = new Athlete("RICHARD","Baptiste",'M', 11, 18, 16,france);
        Athlete julian = new Athlete("MARQUES","Julian",'M', 15,10, 19,france);
        Athlete axel = new Athlete("MEUNIER","Axel",'M', 19,18, 9,france);

        Epreuve<Athlete> athletisme110 = new Epreuve<>("110 m haies hommes", athletisme, 'M');
        Epreuve<Athlete> athletisme4x110 = new Epreuve<>("4*110 m haies hommes", athletisme, 'M');
        Epreuve<Athlete> natatione110 = new Epreuve<>("110 m nage libre hommes", natation, 'M');
        Epreuve<Athlete> natation4x110 = new Epreuve<>("4*110 m nage libre hommes", natation, 'M');

        try {
            athletisme110.inscrire(baptiste);
            athletisme110.inscrire(julian);
            athletisme110.inscrire(axel);

            athletisme4x110.inscrire(baptiste);
            athletisme4x110.inscrire(julian);
            athletisme4x110.inscrire(axel);

            natatione110.inscrire(baptiste);
            natatione110.inscrire(julian);
            natatione110.inscrire(axel);

            natation4x110.inscrire(baptiste);
            natation4x110.inscrire(julian);
            natation4x110.inscrire(axel);
            
        } catch (AlreadyInException e) {
            System.err.println(e.getMessage());
        } catch(NePeuxPasInscrireException e){
            System.err.println(e.getMessage());
        }

        Match<Athlete> matchsAthle100 = new Match<>(0, "Tour athle 110", athletisme110);
        Match<Athlete> matchsAthle4x100 = new Match<>(0, "Tour athle 4*110", athletisme4x110);
        Match<Athlete> matchsNatation100 = new Match<>(0, "Tour nata 110", natatione110);
        Match<Athlete> matchsNatation4x100 = new Match<>(0, "Tour nata 4*110", natation4x110);
        
        List<Double> resAthle100 = matchsAthle100.getResultats();
        List<Double> resAthle4x100 = matchsAthle4x100.getResultats();
        List<Double> resNata100 = matchsNatation100.getResultats();
        List<Double> resNata4x100 = matchsNatation4x100.getResultats();


        for(Double temps1 : resAthle100){
            assertTrue(temps1>=10.2);
            assertTrue(temps1<=20.0);
        }
        for(Double temps1 : resAthle4x100){
            assertTrue(temps1>=30.2);
            assertTrue(temps1<=40.0);
        }
        for(Double temps1 : resNata100){
            assertTrue(temps1>=42.2);
            assertTrue(temps1<=52.0);
        }
        for(Double temps1 : resNata4x100){
            assertTrue(temps1>=182.0);
            assertTrue(temps1<=280.0);
        }
    }

    @Test
    public void testInscrireAthlete(){
        Athletisme athletisme = new Athletisme();
        VoleyBall voleyBall = new VoleyBall();
        Pays france = new Pays("France");

        Athlete baptiste = new Athlete("RICHARD","Baptiste",'M', 11, 18, 16,france);
        Athlete julian = new Athlete("MARQUES","Julian",'M', 15,10, 19,france);
        Athlete axel = new Athlete("MEUNIER","Axel",'M', 19,18, 9,france);

        Epreuve<Athlete> athletisme110 = new Epreuve<>("110 m haies hommes", athletisme, 'M');
        Epreuve<Athlete> epreuveVoleyTest = new Epreuve<>("VoleyBall athlete (impossible)", voleyBall, 'M');

        boolean testNormal = false;
        boolean testAlreadyIn = false;
        boolean testPeuxPasInscrire = false;

        try {
            athletisme110.inscrire(baptiste);
            athletisme110.inscrire(julian);
            athletisme110.inscrire(axel);
            testNormal = true;
            
        } catch (AlreadyInException e) {
            System.err.println(e.getMessage());
        } catch(NePeuxPasInscrireException e){
            System.err.println(e.getMessage());
        }

        try {
            athletisme110.inscrire(baptiste);
            athletisme110.inscrire(julian);
            athletisme110.inscrire(axel);
            
        } catch (AlreadyInException e) {
            System.err.println(e.getMessage());
            testAlreadyIn = true;
        } catch(NePeuxPasInscrireException e){
            System.err.println(e.getMessage());
        }

        try {
            epreuveVoleyTest.inscrire(baptiste);
            epreuveVoleyTest.inscrire(julian);
            epreuveVoleyTest.inscrire(axel);
            
        } catch (AlreadyInException e) {
            System.err.println(e.getMessage());
        } catch(NePeuxPasInscrireException e){
            testPeuxPasInscrire = true;
            System.err.println(e.getMessage());
        }

        assertTrue(testPeuxPasInscrire);
        assertTrue(testAlreadyIn);
        assertTrue(testNormal);
    }
}