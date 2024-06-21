package database;
import participants.Athlete;
import participants.Equipe;
import participants.Participant;
import participants.Pays;
import sports.VoleyBall;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.ParagraphView;

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
                System.out.println("debut boucle");    
                // r.clearAll();
            //     r.insertUser("i_am_julian", "Salut@1aaa".hashCode()*97,"marquesjulian26@gmail.com", "admin");
            //     r.insertUser("Slyjack999", "Salut@1aaa".hashCode()*97,"slyjack999@gmail.com", "visiteur");
            //     r.insertUser("Axel1234", "Salut@1aaa".hashCode()*97, "hacksel002@gmail.com", "visiteur");
            //     r.insertUser("trucmoihaha", "Salut@1aaa".hashCode()*97, "truc@gmail.com", "organisateur");
            //     System.out.println(r.getUser("truc",1323));
            //     System.out.println(r.getUser("michel",1323));

                r.selectAllUser();
                r.updateUser("a", "admin");
                r.selectAllUser();
                
                // setUser = r.selectUser();
                // for (String user : setUser) {
                //     System.out.println("user : "+user);    
                // }
            //     System.out.println("==========================================");
            //     setmail = r.selectUserMail();
            //     for (String mail : setmail) {
            //         System.out.println("mail : " + mail);    
            //     }
            //     System.out.println("==========================================");
            //     setmail = r.selectUserMail();
            //     for (String mail : setmail) {
            //         System.out.println("mail : " + mail);    
            //     }
            //     System.out.println("==========================================");
            //     System.out.println("tous les users");
            //     r.selectAllUser();
            // } catch (SQLException e) {
            //     System.err.println("USER");
            //     System.err.println("erreur selse"+e);
            // }
            // try {
            //     System.out.println("==========================================");

            //     Pays france = new Pays("France");
            //     Pays russie = new Pays("Russie");
            //     Pays italie = new Pays("Italie");
            //     r.insertPays(france);
            //     r.insertPays(russie);
            //     r.insertPays(italie);
                
            //     Athlete raphael  = new Athlete("Raphael", "Nadal", 'H', 9, 8, 5, france);
            //     Athlete sophie  = new Athlete("Sophie", "Duke", 'F', 9, 8, 5, russie);
            //     Athlete thomas  = new Athlete("Thomas", "King", 'F', 9, 8, 5, russie);
            //     Athlete harry  = new Athlete("Harry", "Potter", 'H', 9, 8, 5, italie);
    
            //     r.insertAthlete(raphael);
            //     r.insertAthlete(sophie);
            //     r.insertAthlete(thomas);
            //     r.insertAthlete(harry);
            //     List<Athlete> res = r.selectAthlete();
            //     for(Athlete a:res){
            //         System.out.println(a);
            //     }
            //     System.out.println("--------------------------------------");
            //     System.out.println(r.rechercherAthletes(null,null,null,"russie"));
            //     System.out.println("--------------------------------------");
            //     System.out.println(r.rechercherAthletes(null,"Duke",null,null));
            //     System.out.println("--------------------------------------");
            //     System.out.println(r.rechercherAthletes("Raphael",null,null,null));
            //     System.out.println("--------------------------------------");
            //     System.out.println(r.rechercherAthletes(null,null,"H",null));
            //     System.out.println("--------------------------------------");
            //     System.out.println(r.rechercherAthletes(null,null,null,null));
                
            //     System.out.println("==========================================");

            //     try {
            //         r.insertAllEpreuve();
                    
            //     } catch (Exception e) {
            //         System.err.println(e);
            //     }
            //     System.out.println("--------------------------------------");

            //     List<Epreuve<?>> truc = r.selectEpreuves();


                // List<Epreuve<Participant>> truc = r.selectEpreuves();
                // r.insertPays(new Pays("salutd"));
            //     for (Epreuve<?> epreuve : truc) {
            //         System.out.println(epreuve);
            //     }

                // r.insertPays(new Pays("C'est mua"));

            //     r.csvToBd("donnees.csv");
            //     res = r.selectAthlete();
            //     for(Athlete a:res){
            //         System.out.println(a);
            //     }

            // r.insertEpreuve(new Epreuve<>("mama la truc",new VoleyBall(), 'F'));
            // List<Equipe> lesEquipes = r.selectEquipe();

            // for (Equipe equipe : lesEquipes) {
            //     System.out.println("l'equipe : :" + equipe);
            // }

            // List<Athlete> truuuuc1 = r.rechercherAthletes(null, null, "F", null);

            List<Equipe> truuuuc = r.selectEquipe();
            for (Equipe equipe : truuuuc) {
                System.out.println("equipe" + equipe);

                // for (Athlete athlete : truuuuc1) {
                //     r.insertToEquipe(equipe,athlete);                
                // }
            }

            List<Athlete> truuuuc2 = r.selectAthlete();
            for (Athlete athe : truuuuc2) {
                System.out.println("equipe" + athe);
            }

                System.out.println(r.getDrapeau("France"));
                System.out.println(r.getDrapeau("Italy"));
                System.out.println(r.getDrapeau("Italie"));
                System.out.println(r.getDrapeau("Angola"));
                System.out.println(r.getDrapeau("Japan"));
                System.out.println(r.getDrapeau("Panama"));
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
