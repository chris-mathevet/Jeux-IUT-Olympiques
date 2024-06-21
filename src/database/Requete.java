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

import MVC.modele.ModeleJO;
import MVC.tableClass.UserTableau;
import MVC.user.User;


public class Requete {
    private ConnexionMySql laConnexion;
    private ModeleJO modele;

    public Requete(ConnexionMySql co){
        this.laConnexion=co;
        this.modele = new ModeleJO();
    }

    public Requete(ConnexionMySql co, ModeleJO mo){
        this.laConnexion=co;
        this.modele=mo;
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
            try {
                lesAthletes.add(new Athlete(nomAthlete, prenomAthlete, sexe, force, agilite, endurance, this.modele.getPays(nomPays)));
                
            } catch (DoesntExistException e) {
                System.out.println("pays n'existe pas");
            }        }

        return lesAthletes;
    }

    public List<Epreuve<Participant>> selectEpreuves() throws SQLException{
        List<Epreuve<Participant>> lesEpreuves= new ArrayList<>();
        
        Statement s=laConnexion.createStatement();

        ResultSet resultSet = s.executeQuery("SELECT * FROM EPREUVE");

        while (resultSet.next()) {
            
            String descriptionEpreuve = resultSet.getString("descriptionEpreuve");
            String sexeString = resultSet.getString("sexe");
            char sexe = ' ';
            if (sexeString != null && !sexeString.isEmpty()) { // convertir le sexe de la base de donné qui est en Varchar (String) en un char 
                sexe = sexeString.charAt(0);
            }
            String nomSport = resultSet.getString("nomSport");
            try {
                Epreuve<Participant> epreuve = new Epreuve<>(descriptionEpreuve,this.modele.getSport(nomSport),sexe);
                lesEpreuves.add(epreuve);                
                PreparedStatement ps = laConnexion.prepareStatement("select nomManche,numeroManche from MANCHE where descriptionEpreuve = ? and sexe = ?");
                ps.setString(1, descriptionEpreuve);
                ps.setString(2, sexeString);
                ResultSet res = ps.executeQuery();
                
                while(res.next()){
                    epreuve.ajoutManche(new Manche<>(res.getInt("numeroManche"),res.getString("nomManche"), epreuve));
                }
                ps.close();

                // requete savoir si nomEpreuve est dans ATHLETE OU EQUIPE
                try {
                    PreparedStatement ps2 = laConnexion.prepareStatement("select nomAthlete, prenomAthlete, sexe, nomPays, resultat from PARTICIPER_ATHLETE where descriptionEpreuve = ? and sexe = ?");
                    ps2.setString(1, descriptionEpreuve);
                    ps2.setString(2, sexeString);
                    res = ps2.executeQuery();
                    
                    while(res.next()){
                        try {
                            System.out.println("insc");
                            List<Double> listeRes;
                            epreuve.inscrire(this.modele.getAthlete(res.getString("nomAthlete"),res.getString("prenomAthlete"),res.getString("sexe").charAt(0),res.getString("nomPays")));
                            for(Manche<Participant> manche :epreuve.getLesManches()){
                                try {
                                    PreparedStatement ps3 = laConnexion.prepareStatement("select resultat from PARTCIPER_ATHLETE where nomManche = ? and descriptionEpreuve = ? and sexe = ?");
                                    ps3.setString(1, manche.getNomDeTour());
                                    ps3.setString(2, descriptionEpreuve);
                                    ps3.setString(3, sexeString);
                                    ResultSet res3 = ps3.executeQuery();
                                    listeRes = new ArrayList<>();
                                        while(res3.next()){
                                            listeRes.add(res3.getDouble("resultat"));

                                        }
                                        manche.setResultat(listeRes);
                                      
                                } catch (Exception err) {
                                    err.printStackTrace();
                                }
                            }   


                        } catch (Exception err) {
                            err.printStackTrace();
                        }
                    }
                    ps2.close();
                    
                } catch (Exception e) {
                    PreparedStatement ps2 = laConnexion.prepareStatement("select nomEquipe from PARTICIPER_EQUIPE where descriptionEpreuve = ? and sexeEpreuve = ?");  
                    ps2.setString(1, descriptionEpreuve);
                    ps2.setString(2, sexeString);
                    res = ps2.executeQuery();
                    
                    while(res.next()){
                        try {
                            List<Double> listeRes;
                            epreuve.inscrire(this.modele.getEquipe(res.getString("nomEquipe")));
                            for(Manche<Participant> manche :epreuve.getLesManches()){
                                try {
                                    PreparedStatement ps3 = laConnexion.prepareStatement("select resultat from PARTICIPER_EQUIPE where nomManche = ? and descriptionEpreuve = ? and sexeEpreuve = ?");
                                    ps3.setString(1, manche.getNomDeTour());
                                    ps3.setString(2, descriptionEpreuve);
                                    ps3.setString(3, sexeString);
                                    ResultSet res2 = ps3.executeQuery();
                                    listeRes = new ArrayList<>();
                                    while(res2.next()){
                                        listeRes.add(res2.getDouble("resultat"));
                                    }
                                    manche.setResultat(listeRes);
                                } catch (Exception err) {
                                    err.printStackTrace();
                                }
                               
                            }
                            
                        } catch (Exception err) {
                            err.printStackTrace();
                        }
                    }   
                    ps2.close();
                }

            } catch (Exception err) {
                err.printStackTrace();
            }
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
            try {
                lesAthletes.add(new Athlete(nomAthlete, prenomAthlete, sexeCaract, capaciteForce, agilite, endurance, this.modele.getPays(nomPays2)));
                
            } catch (DoesntExistException e) {
                System.out.println("pays n'existe pas");
            }
        }
        resultSet.close();
        statement.close();
    
        return lesAthletes;
    }
    public Pays getPaysbyNom(String nomPays, ModeleJO model) throws SQLException{
            
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


    public String getMailUser(String pseudo) throws SQLException{
            
        Statement s=laConnexion.createStatement();
        ResultSet r=s.executeQuery("select * from USER where idPseudo="+"\""+pseudo+"\""+";");
        r.next();
        String res = r.getString("email"); 

        r.close();
        return res;    
    }


    public String getDrapeau(String nomPays) throws SQLException{
            
        Statement s=laConnexion.createStatement();
        ResultSet r=s.executeQuery("select * from DRAPEAU where nomPays="+"\""+nomPays+"\""+";");
        r.next();
        String pathImg = r.getString("pathImg"); 

        r.close();
        return pathImg;    
    }


    public Epreuve<Participant> getEpreuvebyDescpt(String descriptionEpreuve) throws SQLException{
            
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


        try {
            return this.modele.getEpreuve(descripEpreuve, sexe);
        } catch (DoesntExistException e) {
            e.printStackTrace();
        } 
        return null;
    }


    public List<Pays> selectPays() throws SQLException{

        List<Pays> lesPays = new ArrayList<>();

		Statement s=laConnexion.createStatement();
		ResultSet rs=s.executeQuery("SELECT * FROM PAYS");
		while (rs.next()) {
            lesPays.add(new Pays(rs.getString("nomPays"),rs.getInt("nbMedailleOr"),rs.getInt("nbMedailleArgent"),rs.getInt("nbMedaillebronze")));
		}
		rs.close();
    	return lesPays ;
	}

    public List<User> selectUsers() throws SQLException{

        List<User> lesUsers = new ArrayList<>();

		Statement s=laConnexion.createStatement();
		ResultSet rs=s.executeQuery("SELECT * FROM USER");
		while (rs.next()) {
            lesUsers.add(new User(rs.getString("idPseudo"),rs.getString("email"),rs.getString("type")));
		}
		rs.close();
    	return lesUsers ;
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
        Statement s2=laConnexion.createStatement();
        ResultSet rs=s.executeQuery("SELECT * FROM EQUIPE");
        ResultSet rs2;
        int cpt = 0;
        while (rs.next()) { 
            res.add(new Equipe(rs.getString("nomEquipe"))); 

            rs2 =s2.executeQuery("SELECT * FROM EQUIPE NATURAL JOIN EST_CONSTITUE WHERE nomEquipe="+"\""+rs.getString("nomEquipe")+"\""); 

            while (rs2.next()) {
                try {
                    res.get(cpt).add(modele.getAthlete(rs2.getString("nomAthlete"),rs2.getString("prenomAthlete"),rs2.getString("sexe").charAt(0),rs2.getString("nomPays")));
                } catch (DoesntExistException e) {
                    
                    e.printStackTrace();
                }
            }
            rs2.close();
            cpt++;
		}
        rs.close();
        s.close();
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

    public void insertEpreuve(Epreuve<Participant> e) throws  SQLException {
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

    public void insertEquipe(Equipe e) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO EQUIPE (nomEquipe) VALUES (?)");
        ps.setString(1, e.getNom());
        ps.executeUpdate();
		ps.close();
    }

    public void insertToEquipe(Equipe e, Athlete a) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO EST_CONSTITUE (nomEquipe,nomAthlete,prenomAthlete,sexe,nomPays) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, e.getNom());
        ps.setString(2, a.getNom());
        ps.setString(3, a.getPrenom());
        ps.setString(4, String.valueOf(a.getSexe()));
        ps.setString(5, a.getPays().getNomPays());
        ps.executeUpdate();
        ps.close();
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

    public void insertManche(Manche<Participant> m) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO MANCHE (nomManche, descriptionEpreuve, sexe, numeroManche) VALUES (?, ?, ?, ?)");
        ps.setString(1, m.getNomDeTour());
        ps.setString(2, m.getEpreuve().getDescription());
        ps.setString(3, String.valueOf(m.getEpreuve().getSexe()));
        ps.setInt(4, m.getNumeroDeTour());

        if(m.getEpreuve().getLesParticipants().get(0) instanceof Equipe){
            Equipe equipe;
            for(Participant p : m.getEpreuve().getLesParticipants()){
                equipe = (Equipe) p;
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_ATHLETE (nomManche, descriptionEpreuve, sexeEpreuve, nomEquipe) VALUES (?, ?, ?, ?)");
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
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_EQUIPE (nomManche, descriptionEpreuve, sexeEpreuve, nomAthlete, prenomAthlete, sexe,nomPays) VALUES (?, ?, ?, ?, ?, ?, ?)");
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

    // public <T extends Participant> void ajoutParticipant(Manche<T> m) throws  SQLException {
    //     PreparedStatement ps;
    //     if(m.getEpreuve().getLesParticipants().get(0) instanceof Equipe){
    //         Equipe equipe;
    //         for(Participant p : m.getEpreuve().getLesParticipants()){
    //             equipe = (Equipe) p;
    //             ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_ATHLETE (nomManche, descriptionEpreuve, sexe, nomEquipe) VALUES (?, ?, ?, ?)");
    //             ps.setString(1, m.getNomDeTour());
    //             ps.setString(2, m.getEpreuve().getDescription());
    //             ps.setString(3, String.valueOf(m.getEpreuve().getSexe()));
    //             ps.setString(4, equipe.getNom());
    //             ps.executeUpdate();
    //             ps.close();
    //         }
    //     }
    //     else{
    //         Athlete athleteInsert;
    //         for(Participant p:m.getEpreuve().getLesParticipants()){
    //             athleteInsert = (Athlete)p;
    //             ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_EQUIPE (nomManche, descriptionEpreuve, sexeEpreuve, nomAthlete, prenomAthlete sexe,nomPays) VALUES (?, ?, ?, ?, ?, ?, ?)");
    //             ps.setString(1, m.getNomDeTour());
    //             ps.setString(2, m.getEpreuve().getDescription());
    //             ps.setString(3, String.valueOf(m.getEpreuve().getSexe()));
    //             ps.setString(4, athleteInsert.getNom());
    //             ps.setString(5, athleteInsert.getPrenom());
    //             ps.setString(6, String.valueOf(athleteInsert.getSexe()));
    //             ps.setString(7, athleteInsert.getPays().getNomPays());
    //             ps.executeUpdate();
    //             ps.close();
    //         }
    //     }
    // }

    

    public void insertAllEpreuve() throws SQLException {
        Sport voley = new VoleyBall();
        Sport hand = new HandBall();
        Sport escr = new Escrime();
        Sport nat = new Natation();
        Sport athle = new Athletisme();
        List<Epreuve<Participant>> lesEpreuves = new ArrayList<>(Arrays.asList(
            new Epreuve<>("Natation 100 brasse", nat, 'H'),
            new Epreuve<>("Natation 100 brasse", nat, 'F'),
            new Epreuve<>("Natation relais libre", nat, 'H'),
            new Epreuve<>("Natation relais libre", nat, 'F'),
            new Epreuve<>("Handball", hand, 'H'),
            new Epreuve<>("Handball", hand, 'F'),
            new Epreuve<>("Volley-Ball", voley, 'H'),
            new Epreuve<>("Volley-Ball", voley, 'F'),
            new Epreuve<>("Escrime fleuret", escr, 'H'),
            new Epreuve<>("Escrime fleuret", escr, 'F'),
            new Epreuve<>("Escrime épée", escr, 'H'),
            new Epreuve<>("Escrime épée", escr, 'F'),
            new Epreuve<>("Athétisme 110 haies", athle, 'H'),
            new Epreuve<>("Athétisme 110 haies", athle, 'F'),
            new Epreuve<>("Athlétisme relais 400m", athle, 'H'),
            new Epreuve<>("Athlétisme relais 400m", athle, 'F')
        ));
        for (Epreuve<Participant> epreuve : lesEpreuves) {
            insertEpreuve(epreuve);
            
        }
        // PreparedStatement ps;
        // for (Epreuve<Participant> epreuve : lesEpreuves) {
        //     ps = laConnexion.prepareStatement("INSERT INTO EPREUVE (descriptionEpreuve, sexe, nomSport) VALUES (?, ?, ?)");
        //     System.out.println("epreuve :" + epreuve);
        //     ps.setString(1, epreuve.getDescription());
        //     ps.setString(2, String.valueOf(epreuve.getSexe()));
        //     ps.setString(3, epreuve.getSport().getSport());

        //     ps.executeUpdate();
        //     ps.close();
        // }
    }

    public void insertParticipantToEpreuve(Participant participant, Epreuve<Participant> epreuve)throws  SQLException {
        PreparedStatement ps;
        for(Manche<Participant> manche : epreuve.getLesManches()){
            if(participant instanceof Athlete){
                Athlete a = (Athlete) participant;
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_ATHLETE (nomManche, descriptionEpreuve, sexeEpreuve, nomAthlete, prenomAthlete, sexe,nomPays) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, manche.getNomDeTour());
                ps.setString(2, manche.getEpreuve().getDescription());
                ps.setString(3, String.valueOf(epreuve.getSexe()));
                ps.setString(4, a.getNom());
                ps.setString(5, a.getPrenom());
                ps.setString(6, String.valueOf(a.getSexe()));
                ps.setString(7, a.getPays().getNomPays());
                ps.executeUpdate();
                ps.close();
            }
            else{
                Equipe e = (Equipe) participant;
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_EQUIPE (nomManche, descriptionEpreuve, sexeEpreuve, nomEquipe) VALUES (?, ?, ?, ?)");
                ps.setString(1, manche.getNomDeTour());
                ps.setString(2, manche.getEpreuve().getDescription());
                ps.setString(3, String.valueOf(manche.getEpreuve().getSexe()));
                ps.setString(4, e.getNom());
                ps.executeUpdate();
                ps.close();
            }
        }
    }

    public void insertResultat(Epreuve<Participant> epreuve) throws SQLException{
        PreparedStatement ps;
        for(Manche<Participant> manche : epreuve.getLesManches()){
            for(int i=0;i<epreuve.getLesParticipants().size(); i++){
                Participant participant = epreuve.getLesParticipants().get(i);
                if(participant instanceof Athlete){
                    Athlete a = (Athlete) participant;
                    ps = laConnexion.prepareStatement("UPTDATE PARTICIPER_ATHLETE SET resultat = ? WHERE nom = ? and prenom = ? and sexe = ? and nomPays = ? and nomManche = ? and descriptionEpreuve = ? and sexeEpreuve = ?");
                    ps.setDouble(1, manche.getResultats().get(i));
                    ps.setString(2, a.getNom());
                    ps.setString(3, a.getPrenom());
                    ps.setString(4, String.valueOf(a.getSexe()));
                    ps.setString(5, a.getPays().getNomPays());
                    ps.setString(6, manche.getNomDeTour());
                    ps.setString(7, manche.getEpreuve().getDescription());
                    ps.setString(8, String.valueOf(manche.getEpreuve().getSexe()));
                    ps.executeUpdate();
                    ps.close();
                }
                else{
                    Equipe e = (Equipe) participant;
                    ps = laConnexion.prepareStatement("UPTDATE PARTICIPER_ATHLETE SET resultat = ? WHERE nomEquipe = ? and nomManche = ? and descriptionEpreuve = ? and sexeEpreuve = ?");
                    ps.setDouble(1, manche.getResultats().get(i));
                    ps.setString(2, e.getNom());
                    ps.setString(3, manche.getNomDeTour());
                    ps.setString(4, manche.getEpreuve().getDescription());
                    ps.setString(5, String.valueOf(manche.getEpreuve().getSexe()));
                    ps.executeUpdate();
                    ps.close();
                }
            }
        }
    }


    
    //---------------Modifieur-------------------\\
    public void updateUser(String pseudo, String role) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("update USER set type = \"" +role+"\" where idPseudo = \""+pseudo+"\";");

        // Statement s=laConnexion.createStatement();
        // ResultSet r=s.executeQuery("update USER set type = \"" +role+"\" idPseudo = \""+pseudo+"\";");
        ps.executeUpdate();
        ps.close();
    }

    


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
        public void csvToBd(String chemin) throws SQLException{
        System.out.println("l'Import du CSV");
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
                        try {
                            pays = modele.getPays(nomPays);
                            
                        } catch (DoesntExistException e) {
                            pays = new Pays(nomPays);
                            try {
                                insertPays(pays);
    
                            } catch (Exception err) {
                                System.out.println("Pays deja dans la base de donnée" + err);
                            }
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
                        try {
                            // this.modele.
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        
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