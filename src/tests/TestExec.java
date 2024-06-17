package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import executable.*;
import epreuves.Epreuve;
import exceptions.AlreadyExistException;
import exceptions.AlreadyInException;
import exceptions.DoesntExistException;
import exceptions.NotSameCountryException;
import exceptions.NotSameGenderException;
import participants.Athlete;
import participants.Equipe;
import participants.Pays;
import sports.HandBall;
import sports.VoleyBall;

public class TestExec {
    
    private JO jo;


    @BeforeEach
    public void setUp() {
        jo = new JO();
        try {
            LibCreation.creerPays(jo.getLesPays(), "France");
            
        } catch (AlreadyExistException e) {
        }
        jo.addSport("VolleyBall");
        jo.creerEquipe();
    }

    @Test
    public void testGetPays() {

        boolean testDoesnt = false;

        try {
            jo.getPays("France");
        } catch (DoesntExistException e) {
            System.err.println(e.getMessage());
        }

        try {
            jo.getPays("Congo");
        } catch (DoesntExistException e) {
            System.err.println(e.getMessage());
            testDoesnt = true;
        }

        assertTrue(testDoesnt);
        
    }

    @Test
    public void testCreation() {

        boolean testAlreay = false;

        try {
            LibCreation.creerAthlete(jo.getLesAthletes(), new Athlete("Test","init", 'H',0,0,0,jo.getPays("France")));
        } catch (AlreadyExistException | DoesntExistException e) {
            System.err.println(e.getMessage());
        }
        try {
            LibCreation.creerAthlete(jo.getLesAthletes(), new Athlete("Test","init", 'H',0,0,0,jo.getPays("France")));
        } catch (AlreadyExistException | DoesntExistException e) {
            System.err.println(e.getMessage());
            testAlreay = true;
        }

        assertTrue(testAlreay);
    }

    
}