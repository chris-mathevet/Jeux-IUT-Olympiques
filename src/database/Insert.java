// by Julian

import java.sql.*;

public class Insert {
    
    // Informations de connexion
    private String url;
    // SHOW VARIABLES LIKE 'port';  // ===> port du serveur a use
    // saejava ==> nom de la base a utilisé 
    private String utilisateur;
    private String motDePasse ;

    
    public Insert(String url, String user, String mdp ){
        this.url = url;
        this.utilisateur= user;
        this.motDePasse= mdp;
    }
    public Insert(){        
        this.url = "jdbc:mysql://localhost:3306/saejava";
        this.utilisateur = "root";
        this.motDePasse = " ";
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

    public void insertSport(int idSport, String nomSport, int nbParEquipe){
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");
            
            // Insertion de données
            String sqlInsertionSport = "INSERT INTO SPORT (idSport, nomSport, nbParEquipe) VALUES (?, ?, ?)";
            // String sqlInsertion = "INSERT INTO table1 (colonne1, colonne2) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlInsertionSport)) {
                preparedStatement.setInt(1, idSport);
                preparedStatement.setString(2, nomSport);
                preparedStatement.setInt(3, nbParEquipe);
                preparedStatement.executeUpdate();
                System.out.println("Données insérées avec succès !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'insertion d'un sport : " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public void insertEpreuve(int idEpreuve, String description, String typeEpreuve, int idSport){
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");
            
            // Insertion de données
            String sqlInsertionEpreuve = "INSERT INTO Epreuve (idEpreuve, descriptionEpreuve, typeEpreuve, idSport) VALUES (?, ?, ?, ?)";
            // String sqlInsertion = "INSERT INTO table1 (colonne1, colonne2) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlInsertionEpreuve)) {
                preparedStatement.setInt(1, idEpreuve);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, typeEpreuve);
                preparedStatement.setInt(4, idSport);

                preparedStatement.executeUpdate();
                System.out.println("Données insérées avec succès !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'insertion d'une epreuve : " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public void insertEquipe(int idEquipe, String nomEquipe){
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");
            
            // Insertion de données
            String sqlInsertionEquipe = "INSERT INTO EQUIPE (idEquipe,nomEquipe) VALUES (?, ?)";
            // String sqlInsertion = "INSERT INTO table1 (colonne1, colonne2) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlInsertionEquipe)) {
                preparedStatement.setInt(1, idEquipe);
                preparedStatement.setString(2, nomEquipe);
                preparedStatement.executeUpdate();
                System.out.println("Données insérées avec succès !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'insertion d'une equipe : " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public void insertAthlete(int idAthlete, String nom, String prenom, char sexe, int force, int endurance, int agilite, int idPays){
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");
            
            // Insertion de données
            String sqlInsertionAthlete = "INSERT INTO ATHLETE (idAthlete, nomAthlete, prenomAthlete, sexe, capaciteForce, endurance, agilite, idPays) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            // String sqlInsertion = "INSERT INTO table1 (colonne1, colonne2) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlInsertionAthlete)) {
                preparedStatement.setInt(1, idAthlete);
                preparedStatement.setString(2, nom);
                preparedStatement.setString(3, prenom);
                preparedStatement.setString(4, String.valueOf(sexe)); // set string mais sexe est un char donc faut le convertir en string
                preparedStatement.setInt(5, force);
                preparedStatement.setInt(6, endurance);
                preparedStatement.setInt(7, agilite);
                preparedStatement.setInt(8,idPays);
                preparedStatement.executeUpdate();
                System.out.println("Données insérées avec succès !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'insertion de l'athlete: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }   
}
