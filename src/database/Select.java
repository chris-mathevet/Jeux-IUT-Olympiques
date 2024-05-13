// by Julian
import java.sql.*;

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

    public void selectSport(){
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");

            String sqlSelectionSport = "SELECT * FROM SPORT";
            try (Statement statement = connexion.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlSelectionSport)) {
                while (resultSet.next()) {
                    int idSport = resultSet.getInt("idSport");
                    String nomSport = resultSet.getString("nomSport");
                    int nbParEquipe = resultSet.getInt("nbParEquipe");
                    System.out.println("id sport : " + idSport + ", nom sport : " + nomSport+ ", nombre par equipe : " + nbParEquipe);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des données de la table SPORT : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
    public void selectAthlete(){
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");

            String sqlSelectionAthlete = "SELECT * FROM ATHLETE";
            try (Statement statement = connexion.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlSelectionAthlete)) {
                while (resultSet.next()) {
                    
                    int idAthlete = resultSet.getInt("idAthlete");
                    String nom = resultSet.getString("nomAthlete");
                    String prenom = resultSet.getString("prenomAthlete");
                    String sexeString = resultSet.getString("sexe");
                    char sexe = ' ';
                    if (sexeString != null && !sexeString.isEmpty()) { // convertir le sexe de la base de donné qui est en Varchar (String) en un char 
                        sexe = sexeString.charAt(0);
                    }
                    int force = resultSet.getInt("capaciteForce");
                    int endurance = resultSet.getInt("endurance");
                    int agilite = resultSet.getInt("agilite");
                    System.out.println("idAthlete : " +idAthlete+ ", nom : " + nom + ", prenom : "+prenom +", sexe : "+ sexe + ", force : "+force + ", endurance : "+endurance + ", agilite : "+ agilite);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des données de la table ATHLETE : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
    public void rechercherJoueur(Integer id,String prenom, String nom) {
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
                int capaciteForce = resultSet.getInt("capaciteForce");
                int endurance = resultSet.getInt("endurance");
                int agilite = resultSet.getInt("agilite");
                int idPays = resultSet.getInt("idPays");
            
                System.out.println("ID: " + idAthlete + ", Nom: " + nomAthlete + ", Prénom: " + prenomAthlete +
                                   ", Sexe: " + sexe + ", Capacité de force: " + capaciteForce + ", Endurance: " +
                                   endurance + ", Agilité: " + agilite + ", ID Pays: " + idPays);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}