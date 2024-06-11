package executable;

import java.util.List;

import epreuves.Epreuve;
import exceptions.AlreadyExistException;
import participants.Athlete;
import participants.Equipe;
import participants.Participant;
import participants.Pays;

public class LibCreation {
    private LibCreation(){}

    public static void creerAthlete(List<Athlete> lesAthletes, Athlete athlete)
    throws AlreadyExistException{
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

    public static <T extends Participant> void creerEpreuve(List<Epreuve<T>> lesEpreuves , Epreuve<T> uneEpreuve)
    throws AlreadyExistException{
        if(!lesEpreuves.contains(uneEpreuve)){
            lesEpreuves.add(uneEpreuve);
        }
        else{
            throw new AlreadyExistException("Cette Epreuve existe déjà");
        }
    }
}
