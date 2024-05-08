import java.util.ArrayList;
import java.util.List;

public class Equipe implements Participant {
    private String nom;
    private List <Athlete> lesAthletes;


    /**
     * @param nom
     */
    public Equipe(String nom) {
        this.nom = nom;
        this.lesAthletes = new ArrayList<>();
    }

    /**
     * @return String 
     * Nom de l'équipe
     * 
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Permet d'ajouter un athlete dans l'équipe, si il n'est pas déjà dedans sinon on renvoie l'exception "AlreadyInException" créer à cet effet.
     * 
     * @param Athlete athlete 
     * @return void
     */

    public void ajouter(Athlete athlete) throws AlreadyInException {
        if (!(lesAthletes.contains(athlete))) {
            lesAthletes.add(athlete);
        }
        else {
            throw new AlreadyInException();
        }
    }

    /**
     * 
     * Permet d'obtenir la somme des barèmes des athletes présents dans l'équipe.
     * 
     * @param Match match 
     * @return int 
     */
    public int participer(Match match) {
        int sommeBareme = 0;
        for (Athlete athlete : this.lesAthletes) {
            sommeBareme += match.getEpreuve().getSport().bareme(athlete);
        }

        return sommeBareme;
    }
}