
import org.junit.*;
import static org.junit.Assert.assertEquals;


public class TestEquipe {
    
    @Test
    public void test() {
        Equipe testEquipe = new Equipe("farfadetsMalicieux");
        testEquipe.ajouter(new Athlete("Marin", "farf", 'M', 5, 5, 5));
        assertEquals(testEquipe, testEquipe);
    }
}
