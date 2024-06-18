package database;

import epreuves.*;
import exceptions.*;
import participants.*;
import sports.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
            String nomPays = resultSet.getString("nomPays");
            lesAthletes.add(new Athlete(nomAthlete, prenomAthlete, sexe, force, agilite, endurance, getPaysbyNom(nomPays)));
        }

        return lesAthletes;
    }

    public List<Athlete> rechercherAthletes(String nomPays, String prenom, String nom) throws SQLException {
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
            statement.setString(index++, nomPays);
        }
        if (prenom != null) {
            statement.setString(index++, prenom);
        }
        if (nom != null) {
            statement.setString(index++, nom);
        }

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String nomAthlete = resultSet.getString("nomAthlete");
            String prenomAthlete = resultSet.getString("prenomAthlete");
            String sexe = resultSet.getString("sexe");
            char sexeCaract = sexe.charAt(0);
            int capaciteForce = resultSet.getInt("capaciteForce");
            int endurance = resultSet.getInt("endurance");
            int agilite = resultSet.getInt("agilite");
            String nomPays2 = resultSet.getString("nomPays");

            lesAthletes.add(new Athlete(nomAthlete, prenomAthlete, sexeCaract, capaciteForce, agilite, endurance, getPaysbyNom(nomPays2)));
        }
        resultSet.close();
        statement.close();

        return lesAthletes;
    }
    public Pays getPaysbyNom(String nomPays) throws SQLException{
            
        Statement s=laConnexion.createStatement();
        ResultSet r=s.executeQuery("select * from PAYS where nomPays="+"\""+nomPays+"\""+";");
        r.next();
        String res = r.getString("nomPays"); 
        int or = r.getInt("nbMedailleOr"); 
        int argent = r.getInt("nbMedailleArgent"); 
        int bronze = r.getInt("nbMedaillebronze"); 
        r.close();
        return new Pays(res, or,argent,bronze);    
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

    public String getUser(String pseudo, int mdp) throws SQLException{ 
        Statement s=laConnexion.createStatement();
        ResultSet r=s.executeQuery("select * from USER where idPseudo="+"\""+pseudo+"\""+ "and mdp="+ mdp);
        r.next();
        String res = r.getString("type"); 
        r.close();
        return res;    
    }
    public void selectAllUser() throws SQLException{

		Statement s=laConnexion.createStatement();
		ResultSet rs=s.executeQuery("SELECT * FROM USER");
		while (rs.next()) {
            System.out.println("user : " + rs.getString("idPseudo") + " " + rs.getString("mdp") +" "+ rs.getString("email")+" " + rs.getString("type"));
		}
		rs.close();
    	
	}
    public Set<String> selectUser() throws SQLException{

        Set<String> lesUsers = new HashSet<>();

		Statement s=laConnexion.createStatement();
		ResultSet rs=s.executeQuery("SELECT * FROM USER");
		while (rs.next()) {
            lesUsers.add( rs.getString("idPseudo"));

		}
		rs.close();
    	return lesUsers ;
	}

    public Set<String> selectUserMail() throws SQLException{

        Set<String> lesMailsUser = new HashSet<>();

		Statement s=laConnexion.createStatement();
		ResultSet rs=s.executeQuery("SELECT * FROM USER");
		while (rs.next()) { 
            lesMailsUser.add( rs.getString("email"));
		}
		rs.close();
    	return lesMailsUser ;
	}

    //---------------Insert---------------------\\
    public void insertPays(Pays p) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO PAYS (nomPays, nbMedailleOr, nbMedailleArgent,nbMedailleBronze) VALUES (?, ?, ?, ?)");
        ps.setString(1, p.getNomPays());
		ps.setInt(2, p.getMedailleOr());
		ps.setInt(3, p.getMedailleArgent());
        ps.setInt(4, p.getMedailleBronze());
        ps.executeUpdate();
		ps.close();
    }

    public void insertEpreuve(Epreuve<?> e) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO EPREUVE (descriptionEpreuve, sexe, typeEpreuve, nomSport) VALUES (?, ?, ?, ?)");
        ps.setString(1,e.getDescription());
		ps.setString(2, e.getDescription());
		ps.setString(3, String.valueOf(e.getSexe()));
        ps.setString(4, e.getSport().getSport());
        ps.executeUpdate();
		ps.close();
    }

    public void insertUser(String pseudo,int mdp,String email,String type)throws SQLException{
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO USER (idPseudo, mdp, email, type) VALUES (?, ?, ?, ?)");
        ps.setString(1, pseudo);
		ps.setInt(2, mdp);
        ps.setString(3, email);

		ps.setString(4, type);
        ps.executeUpdate();
		ps.close();
    }

    public void insertEquipe(Equipe e) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO EQUIPE (nomEquipe) VALUES (?)");
        ps.setString(1, e.getNom());
        ps.executeUpdate();
		ps.close();
        for(Athlete a : e){
            ps = laConnexion.prepareStatement("INSERT INTO EST_CONSTITUE (nomEquipe,nom,prenom,sexe,nomPays) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, e.getNom());
            ps.setString(2, a.getNom());
            ps.setString(3, a.getPrenom());
            ps.setString(4, String.valueOf(a.getSexe()));
            ps.setString(5, a.getPays().getNomPays());
            ps.executeUpdate();
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
        ps.executeUpdate();
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

    public <T extends Participant> void ajoutParticipant(Manche<T> m) throws  SQLException {
        PreparedStatement ps;
        if(m.getEpreuve().getLesParticipants().get(0) instanceof Equipe){
            Equipe equipe;
            for(Participant p : m.getEpreuve().getLesParticipants()){
                equipe = (Equipe) p;
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_ATHLETE (nomManche, descriptionEpreuve, sexe, nomEquipe) VALUES (?, ?, ?, ?)");
                ps.setString(1, m.getNomDeTour());
                ps.setString(2, m.getEpreuve().getDescription());
                ps.setString(3, String.valueOf(m.getEpreuve().getSexe()));
                ps.setString(4, equipe.getNom());
                ps.executeUpdate();
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
                ps.executeUpdate();
                ps.close();
            }
        }
    }


    //---------------Modifieur------------------\\



}