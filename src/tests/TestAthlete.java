package tests;

import static org.junit.jupiter.api.Assertions.assertTrue; // Changement ici pour utiliser JUnit Jupiter
import org.junit.jupiter.api.Test; // Changement ici pour utiliser JUnit Jupiter
import org.junit.jupiter.api.BeforeEach; // Changement ici pour utiliser JUnit Jupiter

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import participants.Athlete;
import participants.Pays;
import comparateurs.*;


public class TestAthlete {

    private Pays fr;
    private Pays ge;
    
    private Athlete a0;
    private Athlete a00;
    private Athlete a1;
    private Athlete a2;
    private Athlete a3;
    private Athlete a4;
    private Athlete a5;


    @BeforeEach
    public void setUp() {

        fr = new Pays("France");
        ge = new Pays("Germany");

        a0 = new Athlete("init", "test", 'H', 0, 0, 0, fr);
        a00 = new Athlete("init", "test", 'H', 0, 0, 0, fr);
        a1 = new Athlete("george", "martin", 'H', 9, 8, 5, fr);
        a2 = new Athlete("raphael", "nadal", 'H', 9, 8, 5, fr);
        a3 = new Athlete("sophie", "duke", 'F', 9, 8, 5, fr);
        a4 = new Athlete("Thomas", "King", 'F', 9, 8, 5, ge);
        a5 = new Athlete("Harry", "Potter", 'H', 79, 78, 75, ge);
    }

    @Test
    public void testGetNom() {
        assertEquals(a0.getNom(), "init");
        assertEquals(a1.getNom(), "george");
        assertEquals(a2.getNom(), "raphael");
    }

    @Test
    public void testGetPrenom() {
        assertEquals(a0.getPrenom(), "test");
        assertEquals(a1.getPrenom(), "martin");
        assertEquals(a2.getPrenom(), "nadal");
    }

    @Test
    public void testGetSexe() {
        assertEquals(a0.getSexe(), 'H');
        assertEquals(a1.getSexe(), 'H');
        assertEquals(a2.getSexe(), 'H');
    }

    @Test
    public void testGetForce() {
        assertEquals(a0.getForce(), 1);
        assertEquals(a1.getForce(), 9);
        assertEquals(a2.getForce(), 9);
    }

    @Test
    public void testGetEndurance() {
        assertEquals(a0.getEndurance(), 1);
        assertEquals(a1.getEndurance(), 5);
        assertEquals(a2.getEndurance(), 5);
    }

    @Test
    public void testGetAgilite() {
        assertEquals(a0.getAgilite(), 1);
        assertEquals(a1.getAgilite(), 8);
        assertEquals(a2.getAgilite(), 8);
    }

    @Test
    public void testGetPays() {
        assertEquals(a0.getPays(), fr);
        assertEquals(a1.getPays(), fr);
        assertEquals(a2.getPays(), fr);
    }

    @Test
    public void testToStringEquals() {
        assertEquals(a0.toString(), "(nom: init, prénom: test, sexe: H, pays: France, force: 1, agilité: 1, endurance: 1)");
        assertNotEquals(a0, a1);
        assertEquals(a0, a00);
    }
}
