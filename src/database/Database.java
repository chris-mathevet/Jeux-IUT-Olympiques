// by Julian
import java.sql.*;

public class Database {
    public static void main(String[] args) {
        Select lesjoueurs = new Select();

        // lesjoueurs.selectAthlete();
        
        System.out.println(lesjoueurs.getMdp());
        
        System.out.println(lesjoueurs.getUrl());
        
        System.out.println(lesjoueurs.getUser());
        
        lesjoueurs.selectSport();
        lesjoueurs.rechercherJoueur(null, null,"Moreau");

        lesjoueurs.rechercherJoueur(9, null, null);

        lesjoueurs.rechercherJoueur(null, "Yuki", null);
        lesjoueurs.rechercherJoueur(null, "Wang", null);


    }
}


