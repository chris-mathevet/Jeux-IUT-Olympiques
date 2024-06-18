package database;
import participants.Athlete;
import participants.Equipe;
import participants.Participant;
import participants.Pays;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ExeTest {
    public static void main(String[] args) {
        List<Athlete> listeAthletes = new ArrayList<>();
        Set<String> setUser = new HashSet<>();
        Set<String> setmail = new HashSet<>();
        
        try {
            ConnexionMySql co = new ConnexionMySql();

            Requete r = new Requete(co);
            

            try {
                System.out.println("debut boucle");    
                // r.insertUser("Julian", 1323,"truc@gmail.com", "admin");
                // r.insertUser("truc", 1323,"truc@gmail.com", "visiteur");
                // r.insertUser("michel", 1323, "truc@gmail.com", "organisateur");
                System.out.println(r.getUser("truc",1323));
                System.out.println(r.getUser("michel",1323));

                
                setUser = r.selectUser();
                for (String user : setUser) {
                    System.out.println("user : "+user);    
                }
                System.out.println("==========================================");
                setmail = r.selectUserMail();
                for (String mail : setmail) {
                    System.out.println("mail : " + mail);    
                }
                System.out.println("==========================================");
                setmail = r.selectUserMail();
                for (String mail : setmail) {
                    System.out.println("mail : " + mail);    
                }
                System.out.println("==========================================");
                System.out.println("tous les users");
                r.selectAllUser();
                // r.insertAthlete(toi);

                // for (Athlete athlete : listeAthletes) {
                //     System.out.println("truc1"+athlete);    
                // }
                // // r.insertAthlete(toi);


            } catch (Exception e) {
                System.err.println("erreur selse"+e);
            }
            
        } catch (SQLException e) {
            System.err.println("euuuuuh pas trouvé, ca marche pas");
        }

    }
}
