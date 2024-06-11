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

import participants.Pays;
import comparateurs.*;

public class TestComparateur {

    private Pays france;
    private Pays angleterre;
    private Pays portugal;
    private Pays italie;
    private Pays russie;
    private Pays vatican;
    private Pays finlande;
    private List<Pays> lesPays;
    private List<Pays> trie1;
    private List<Pays> trie2;
    private List<Pays> trie3;
    private Comparator<Pays> compTotal;
    private Comparator<Pays> compMedaille;

    @BeforeEach
    public void setUp() {

        france = new Pays("France");
        angleterre = new Pays("Angleterre");
        portugal = new Pays("Portugal");
        italie = new Pays("Italie");
        russie = new Pays("Russie");
        vatican = new Pays("Vatican");
        finlande = new Pays("Finlande");
        lesPays = Arrays.asList(france,angleterre,portugal,italie,russie,vatican,finlande);
        trie1 = Arrays.asList(angleterre, finlande,france,italie,portugal,russie,vatican);
        trie2 = Arrays.asList(vatican, angleterre,finlande,france,russie,italie,portugal);
        trie3 = Arrays.asList(vatican, france,portugal,angleterre,italie,russie,finlande);
        compTotal = new ComparateurTotal();
        compMedaille = new ComparateurMedailles();

    }
    
    @Test
    public void testCompNaturel(){

       

        Collections.sort(lesPays);


        // System.out.println(trie);
        // System.out.println();
        // System.out.println(lesPays);

        assertEquals(lesPays, trie1);
    }

    @Test
    public void testCompTotal(){

        // Ajout des médailles

        france.addMedailleOr(69);
        france.addMedailleArgent(42);
        france.addMedailleBronze(404);

        angleterre.addMedailleOr(15);
        angleterre.addMedailleArgent(12);
        angleterre.addMedailleBronze(666);

        portugal.addMedailleOr(15);
        portugal.addMedailleArgent(13);
        portugal.addMedailleBronze(2);

        italie.addMedailleOr(15);
        italie.addMedailleArgent(12);
        italie.addMedailleBronze(6);

        russie.addMedailleOr(1);
        russie.addMedailleArgent(55);
        russie.addMedailleBronze(0);

        vatican.addMedailleOr(666);
        vatican.addMedailleArgent(666);
        vatican.addMedailleBronze(666);

        finlande.addMedailleOr(667);
        finlande.addMedailleArgent(0);
        finlande.addMedailleBronze(0);



        Collections.sort(lesPays,compTotal);

        assertEquals(lesPays, trie2);
    }

    @Test
    public void testCompMedailles(){


        // Ajout des médailles

        france.addMedailleOr(69);
        france.addMedailleArgent(42);
        france.addMedailleBronze(404);

        angleterre.addMedailleOr(15);
        angleterre.addMedailleArgent(12);
        angleterre.addMedailleBronze(666);

        portugal.addMedailleOr(15);
        portugal.addMedailleArgent(13);
        portugal.addMedailleBronze(2);

        italie.addMedailleOr(15);
        italie.addMedailleArgent(12);
        italie.addMedailleBronze(6);

        russie.addMedailleOr(1);
        russie.addMedailleArgent(55);
        russie.addMedailleBronze(0);

        vatican.addMedailleOr(666);
        vatican.addMedailleArgent(666);
        vatican.addMedailleBronze(666);

        finlande.addMedailleOr(0);
        finlande.addMedailleArgent(0);
        finlande.addMedailleBronze(0);

        

        Collections.sort(lesPays,compMedaille);

        System.out.println(lesPays);
        
        assertEquals(lesPays, trie3);
    }
}
