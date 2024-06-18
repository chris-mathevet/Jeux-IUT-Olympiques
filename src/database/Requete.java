package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import epreuves.*;
import exceptions.*;
import participants.*;
import sports.*;
import participants.*;

public class Requete {
    private ConnexionMySql laConnexion;

    public Requete(ConnexionMySql co){
        this.laConnexion=co;
    }

    //---------------Select---------------------\\

    public List<Athlete> selectAthlete() throws SQLException{
        List<Athlete> lesAthletes= new ArrayList<>();
        
        
        String sqlSelectionAthlete = "SELECT * FROM ATHLETE";
        Statement s=laConnexion.createStatement();

        ResultSet resultSet = s.executeQuery(sqlSelectionAthlete);

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

        return lesAthletes;
    }

    public List<Athlete> rechercherJoueur(String nomPays, String prenom, String nom) throws SQLException {
        List<Athlete> lesAthletes = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ATHLETE WHERE ");
        boolean firstCondition = true;
        if (nomPays != null) {
            queryBuilder.append("nomPays = ? ");
            firstCondition = false;
        }
        if (prenom != null) {
            if (!firstCondition) {
                queryBuilder.append("AND ");
            }
            queryBuilder.append("prenomAthlete = ? ");
            firstCondition = false;
        }
        if (nom != null) {
            if (!firstCondition) {
                queryBuilder.append("AND ");
            }
            queryBuilder.append("nomAthlete = ? ");
        }

        PreparedStatement statement = laConnexion.prepareStatement(queryBuilder.toString());
        int index = 1;
        if (nomPays != null) {
            statement.setInt(index++, nomPays);
        }
        if (prenom != null) {
            statement.setString(index++, prenom);
        }
        if (nom != null) {
            statement.setString(index++, nom);
        }

        ResultSet resultSet = statement.executeQuery();
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

            lesAthletes.add(new Athlete(nomAthlete, prenomAthlete, sexeCaract, capaciteForce, agilite, endurance, getPaysbyId(idPays)));
        }
        resultSet.close();
        statement.close();

        return lesAthletes;
    }
    public Pays getPaysbyId(int id) throws SQLException{
            
        Statement s=laConnexion.createStatement();
        ResultSet r=s.executeQuery("select monPays from JOUEUR where id="+id);
        r.next();
        String res = r.getString("nomPays"); 
        r.close();
        return new Pays(res);    
    }

    public List<Pays> selectPays() throws SQLException{

        List<Pays> lesPays = new ArrayList<>();

		Statement s=laConnexion.createStatement();
		ResultSet rs=s.executeQuery("SELECT * FROM PAYS");
		while (rs.next()) {
            lesPays.add(new Pays(rs.getString("nomPays")));

			
		}
		rs.close();
    	return lesPays ;
	}

    
    //---------------Insert---------------------\\
    public void insertPays(Pays p) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO PAYS (nomPays, nbMedailleOr, nbMedailleArgent,nbMedailleBronze) VALUES (?, ?, ?, ?)");
        ps.setString(1, p.getNomPays());
		ps.setInt(2, p.getMedailleOr());
		ps.setInt(3, p.getMedailleArgent());
        ps.setInt(4, p.getMedailleBronze());
        ps.executeQuery();
		ps.close();
    }

    public void insertEpreuve(Epreuve e) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO EPREUVE (descriptionEpreuve, sexe, typeEpreuve, nomSport) VALUES (?, ?, ?, ?)");
        ps.setString(1,e.getDescription());
		ps.setString(2, e.getDescription());
		ps.setString(3, String.valueOf(e.getSexe()));
        ps.setString(4, e.getSport().getSport());
        ps.executeQuery();
		ps.close();
    }

    public void insertUser(String id,String mdp,String type)throws SQLException{
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO USER (idPseudo, mdp, type) VALUES (?, ?, ?)");
        ps.setString(1, id);
		ps.setString(2, mdp);
		ps.setString(3, type);
        ps.executeQuery();
		ps.close();
    }

    public void insertEquipe(Equipe e) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO EQUIPE (nomEquipe) VALUES (?)");
        ps.setString(1, e.getNom());
        ps.executeQuery();
		ps.close();
        for(Athlete a : e){
            ps = laConnexion.prepareStatement("INSERT INTO EST_CONSTITUE (nomEquipe,nom,prenom,sexe,nomPays) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, e.getNom());
            ps.setString(2, a.getNom());
            ps.setString(3, a.getPrenom());
            ps.setString(4, String.valueOf(a.getSexe()));
            ps.setString(5, a.getPays().getNomPays());
            ps.executeQuery();
            ps.close();
        }
    }

    public void insertAthlete(Athlete a) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO ATHLETE (nomAthlete, prenomAthlete, sexe, capaciteForce, endurance, agilite, nomPays) VALUES (?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, a.getNom());
		ps.setString(2, a.getPrenom());
        ps.setString(3, String.valueOf(a.getSexe()));
        ps.setInt(4, a.getForce());
        ps.setInt(5, a.getEndurance());
        ps.setInt(6, a.getAgilite());
        ps.setString(7, a.getPays().getNomPays());
        ps.executeQuery();
		ps.close();
    }

    public <T extends Participant> void insertManche(Manche<T> m) throws  SQLException {
        
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO MANCHE (nomManche, descriptionEpreuve, sexe, numeroManche) VALUES (?, ?, ?, ?)");
        ps.setString(1, m.getNomDeTour());
        ps.setString(2, m.getEpreuve().getDescription());
        ps.setString(3, String.valueOf(m.getEpreuve().getSexe()));
        ps.setInt(4, m.getNumeroDeTour());
        
    
        if(m.getEpreuve().getLesParticipants().get(0) instanceof Equipe){
            Equipe equipe;
            for(Participant p : m.getEpreuve().getLesParticipants()){
                equipe = (Equipe) p;
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_ATHLETE (nomManche, descriptionEpreuve, sexe, nomEquipe) VALUES (?, ?, ?, ?)");
                ps.setString(1, m.getNomDeTour());
                ps.setString(2, m.getEpreuve().getDescription());
                ps.setString(3, String.valueOf(m.getEpreuve().getSexe()));
                ps.setString(4, equipe.getNom());
                ps.executeQuery();
                ps.close();
            }
        }
        else{
            Athlete athleteInsert;
            for(Participant p:m.getEpreuve().getLesParticipants()){
                athleteInsert = (Athlete)p;
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_EQUIPE (nomManche, descriptionEpreuve, sexeEpreuve, nom, prenom, sexe,nomPays) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, m.getNomDeTour());
                ps.setString(2, m.getEpreuve().getDescription());
                ps.setString(3, String.valueOf(m.getEpreuve().getSexe()));
                ps.setString(4, athleteInsert.getNom());
                ps.setString(5, athleteInsert.getPrenom());
                ps.setString(6, String.valueOf(athleteInsert.getSexe()));
                ps.setString(7, athleteInsert.getPays().getNomPays());
                ps.executeQuery();
                ps.close();
            }
        }
 
    }


    //---------------Modifieur------------------\\



}