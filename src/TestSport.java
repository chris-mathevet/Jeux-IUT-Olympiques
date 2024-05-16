// By julian

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestSport{
    @Test
    public void testSport() {
        Athlete michel = new Athlete( "balade","Michel",'M', 10, 18, 9, null);

        VoleyBall equipeVoley1 = new VoleyBall();        
        // System.out.println("equipe a un score de :"+equipeVoley1.bareme(michel));
        assertTrue(equipeVoley1.bareme(michel)>=4);
        assertTrue(equipeVoley1.bareme(michel)<=200);

        HandBall equipeHandBall1 = new HandBall();        
        // System.out.println("equipe a un score de :"+equipeHandBall1.bareme(michel));
        assertTrue(equipeHandBall1.bareme(michel)>=4);
        assertTrue(equipeHandBall1.bareme(michel)<=200);

        Natation equipeNatation1 = new Natation();        
        // System.out.println("equipe a un score de :"+equipeNatation1.bareme(michel));
        assertTrue(equipeNatation1.bareme(michel)>=4);
        assertTrue(equipeNatation1.bareme(michel)<=200);

        Escrime equipeEscrime1 = new Escrime();        
        // System.out.println("equipe a un score de :"+equipeEscrime1.bareme(michel));
        assertTrue(equipeEscrime1.bareme(michel)>=4);
        assertTrue(equipeEscrime1.bareme(michel)<=200);

        Athletisme equipeAthletisme1 = new Athletisme();        
        // System.out.println("equipe a un score de :"+equipeAthletisme1.bareme(michel));
        assertTrue(equipeAthletisme1.bareme(michel)>=4);
        assertTrue(equipeAthletisme1.bareme(michel)<=200);



        Athlete jean = new Athlete( "balade","jean",'M', 20, 6, 19, null);
        VoleyBall equipeVoley2 = new VoleyBall();    
        // System.out.println("equipe a un score de :"+equipeVoley2.bareme(michel));
        assertTrue(equipeVoley2.bareme(jean)>=4);
        assertTrue(equipeVoley2.bareme(jean)<=200);

        HandBall equipeHandBall2 = new HandBall();  
        // System.out.println("equipe a un score de :"+equipeHandBall2.bareme(michel));
        assertTrue(equipeHandBall2.bareme(jean)>=4);
        assertTrue(equipeHandBall2.bareme(jean)<=200);

        Natation equipeNatation2 = new Natation();   
        // System.out.println("equipe a un score de :"+equipeNatation2.bareme(michel));
        assertTrue(equipeNatation2.bareme(jean)>=4);
        assertTrue(equipeNatation2.bareme(jean)<=200);

        Escrime equipeEscrime2 = new Escrime();        
        // System.out.println("equipe a un score de :"+equipeEscrime2.bareme(michel));
        assertTrue(equipeEscrime2.bareme(jean)>=4);
        assertTrue(equipeEscrime2.bareme(jean)<=200);

        Athletisme equipeAthletisme2 = new Athletisme();        
        // System.out.println("equipe a un score de :"+equipeAthletisme2.bareme(michel));
        assertTrue(equipeAthletisme2.bareme(jean)>=4);
        assertTrue(equipeAthletisme2.bareme(jean)<=200);

        assertTrue(equipeVoley2.getNbParEquipe() == 6);
        assertTrue(equipeHandBall2.getNbParEquipe() == 7);
        assertTrue(equipeNatation2.getNbParEquipe() == 1);
        assertTrue(equipeEscrime2.getNbParEquipe() == 1);
        assertTrue(equipeAthletisme2.getNbParEquipe() == 1);
        // System.out.println(equipeVoley2.getNbParEquipe());
        // System.out.println(equipeHandBall2.getNbParEquipe());
        // System.out.println(equipeNatation2.getNbParEquipe());
        // System.out.println(equipeEscrime2.getNbParEquipe());
        // System.out.println(equipeAthletisme2.getNbParEquipe());

        assertTrue(equipeVoley2.getSport() == "Voley");
        assertTrue(equipeHandBall2.getSport() == "Handball");
        assertTrue(equipeNatation2.getSport() == "Natation");
        assertTrue(equipeEscrime2.getSport() == "Escrime");
        assertTrue(equipeAthletisme2.getSport() == "Athletisme");
        // System.out.println(equipeVoley2.getSport());
        // System.out.println(equipeHandBall2.getSport());
        // System.out.println(equipeNatation2.getSport());
        // System.out.println(equipeEscrime2.getSport());
        // System.out.println(equipeAthletisme2.getSport());
    }
};
