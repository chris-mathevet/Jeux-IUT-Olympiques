package database;
import java.sql.*;
import java.util.List;

import epreuves.*;
import exceptions.*;
import participants.*;
import sports.*;
import participants.*;

public class Requete {
    private ConnexionConnexion co;

    public Requete(ConnexionConnexion co){
        this.co=co;
    }

    //---------------Select---------------------\\

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
		ps.setString(3, e.getSexe());
        ps.setString(4, e.getSport().getSport());
        ps.executeQuery();
		ps.close();
    }

    public void insertUser(String id,String mdp,String type){
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO USER (idPseudo, mdp, type) VALUES (?, ?, ?)");
        ps.setString(1, id);
		ps.setString(2, mdp);
		ps.setString(3, type);
        ps.executeQuery();
		ps.close();
    }

    public void insertEquipe(Equipe e) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO EQUIPE (nomEquipe) VALUES (?)");
        ps.setInt(1, e.getNom());
        ps.executeQuery();
		ps.close();
        for(Athlete a : e){
            ps = laConnexion.prepareStatement("INSERT INTO EST_CONSTITUE (nomEquipe,nom,prenom,sexe,nomPays) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, e.getNom());
            ps.setString(2, a.getNom());
            ps.setString(3, a.getPrenom());
            ps.setString(4, String.valueOf(a.getSexe()));
            ps.setString(5, a.getPays().getPays());
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
        ps.setInt(7, a.getPays().getNomPays());
        ps.executeQuery();
		ps.close();
    }

    public void insertManche(Manche<T> m) throws  SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO MANCHE (nomManche, descriptionEpreuve, sexe, numeroManche) VALUES (?, ?, ?, ?)");
        ps.setString(1, m.getNomDeTour());
        ps.setString(2, m.getEpreuve().getDescription());
        ps.setString(3, m.getEpreuve().getSexe());
        ps.setString(4, m.getNumeroDeTour());
        String table;
        if(m.getLesParticipants() instanceof List<Equipe>){
            for(T p:m.getLesParticipants()){
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_ATHLETE (nomManche, descriptionEpreuve, sexe, nomEquipe) VALUES (?, ?, ?, ?)");
                ps.setString(1, m.getNomDeTour());
                ps.setString(2, m.getEpreuve().getDescription());
                ps.setString(3, m.getEpreuve().getSexe());
                ps.setString(4, p.getNom());
                ps.executeQuery();
                ps.close();
            }
        }
        else{
            for(T p:m.getLesParticipants()){
                ps = laConnexion.prepareStatement("INSERT INTO PARTICIPER_EQUIPE (nomManche, descriptionEpreuve, sexeEpreuve, nom, prenom, sexe,nomPays) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, m.getNomDeTour());
                ps.setString(2, m.getEpreuve().getDescription());
                ps.setString(3, m.getEpreuve().getSexe());
                ps.setString(4, p.getNom());
                ps.setString(5, p.getPrenom());
                ps.setString(6, p.getSexe());
                ps.setString(7, p.getPays().getPays());
                ps.executeQuery();
                ps.close();
            }
        }
 
    }


    //---------------Modifieur------------------\\



}