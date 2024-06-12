package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import participants.Athlete;
import participants.Pays;
import sports.*;

public class TestSport {
    private Athlete michel;
    private Athlete jean;
    private VoleyBall voley1;
    private HandBall handBall1;
    private Natation natation1;
    private Escrime escrime1;
    private Athletisme athletisme1;
    private VoleyBall voley2;
    private HandBall handBall2;
    private Natation natation2;
    private Escrime escrime2;
    private Athletisme athletisme2;

    @BeforeEach
    public void setUp() {
        michel = new Athlete("balade", "Michel", 'M', 10, 18, 9, new Pays("France"));
        jean = new Athlete("balade", "Jean", 'M', 20, 6, 19, new Pays("France"));

        voley1 = new VoleyBall();
        handBall1 = new HandBall();
        natation1 = new Natation();
        escrime1 = new Escrime();
        athletisme1 = new Athletisme();

        voley2 = new VoleyBall();
        handBall2 = new HandBall();
        natation2 = new Natation();
        escrime2 = new Escrime();
        athletisme2 = new Athletisme();
    }

    @Test
    public void testGetNbParEquipe() {
        assertEquals(voley1.getNbParEquipe(), 6);
        assertEquals(handBall1.getNbParEquipe(), 7);
        assertEquals(natation1.getNbParEquipe(), 1);
        assertEquals(escrime1.getNbParEquipe(), 1);
        assertEquals(athletisme1.getNbParEquipe(), 1);
    }

    @Test
    public void testGetSport() {
        assertEquals(voley1.getSport(), "Voley");
        assertEquals(handBall1.getSport(), "Handball");
        assertEquals(natation1.getSport(), "Natation");
        assertEquals(escrime1.getSport(), "Escrime");
        assertEquals(athletisme1.getSport(), "Athletisme");
    }

    @Test
    public void testCoefForce() {
        assertEquals(voley1.getCoefForce(), 4);
        assertEquals(handBall1.getCoefForce(), 4);
        assertEquals(natation1.getCoefForce(), 1);
        assertEquals(escrime1.getCoefForce(), 1);
        assertEquals(athletisme1.getCoefForce(), 1);
    }

    @Test
    public void testGetCoefEndurance() {
        assertEquals(voley1.getCoefEndurance(), 2);
        assertEquals(handBall1.getCoefEndurance(), 3);
        assertEquals(natation1.getCoefEndurance(), 5);
        assertEquals(escrime1.getCoefEndurance(), 2);
        assertEquals(athletisme1.getCoefEndurance(), 3);
    }

    @Test
    public void testGetCoefAgilite() {
        assertEquals(voley1.getCoefAgilite(), 3);
        assertEquals(handBall1.getCoefAgilite(), 2);
        assertEquals(natation1.getCoefAgilite(), 2);
        assertEquals(escrime1.getCoefAgilite(), 4);
        assertEquals(athletisme1.getCoefAgilite(), 5);
    }

    @Test
    public void testGetCoefRandom() {
        assertEquals(voley1.getCoefRandom(), 1);
        assertEquals(handBall1.getCoefRandom(), 1);
        assertEquals(natation1.getCoefRandom(), 2);
        assertEquals(escrime1.getCoefRandom(), 3);
        assertEquals(athletisme1.getCoefRandom(), 1);
    }

    @Test
    public void testGetEstTemps() {
        assertEquals(voley1.getEstTemsp(), false);
        assertEquals(handBall1.getEstTemsp(), false);
        assertEquals(natation1.getEstTemsp(), true);
        assertEquals(escrime1.getEstTemsp(), false);
        assertEquals(athletisme1.getEstTemsp(), true);
    }

    @Test
    public void testSetCoefForce() {
        voley1.setCoefForce(50);
        assertEquals(voley1.getCoefForce(), 50);
    }

    @Test
    public void testSetCoefEndurance() {
        voley1.setCoefEndurance(50);
        assertEquals(voley1.getCoefEndurance(), 50);
    }

    @Test
    public void testSetCoefAgilite() {
        voley1.setCoefAgilite(50);
        assertEquals(voley1.getCoefAgilite(), 50);
    }

    @Test
    public void testSetCoefRandom() {
        voley1.setCoefRandom(50);
        assertEquals(voley1.getCoefRandom(), 50);
    }

    @Test
    public void testBareme() {
        assertTrue(voley1.bareme(michel) >= 4);
        assertTrue(voley1.bareme(michel) <= 200);

        assertTrue(handBall1.bareme(michel) >= 4);
        assertTrue(handBall1.bareme(michel) <= 200);

        assertTrue(natation1.bareme(michel) >= 4);
        assertTrue(natation1.bareme(michel) <= 200);

        assertTrue(escrime1.bareme(michel) >= 4);
        assertTrue(escrime1.bareme(michel) <= 200);

        assertTrue(athletisme1.bareme(michel) >= 4);
        assertTrue(athletisme1.bareme(michel) <= 200);

        assertTrue(voley2.bareme(jean) >= 4);
        assertTrue(voley2.bareme(jean) <= 200);

        assertTrue(handBall2.bareme(jean) >= 4);
        assertTrue(handBall2.bareme(jean) <= 200);

        assertTrue(natation2.bareme(jean) >= 4);
        assertTrue(natation2.bareme(jean) <= 200);

        assertTrue(escrime2.bareme(jean) >= 4);
        assertTrue(escrime2.bareme(jean) <= 200);

        assertTrue(athletisme2.bareme(jean) >= 4);
        assertTrue(athletisme2.bareme(jean) <= 200);
    }
}
