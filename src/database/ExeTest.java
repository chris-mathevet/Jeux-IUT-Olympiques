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

import epreuves.Epreuve;


public class ExeTest {
    public static void main(String[] args) {
        List<Athlete> listeAthletes = new ArrayList<>();
        Set<String> setUser = new HashSet<>();
        Set<String> setmail = new HashSet<>();
        
        try {
            ConnexionMySql co = new ConnexionMySql();

            Requete r = new Requete(co);
            

            try {  
                System.out.println("==========================================");
                r.clearAll();
                r.insertUser("Julian", 1323,"Julian@gmail.com", "admin");
                r.insertUser("truc", 1323,"truc@gmail.com", "visiteur");
                r.insertUser("michel", 1323, "michel@gmail.com", "organisateur");
                System.out.println(r.getUser("truc",1323));
                System.out.println(r.getUser("michel",1323));

                
                setUser = r.selectUser();
                for (String user : setUser) {
                    System.out.println("user : "+user);    
                }
                
                setmail = r.selectUserMail();
                for (String mail : setmail) {
                    System.out.println("mail : " + mail);    
                }

                setmail = r.selectUserMail();
                for (String mail : setmail) {
                    System.out.println("mail : " + mail);    
                }
                System.out.println("tous les users");
                r.selectAllUser();
            } catch (SQLException e) {
                System.err.println("USER");
                System.err.println("erreur selse"+e);
            }
            try {
                System.out.println("==========================================");

                Pays france = new Pays("France");
                Pays russie = new Pays("Russie");
                Pays italie = new Pays("Italie");
                r.insertPays(france);
                r.insertPays(russie);
                r.insertPays(italie);
                
                Athlete raphael  = new Athlete("Raphael", "Nadal", 'H', 9, 8, 5, france);
                Athlete sophie  = new Athlete("Sophie", "Duke", 'F', 9, 8, 5, russie);
                Athlete thomas  = new Athlete("Thomas", "King", 'F', 9, 8, 5, russie);
                Athlete harry  = new Athlete("Harry", "Potter", 'H', 9, 8, 5, italie);
    
                r.insertAthlete(raphael);
                r.insertAthlete(sophie);
                r.insertAthlete(thomas);
                r.insertAthlete(harry);
                List<Athlete> res = r.selectAthlete();
                for(Athlete a:res){
                    System.out.println(a);
                }
                System.out.println("--------------------------------------");
                System.out.println(r.rechercherAthletes(null,null,null,"russie"));
                System.out.println("--------------------------------------");
                System.out.println(r.rechercherAthletes(null,"Duke",null,null));
                System.out.println("--------------------------------------");
                System.out.println(r.rechercherAthletes("Raphael",null,null,null));
                System.out.println("--------------------------------------");
                System.out.println(r.rechercherAthletes(null,null,"H",null));
                System.out.println("--------------------------------------");
                System.out.println(r.rechercherAthletes(null,null,null,null));
                
                System.out.println("==========================================");

                try {
                    r.insertAllEpreuve();
                    
                } catch (Exception e) {
                    System.err.println(e);
                }
                System.out.println("--------------------------------------");

                List<Epreuve<Participant>> truc = r.selectEpreuves();

                for (Epreuve<?> epreuve : truc) {
                    System.out.println(epreuve);
                }
                System.out.println("--------------------------------------");

                r.csvToBd("donnees.csv");
                res = r.selectAthlete();
                for(Athlete a:res){
                    System.out.println(a);
                }
                System.out.println("--------------------------------------");
            }
            catch(SQLException e){
                System.err.println("Athlete");
                System.err.println("erreur selse"+e);
            }
        } catch (SQLException e) {
            System.err.println("euuuuuh pas trouvé, ca marche pas");
        }

    }
}
