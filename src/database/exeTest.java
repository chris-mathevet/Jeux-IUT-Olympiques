package database;
import participants.Athlete;
import participants.Equipe;
import participants.Participant;
import participants.Pays;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExeTest {
    public static void main(String[] args) {
        List<Athlete> listeAthletes = new ArrayList<>();
        
        try {
            ConnexionMySql co = new ConnexionMySql();

            Requete r = new Requete(co);
            // Pays france = new Pays("France");
            // Athlete toi = new Athlete("le","rouix", 'M', 2,10,6,new Pays("France"));
            // Athlete lui = new Athlete("truc","hdsvfs", 'M', 2,10,6,new Pays("France"));
            // Athlete elle = new Athlete("jhigsj","fskj fqf", 'M', 2,10,6,new Pays("France"));
            // Athlete moi = new Athlete("marques","julian", 'M', 2,10,6,new Pays("France"));
            Pays italie = new Pays("Italie");
            Athlete toi = new Athlete("fabri","cation", 'M', 2,10,6,italie);


            try {
                System.out.println("debut boucle");    
                r.insertPays(italie);

                listeAthletes = r.selectAthlete();
                System.out.println("debut boucle");    

                for (Athlete athlete : listeAthletes) {
                    System.out.println("truc1"+athlete);    
                }
                // r.insertAthlete(toi);

                
            } catch (Exception e) {
                System.err.println("erreur selse"+e);
            }
            
        } catch (SQLException e) {
            System.err.println("euuuuuh pas trouvé, ca marche pas");
        }

    }
}
