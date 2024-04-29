import java.sql.*;


public class Database {
    public static void main(String[] args) {
        // Select lesjoueurs = new Select("jdbc:mysql://cigale1.lescigales.org:3310/s4a_julian", "s4a_julian", "VUpitdBD");
        Select lesjoueurs = new Select();
        // public Select(String url, String user, String mdp ){
        //     this.url = url;
        //     this.utilisateur= user;
        //     this.motDePasse= mdp;
        // }
        // public Select(){
        //     this.url = "jdbc:mysql://localhost:3306/saejava";
        //     this.utilisateur = "root";
        //     this.motDePasse = "marques";
        // // lesjoueurs.selectAthlete();
        
        // System.out.println(lesjoueurs.getMdp());
        
        // System.out.println(lesjoueurs.getUrl());
        
        // System.out.println(lesjoueurs.getUser());
        
        lesjoueurs.selectSport();
        
        // lesjoueurs.rechercherJoueur(null, null,"Moreau");

        // lesjoueurs.rechercherJoueur(9, null, null);

        // lesjoueurs.rechercherJoueur(null, "Yuki", null);
        // lesjoueurs.rechercherJoueur(null, "Wang", null);
        
        // Insert newInsert = new Insert();
        // newInsert.insertAthlete(12, "marques", "julian", 'M', 10, 11, 18, 1);
        // newInsert.insertEpreuve(0, "null", "solo", 0);
        // newInsert.insertEquipe(0, "les mich-michs");
        // newInsert.insertSport(9, "ping-pong", 1);
    }
}


