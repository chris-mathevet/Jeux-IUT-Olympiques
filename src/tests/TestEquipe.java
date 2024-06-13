package tests;

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

public class TestEquipe {
    
    private Equipe equipe;
    private Pays fr;
    private Pays ge;
    private Athlete a0;
    private Athlete a1;
    private Athlete a2;
    private Athlete a3;
    private Athlete a4;
    private Athlete a5;
    private HandBall sport1;
    private Epreuve<Equipe> epreuveTest;
    private Match<Equipe> matchTest;

    @BeforeEach
    public void setUp() {
        equipe = new Equipe("France");
        fr = new Pays("France");
        ge = new Pays("Germany");
        a0 = new Athlete("init", "test", 'H', 0, 0, 0, fr);
        a1 = new Athlete("george", "martin", 'H', 9, 8, 5, fr);
        a2 = new Athlete("raphael", "nadal", 'H', 9, 8, 5, fr);
        a3 = new Athlete("sophie", "duke", 'F', 9, 8, 5, fr);
        a4 = new Athlete("Thomas", "King", 'F', 9, 8, 5, ge);
        a5 = new Athlete("Harry", "Potter", 'H', 9, 8, 5, ge);
        sport1 = new HandBall();
        epreuveTest = new Epreuve<>("Test", sport1, 'H');
        matchTest = new Match<>(3, "Test", epreuveTest);

    }

    @Test
    public void testGetNom() {
        assertEquals("France", equipe.getNom());
    }

    @Test
    public void testGetPays() {
        equipe.add(a0);
        assertEquals("France", equipe.getPays().getNomPays());
    }

    @Test
    public void testGetSexe() {
        equipe.add(a0);
        assertEquals('H', equipe.getSexe());
    }

    @Test
    public void testAjouter() {

        try {
            equipe.ajouter(a1);
            equipe.ajouter(a2);
            equipe.ajouter(a3);
        } 
        catch (AlreadyInException e) {
            System.err.println(e.getMessage());
        }
        catch (NotSameCountryException e) {
            System.err.println(e.getMessage());
        }
        catch (NotSameGenderException e){
            System.err.println(e.getMessage());
        }
        
        assertTrue(equipe.size() > 0);
        assertTrue(equipe.size() > 0 && equipe.size() < 4);
       
    }   

    @Test
    public void testParticiper() {
        assertTrue(equipe.participer(matchTest)>=4 * equipe.size());
        assertTrue(equipe.participer(matchTest)<=200 * equipe.size());
    }

    @Test
    public void testException() {

        boolean testGender = false;
        boolean testCountry = false;
        boolean testAlreay = false;


        try {
            equipe.ajouter(a1);
            equipe.ajouter(a1);
        } 
        catch (AlreadyInException | NotSameCountryException | NotSameGenderException e) {
            System.err.println(e.getMessage());
            if (e instanceof AlreadyInException) {
                testAlreay = true;
            }
        }

        try {
            equipe.ajouter(a2);
            equipe.ajouter(a3);
        } 
        catch (AlreadyInException | NotSameCountryException | NotSameGenderException e) {
            System.err.println(e.getMessage());
            if (e instanceof NotSameGenderException) {
                testGender = true;
            }
        }

        try {
            equipe.ajouter(a5);
        } 
        catch (AlreadyInException | NotSameCountryException | NotSameGenderException e) {
            System.err.println(e.getMessage());
            if (e instanceof NotSameCountryException) {
                testCountry = true;
            }
        }

        assertTrue(testAlreay);
        assertTrue(testCountry);
        assertTrue(testGender);
    }

    @Test
    public void testToString() {
        assertEquals(equipe.toString(), "France, membre: []");
    }

}