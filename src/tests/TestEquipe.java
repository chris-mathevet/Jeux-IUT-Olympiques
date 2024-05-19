package tests;

import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Test;

import epreuves.*;
import exceptions.AlreadyInException;
import exceptions.NotSameCountryException;
import exceptions.NotSameGenderException;
import participants.*;
import sports.HandBall;


public class TestEquipe {
    
    @Test
    public void testAjouter() {
        Pays fr = new Pays("France");
        Pays ge = new Pays("Germany");
        Athlete a1 = new Athlete("george", "martin", 'm', 9, 8, 5, fr);
        Athlete a2 = new Athlete("raphael", "nadal", 'm', 9, 8, 5, fr);
        Athlete a3 = new Athlete("sophie", "duke", 'f', 9, 8, 5, fr);
        Athlete a4 = new Athlete("Thomas", "King", 'f', 9, 8, 5, ge);
        Equipe testEquipe = new Equipe("farfadetsMalicieux");
        HandBall sport1 = new HandBall();
        Epreuve<Equipe> epreuveTest = new Epreuve<>("Test", sport1, 'm');
        Match<Equipe> matchTest = new Match<>(3, "Test", epreuveTest);

        try {
            testEquipe.ajouter(a1);
            testEquipe.ajouter(a2);
            testEquipe.ajouter(a3);
        } 
        catch (AlreadyInException e) {
            System.err.println("Déja dans la liste");
        }
        catch (NotSameCountryException e) {
            System.err.println("Pas le bon pays");
        }
        catch (NotSameGenderException e){
            System.err.println("Pas le même sexe");
        }

        assertEquals(testEquipe, Arrays.asList(a1,a2));
        assertNotEquals(testEquipe, Arrays.asList(a1,a2,a4));
        assertTrue(testEquipe.participer(matchTest)>=4 * testEquipe.size());
        assertTrue(testEquipe.participer(matchTest)<=200 * testEquipe.size());
    }   
}