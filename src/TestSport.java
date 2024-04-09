// By julian

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestSport{
    @Test
    public void testSport() {
        Athlete michel = new Athlete( "balade","Michel",'M', 10, 18, 9);

        VoleyBall equipeVoley1 = new VoleyBall();        
        assertTrue(equipeVoley1.bareme(michel)>=4);
        assertTrue(equipeVoley1.bareme(michel)<=20);

        HandBall equipeHandBall1 = new HandBall();        
        assertTrue(equipeHandBall1.bareme(michel)>=4);
        assertTrue(equipeHandBall1.bareme(michel)<=20);

        Natation equipeNatation1 = new Natation();        
        assertTrue(equipeNatation1.bareme(michel)>=4);
        assertTrue(equipeNatation1.bareme(michel)<=20);

        Escrime equipeEscrime1 = new Escrime();        
        assertTrue(equipeEscrime1.bareme(michel)>=4);
        assertTrue(equipeEscrime1.bareme(michel)<=20);

        Athletisme equipeAthletisme1 = new Athletisme();        
        assertTrue(equipeAthletisme1.bareme(michel)>=4);
        assertTrue(equipeAthletisme1.bareme(michel)<=20);



        Athlete jean = new Athlete( "balade","jean",'M', 20, 6, 19);
        VoleyBall equipeVoley2 = new VoleyBall();        
        assertTrue(equipeVoley2.bareme(jean)>=4);
        assertTrue(equipeVoley2.bareme(jean)<=20);

        HandBall equipeHandBall2 = new HandBall();        
        assertTrue(equipeHandBall2.bareme(jean)>=4);
        assertTrue(equipeHandBall2.bareme(jean)<=20);

        Natation equipeNatation2 = new Natation();        
        assertTrue(equipeNatation2.bareme(jean)>=4);
        assertTrue(equipeNatation2.bareme(jean)<=20);

        Escrime equipeEscrime2 = new Escrime();        
        assertTrue(equipeEscrime2.bareme(jean)>=4);
        assertTrue(equipeEscrime2.bareme(jean)<=20);

        Athletisme equipeAthletisme2 = new Athletisme();        
        assertTrue(equipeAthletisme2.bareme(jean)>=4);
        assertTrue(equipeAthletisme2.bareme(jean)<=20);

        assertTrue(equipeVoley2.getNbParEquipe() == 6);
        assertTrue(equipeHandBall2.getNbParEquipe() == 1);
        assertTrue(equipeNatation2.getNbParEquipe() == 1);
        assertTrue(equipeEscrime2.getNbParEquipe() == 1);
        assertTrue(equipeAthletisme2.getNbParEquipe() == 1);

        assertTrue(equipeVoley2.getSport() == "Voley");
        assertTrue(equipeHandBall2.getSport() == "Handball");
        assertTrue(equipeNatation2.getSport() == "Natation");
        assertTrue(equipeEscrime2.getSport() == "Escrime");
        assertTrue(equipeAthletisme2.getSport() == "Athletisme");
    }
};
