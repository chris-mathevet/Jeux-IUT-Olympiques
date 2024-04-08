// By julian

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestSport{
    @Test
    public void test() {
        Athlete michel = new Athlete( "balade","Michel",'M', 10, 18, 9);

        VoleyBall equipeVoley1 = new VoleyBall(5, "Voley");        
        assertTrue(equipeVoley1.bareme(michel)>4);
        assertTrue(equipeVoley1.bareme(michel)<20);

        HandBall equipeHandBall1 = new HandBall(5, "HandBall");        
        assertTrue(equipeHandBall1.bareme(michel)>4);
        assertTrue(equipeHandBall1.bareme(michel)<20);

        Natation equipeNatation1 = new Natation(5, "Natation");        
        assertTrue(equipeNatation1.bareme(michel)>4);
        assertTrue(equipeNatation1.bareme(michel)<20);

        Escrime equipeEscrime1 = new Escrime(5, "Escrime");        
        assertTrue(equipeEscrime1.bareme(michel)>4);
        assertTrue(equipeEscrime1.bareme(michel)<20);

        Athletisme equipeAthletisme1 = new Athletisme(5, "Athletisme");        
        assertTrue(equipeAthletisme1.bareme(michel)>4);
        assertTrue(equipeAthletisme1.bareme(michel)<20);
    }
};
