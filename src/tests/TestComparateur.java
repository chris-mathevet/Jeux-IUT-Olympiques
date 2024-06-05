package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
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
        
    }

    @Test
    public void testCompMedailles(){
        
    }
}
