package database;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class Testdatabase{
    @Test
    public void testSport() {
        Select lesjoueurs = new Select();

        assertTrue(lesjoueurs.getMdp() == " ");
        assertTrue(lesjoueurs.getUrl() == "jdbc:mysql://localhost:3306/saejava");
        assertTrue(lesjoueurs.getUser() == "root");

    }
};
