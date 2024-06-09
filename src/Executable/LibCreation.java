package Executable;

import java.util.List;

import exceptions.AlreadyExistException;
import participants.Athlete;
import participants.Equipe;
import participants.Pays;

public class LibCreation {
    private LibCreation(){}

    public static void creerAthlete(List<Athlete> lesAthletes, String nom, String prenom, char sexe, int force, int agilite, int endurance, Pays pays)
    throws AlreadyExistException{
        Athlete athlete = new Athlete(nom, prenom, sexe, force, endurance, agilite, pays);
        if(!lesAthletes.contains(athlete)){
            lesAthletes.add(athlete);
        }
        else{
            throw new AlreadyExistException("Cet athlete existe déjà");
        }

    }

    public static void creerEquipe(List<Equipe> lesEquipes, String nom)
    throws AlreadyExistException{
        Equipe equipe = new Equipe(nom);
        if(!lesEquipes.contains(equipe)){
            lesEquipes.add(equipe);
        }
        else{
            throw new AlreadyExistException("Cette equipe existe déjà");
        }

    }

    public static void creerPays(List<Pays> lesPays, String nom)
    throws AlreadyExistException{
        Pays pays = new Pays(nom);
        if(!lesPays.contains(pays)){
            lesPays.add(pays);
        }
        else{
            throw new AlreadyExistException("Ce pays existe déjà");
        }
    }
}
