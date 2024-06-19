package database;

import epreuves.*;
import exceptions.*;
import participants.*;
import sports.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Sport convertNomVersSport(String s){
        switch (s) {
            case "Athletisme":
                return new Athletisme();
                

            case "Escrime":
                return new Escrime();
                

            case "Handball":
                return new HandBall();
                

            case "Voley-Ball":
            case "Voley":
                return new VoleyBall();
                

            default:
                return new Natation();  
        }
    }


    public List<Epreuve<?>> selectEpreuves() throws SQLException{
        List<Epreuve<?>> lesEpreuves= new ArrayList<>();
        
        
        String sqlSelectionAthlete = "SELECT * FROM EPREUVE";
        Statement s=laConnexion.createStatement();

        ResultSet resultSet = s.executeQuery(sqlSelectionAthlete);

        while (resultSet.next()) {
            
            String descriptionEpreuve = resultSet.getString("descriptionEpreuve");
            String sexeString = resultSet.getString("sexe");
            char sexe = ' ';
            if (sexeString != null && !sexeString.isEmpty()) { // convertir le sexe de la base de donné qui est en Varchar (String) en un char 
                sexe = sexeString.charAt(0);
            }
            String nomSport = resultSet.getString("nomSport");

    
            lesEpreuves.add(new Epreuve<>(descriptionEpreuve,convertNomVersSport(nomSport),sexe));
        }

        return lesEpreuves;
    }

    
    public List<Athlete> rechercherAthletes(String nom , String prenom, String sexe, String nomPays) throws SQLException {
        List<Athlete> lesAthletes = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ATHLETE");
        boolean firstCondition = true;
        if (nom != null || prenom != null || sexe != null || nomPays != null) {
            queryBuilder.append(" WHERE ");
    
        }
        if (nom != null) {
            queryBuilder.append("nomAthlete = ? ");
            firstCondition = false;
        }
        if (prenom != null) {
            if (!firstCondition) {
                queryBuilder.append("AND ");
            }
            queryBuilder.append("prenomAthlete = ? ");
            firstCondition = false;
        }
        if ( sexe != null) {
            if (!firstCondition) {
                queryBuilder.append("AND ");
            }
            queryBuilder.append("sexe = ? ");
        }
        if (nomPays != null) {
            if (!firstCondition) {
                queryBuilder.append("AND ");
            }
            queryBuilder.append("nomPays = ? ");
        }
    
        PreparedStatement statement = laConnexion.prepareStatement(queryBuilder.toString());
        int index = 1;
        if (nom != null) {
            statement.setString(index++, nom);
        }
        if (prenom != null) {
            statement.setString(index++, prenom);
        }
        if (sexe != null) {
            statement.setString(index++,sexe );
        }
    
        if (nomPays != null) {
            statement.setString(index++,nomPays);
        }
    
        ResultSet resultSet = statement.executeQuery();
    
        while (resultSet.next()) {
            String nomAthlete = resultSet.getString("nomAthlete");
            String prenomAthlete = resultSet.getString("prenomAthlete");
            String sexe2 = resultSet.getString("sexe");
            char sexeCaract = sexe2.charAt(0);
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

    public String getDrapeau(String nomPays) throws SQLException{
            
        Statement s=laConnexion.createStatement();
        ResultSet r=s.executeQuery("select * from DRAPEAU where nomPays="+"\""+nomPays+"\""+";");
        r.next();
        String pathImg = r.getString("pathImg"); 

        r.close();
        return pathImg;    
    }

    public Epreuve<?> getEpreuvebyDescpt(String descriptionEpreuve) throws SQLException{
            
        Statement s=laConnexion.createStatement();
        ResultSet r=s.executeQuery("select * from EPREUVE where descriptionEpreuve="+"\""+descriptionEpreuve+"\""+";");
        r.next();
        String descripEpreuve = r.getString("descriptionEpreuve");
        String sexeString = r.getString("sexe");
        char sexe = ' ';
        if (sexeString != null && !sexeString.isEmpty()) { // convertir le sexe de la base de donné qui est en Varchar (String) en un char 
            sexe = sexeString.charAt(0);
        }
        String nomSport = r.getString("nomSport");


        return new Epreuve<>(descripEpreuve,convertNomVersSport(nomSport),sexe); 
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

    public List<Equipe> selectEquipe() throws SQLException{
        List<Equipe> res = new ArrayList<>();
        Statement s=laConnexion.createStatement();
        ResultSet rs=s.executeQuery("SELECT * FROM EQUIPE");
        ResultSet rs2;
        int cpt = 0;
        while (rs.next()) { 
            res.add(new Equipe(rs.getString("nomEquipe"))); 
            rs2 =s.executeQuery("SELECT * FROM EQUIPE NATURAL JOIN ATHLETE WHERE nomEquipe="+"\""+rs.getString("nomEquipe")+"\""); 
            while (rs2.next()) {
                for(Athlete a:this.rechercherAthletes(rs2.getString("nomAthlete"),rs2.getString("prenomAthlete"),rs2.getString("sexe"),rs2.getString("nomPays"))){
                    res.get(cpt).add(a);
                }
            }
            cpt++;
		}
        return res;
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
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO EPREUVE (descriptionEpreuve, sexe, nomSport) VALUES (?, ?, ?)");
        ps.setString(1, e.getDescription());
		ps.setString(2, String.valueOf(e.getSexe()));
        ps.setString(3, e.getSport().getSport());
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

    public void insertAllEpreuve() throws SQLException {
        List<Epreuve<Participant>> lesEpreuves = new ArrayList<>(Arrays.asList(
            new Epreuve<>("Natation 100 brasse", new Natation(), 'H'),
            new Epreuve<>("Natation 100 brasse", new Natation(), 'F'),
            new Epreuve<>("Natation relais libre", new Natation(), 'H'),
            new Epreuve<>("Natation relais libre", new Natation(), 'F'),
            new Epreuve<>("Handball", new HandBall(), 'H'),
            new Epreuve<>("Handball", new HandBall(), 'F'),
            new Epreuve<>("Volley-Ball", new VoleyBall(), 'H'),
            new Epreuve<>("Volley-Ball", new VoleyBall(), 'F'),
            new Epreuve<>("Escrime fleuret", new Escrime(), 'H'),
            new Epreuve<>("Escrime fleuret", new Escrime(), 'F'),
            new Epreuve<>("Escrime épée", new Escrime(), 'H'),
            new Epreuve<>("Escrime épée", new Escrime(), 'F'),
            new Epreuve<>("Athétisme 110 haies", new Athletisme(), 'H'),
            new Epreuve<>("Athétisme 110 haies", new Athletisme(), 'F'),
            new Epreuve<>("Athlétisme relais 400m", new Athletisme(), 'H'),
            new Epreuve<>("Athlétisme relais 400m", new Athletisme(), 'F')
        ));
        
        PreparedStatement ps;
        for (Epreuve<?> epreuve : lesEpreuves) {
            ps = laConnexion.prepareStatement("INSERT INTO EPREUVE (descriptionEpreuve, sexe, nomSport) VALUES (?, ?, ?)");
            System.out.println("epreuve :" + epreuve);
            ps.setString(1, epreuve.getDescription());
            ps.setString(2, String.valueOf(epreuve.getSexe()));
            ps.setString(3, epreuve.getSport().getSport());

            ps.executeUpdate();
            ps.close();
        }
    }
    
    //---------------Modifieur-------------------\\
    //---------------Delete----------------------\\
    public void effacerAthlete(Athlete a) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("delete from PARTICIPER_ATHLETE where nom = ? and prenom = ? and sexe = ? and nomPays = ?");
        ps.setString(1, a.getNom());
        ps.setString(2, a.getPrenom());
        ps.setString(3, String.valueOf(a.getSexe()));
        ps.setString(4, a.getPays().getNomPays());
        ps.executeUpdate();
        ps.close();

        ps = laConnexion.prepareStatement("delete from PARTICIPER_ATHLETE where nom = ? and prenom = ? and sexe = ? and nomPays = ?");
        ps.setString(1, a.getNom());
        ps.setString(2, a.getPrenom());
        ps.setString(3, String.valueOf(a.getSexe()));
        ps.setString(4, a.getPays().getNomPays());
        ps.executeUpdate();
        ps.close();

		ps = laConnexion.prepareStatement("delete from EST_CONSTITUE where nom = ? and prenom = ? and sexe = ? and nomPays = ?");
        ps.setString(1, a.getNom());
        ps.setString(2, a.getPrenom());
        ps.setString(3, String.valueOf(a.getSexe()));
        ps.setString(4, a.getPays().getNomPays());
		ps.executeUpdate();
		ps.close();
    }
    public void clearAll() throws SQLException {
        Statement s = laConnexion.createStatement();
        s.executeUpdate("TRUNCATE `ATHLETE`;");
        s.executeUpdate("TRUNCATE `EPREUVE`;");
        s.executeUpdate("TRUNCATE `EQUIPE`;");
        s.executeUpdate("TRUNCATE `EST_CONSTITUE`;");
        s.executeUpdate("TRUNCATE `MANCHE`;");
        s.executeUpdate("TRUNCATE `PARTICIPER_ATHLETE`;");
        s.executeUpdate("TRUNCATE `PARTICIPER_EQUIPE`;");
        s.executeUpdate("TRUNCATE `PAYS`;");
        s.executeUpdate("TRUNCATE `USER`;");
        s.close();
    }


    // --------------------------------IMPORT --------------------------------\\
        public void csvToBd(String chemin){
        String ligne;
        String split =",";
        String[] ligneElems;
        Pays pays;
        String nom;
        String prenom;
        char sexe;
        String nomPays;
        String sport;
        String epreuve;
        int force;
        int endurance;
        int agilite;
        Athlete mich;
        String nomEquipe;
        Equipe equipe;
        Set<Pays> lesPays= new HashSet<>();
        
        try (BufferedReader line = new BufferedReader(new FileReader(chemin))){
            line.readLine();
            while ((ligne = line.readLine())!= null) {
                ligneElems = ligne.split(split);
                if(ligneElems.length >=9){
                    try {
                        nom= ligneElems[0];
                        prenom= ligneElems[1];
                        sexe= ligneElems[2].charAt(0);
                        nomPays = ligneElems[3];
                        pays = new Pays(nomPays);

                        try {
                            insertPays(pays);

                        } catch (Exception e) {
                            System.out.println("Pays deja dans la base de donnée" + e);
                        }

                        sport = ligneElems[4];
                        epreuve = ligneElems[5];
                        try {
                            force =  Integer.parseInt(ligneElems[6]);
                            endurance = Integer.parseInt(ligneElems[7]);
                            agilite =  Integer.parseInt(ligneElems[8]);
                            mich = new Athlete(nom,prenom,sexe,force,endurance,agilite,pays);
                            try {
                                insertAthlete(mich);
                                
                            } catch (Exception e) {
                                System.err.println("Athlete existe deja");
                            }
                            try {
                                getEpreuvebyDescpt(epreuve);

                            } 
                            catch(Exception e){
                                System.err.println("l'Epreuve existe deja");

                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Problème format nombre, ligne : " + ligne);
                        }
                        // si athlete pas creee le cree, sinon l'add a une epreuve
                        // incrire()
                        
                    } catch (Exception e) {
                        System.out.println("erreur format ligne : "+ligne);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();  
        }
    }

}