
import org.junit.*;
import static org.junit.Assert.assertEquals;


public class TestEquipe {
    
    @Test
    public void test() {
        Athlete a1 = new Athlete("george", "martin", "m", 9, 8, 5);
        Athlete a2 = new Athlete("raphael", "nadal", "m", 9, 8, 5);
        Athlete a3 = new Athlete("sophie", "duke", "f", 9, 8, 5);
        Equipe testEquipe = new Equipe("farfadetsMalicieux");
        testEquipe.ajouter(a1);
      

    }
}
