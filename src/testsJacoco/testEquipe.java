package testsJacoco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import epreuves.Epreuve;
import epreuves.Match;
import exceptions.AlreadyInException;
import exceptions.NotSameCountryException;
import exceptions.NotSameGenderException;
import participants.Athlete;
import participants.Equipe;
import participants.Pays;
import sports.HandBall;

public class testEquipe {
    
    private Equipe equipe;
    private Pays fr;
    private Pays ge;
    private Athlete a1;
    private Athlete a2;
    private Athlete a3;
    private Athlete a4;
    private HandBall sport1;
    private Epreuve<Equipe> epreuveTest;
    private Match<Equipe> matchTest;

    @BeforeEach
    public void setUp() {
        equipe = new Equipe("France");
        fr = new Pays("France");
        ge = new Pays("Germany");
        a1 = new Athlete("george", "martin", 'm', 9, 8, 5, fr);
        a2 = new Athlete("raphael", "nadal", 'm', 9, 8, 5, fr);
        a3 = new Athlete("sophie", "duke", 'f', 9, 8, 5, fr);
        a4 = new Athlete("Thomas", "King", 'f', 9, 8, 5, ge);
        sport1 = new HandBall();
        epreuveTest = new Epreuve<>("Test", sport1, 'm');
        matchTest = new Match<>(3, "Test", epreuveTest);

    }

    @Test
    public void testGetNom() {
        assertEquals("France", equipe.getNom());
    }

    @Test
    public void testGetPays() {
        assertEquals("France", equipe.getPays());
    }

    @Test
    public void testGetSexe() {
        assertEquals("H", equipe.getSexe());
    }

    @Test
    public void testAjouter() {

        try {
            equipe.ajouter(a1);
            equipe.ajouter(a2);
            equipe.ajouter(a3);
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

        assertEquals(equipe, Arrays.asList(a1,a2));
        assertNotEquals(equipe, Arrays.asList(a1,a2,a4));
       
    }   

    @Test
    public void testParticiper() {
        assertTrue(equipe.participer(matchTest)>=4 * equipe.size());
        assertTrue(equipe.participer(matchTest)<=200 * equipe.size());
    }

}
