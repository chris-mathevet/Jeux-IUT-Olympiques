package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import participants.Athlete;
import participants.Pays;
import sports.Sport;

public class Select {
    
    // info de connexion
    private String url;
    // SHOW VARIABLES LIKE 'port';  // ===> port du serveur a use
    // saejava ==> nom de la base a utilisé 
    private String utilisateur;
    private String motDePasse ;

    
    public Select(String url, String user, String mdp ){
        this.url = url;
        this.utilisateur= user;
        this.motDePasse= mdp;
    }
    public Select(){
        this.url = "jdbc:mysql://localhost:3306/saejava";
        this.utilisateur = "root";
        this.motDePasse = "password";
    }

    public String getUrl(){
        return this.url;
    }
    public String getUser(){
        return this.utilisateur;
    }
    public String getMdp(){
        return this.motDePasse;
    }

    public List<Athlete> selectAthlete(){
        List<Athlete> lesAthletes= new ArrayList<>();
        
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");

            String sqlSelectionAthlete = "SELECT * FROM ATHLETE";
            try (Statement statement = connexion.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlSelectionAthlete)) {
                while (resultSet.next()) {
                    
                    int idAthlete = resultSet.getInt("idAthlete");
                    String nomAthlete = resultSet.getString("nomAthlete");
                    String prenomAthlete = resultSet.getString("prenomAthlete");
                    String sexeString = resultSet.getString("sexe");
                    char sexe = ' ';
                    if (sexeString != null && !sexeString.isEmpty()) { // convertir le sexe de la base de donné qui est en Varchar (String) en un char 
                        sexe = sexeString.charAt(0);
                    }
                    int force = resultSet.getInt("capaciteForce");
                    int endurance = resultSet.getInt("endurance");
                    int agilite = resultSet.getInt("agilite");
                    int idPays = resultSet.getInt("idPays");
                    lesAthletes.add(new Athlete(nomAthlete, prenomAthlete, sexe, force, agilite, endurance, getPaysbyId(idPays)));

                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des données de la table ATHLETE : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
        return lesAthletes;
    }
    public List<Athlete> rechercherJoueur(Integer id,String prenom, String nom) {
        List<Athlete> lesAthletes= new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ATHLETE WHERE ");
        if (id != null) {
            queryBuilder.append("idAthlete = ? ");
        }
        if (prenom != null) {
            if (id != null) {
                queryBuilder.append("AND ");
            }
            queryBuilder.append("prenomAthlete = ? ");
        }
        if (nom != null) {
            if (id != null || prenom != null) {
                queryBuilder.append("AND ");
            }
            queryBuilder.append("nomAthlete = ? ");
        }

        
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)){
            PreparedStatement statement = connexion.prepareStatement(queryBuilder.toString());
            int parameterIndex = 1;
            if (prenom != null) {
                statement.setString(parameterIndex++, prenom);
            }
            if (nom != null) {
                statement.setString(parameterIndex++, nom);
            }
            if (id != null) {
                statement.setInt(parameterIndex, id);
            }
            
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            
            while (resultSet.next()) {
                int idAthlete = resultSet.getInt("idAthlete");
                String nomAthlete = resultSet.getString("nomAthlete");
                String prenomAthlete = resultSet.getString("prenomAthlete");
                String sexe = resultSet.getString("sexe");
                char sexeCaract = sexe.charAt(0);
                int capaciteForce = resultSet.getInt("capaciteForce");
                int endurance = resultSet.getInt("endurance");
                int agilite = resultSet.getInt("agilite");
                int idPays = resultSet.getInt("idPays");
                //  Athlete(String nom, String prenom, char sexe, int force, int agilite, int endurance, Pays pays) {

                lesAthletes.add(new Athlete(nomAthlete, prenomAthlete, sexeCaract, capaciteForce, agilite, endurance, getPaysbyId(idPays)));
                System.out.println("ID: " + idAthlete + ", Nom: " + nomAthlete + ", Prénom: " + prenomAthlete +
                                   ", Sexe: " + sexe + ", Capacité de force: " + capaciteForce + ", Endurance: " +
                                   endurance + ", Agilité: " + agilite + ", ID Pays: " + idPays);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesAthletes;
    }
    
    public Pays getPaysbyId(int id){
            
        Statement s=laConnexion.createStatement();
        ResultSet r=s.executeQuery("select monPays from JOUEUR where id="+id);
        r.next();
        String res = r.getString("nomPays"); 
        r.close();
        return new Pays(res);    
    }

    public List<Pays> selectPays(){
        List<Pays> lesPays = new ArrayList<>();
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");

            String sqlSelectionSport = "SELECT * FROM PAYS";
            try (Statement statement = connexion.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlSelectionSport)) {
                while (resultSet.next()) {
                    int idPays = resultSet.getInt("idPays");
                    String nomPays = resultSet.getString("nomPays");
                    
                    lesPays.add(new Pays(nomPays));

                    System.out.println("id pays : " + idPays + ", nom pays : " + nomPays);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des données de la table SPORT : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
        return lesPays;
    }

}