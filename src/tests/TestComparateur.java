package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import participants.Pays;
import comparateurs.*;

public class TestComparateur {
    
    @Test
    public void testCompNaturel(){
        Pays france = new Pays("France");
        Pays angleterre = new Pays("Angleterre");
        Pays portugal = new Pays("Portugal");
        Pays italie = new Pays("Italie");
        Pays russie = new Pays("Russie");
        Pays vatican = new Pays("Vatican");
        Pays finlande = new Pays("Finlande");

        List<Pays> lesPays = Arrays.asList(france,angleterre,portugal,italie,russie,vatican,finlande);

        Collections.sort(lesPays);

        List<Pays> trie = Arrays.asList(angleterre, finlande,france,italie,portugal,russie,vatican);
        // System.out.println(trie);
        // System.out.println();
        // System.out.println(lesPays);

        assertEquals(lesPays, trie);
    }

    @Test
    public void testCompTotal(){
        Pays france = new Pays("France");
        Pays angleterre = new Pays("Angleterre");
        Pays portugal = new Pays("Portugal");
        Pays italie = new Pays("Italie");
        Pays russie = new Pays("Russie");
        Pays vatican = new Pays("Vatican");
        Pays finlande = new Pays("Finlande");

        List<Pays> lesPays = Arrays.asList(france,angleterre,portugal,italie,russie,vatican,finlande);
        List<Pays> trie = Arrays.asList(vatican, angleterre,finlande,france,russie,italie,portugal);

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

        Comparator<Pays> comp = new ComparateurTotal();

        Collections.sort(lesPays,comp);

        assertEquals(lesPays, trie);
    }

    @Test
    public void testCompMedailles(){
        Pays france = new Pays("France");
        Pays angleterre = new Pays("Angleterre");
        Pays portugal = new Pays("Portugal");
        Pays italie = new Pays("Italie");
        Pays russie = new Pays("Russie");
        Pays vatican = new Pays("Vatican");
        Pays finlande = new Pays("Finlande");

        List<Pays> lesPays = Arrays.asList(france,angleterre,portugal,italie,russie,vatican,finlande);
        List<Pays> trie = Arrays.asList(vatican, france,portugal,angleterre,italie,russie,finlande);

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

        Comparator<Pays> comp = new ComparateurMedailles();

        Collections.sort(lesPays,comp);

        System.out.println(lesPays);
        
        assertEquals(lesPays, trie);
    }
}
