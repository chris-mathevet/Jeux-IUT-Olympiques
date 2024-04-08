
import org.junit.*;
import static org.junit.Assert.assertEquals;


public class TestEquipe {
    
    @Test
    public void test() {
        Equipe testEquipe = new Equipe("farfadetsMalicieux");
        testEquipe.getNom().equals(testEquipe.getNom());
    }
}
