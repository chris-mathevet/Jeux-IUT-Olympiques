
import java.util.ArrayList;
import java.util.List;

public class Equipe extends ArrayList<Athlete> implements Participant{

	private String nom;


	/**
	 * @param nom
	 */
	public Equipe(String nom) {
		this.nom = nom;
	}

	/**
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * 
	 * @return
	 */
	public List<Athlete> getListeAthlete() {
		return this;
	}

	/**
     * Permet d'ajouter un athlete dans l'équipe, si il n'est pas déjà dedans sinon on renvoie l'exception "AlreadyInException" créer à cet effet.
     * 
     * @param Athlete athlete 
     * @return void
     */

	 public void ajouter(Athlete athlete) throws AlreadyInException {

		if (this.isEmpty()) {
			this.add(athlete);
		}


        else if (!(this.contains(athlete)) && athlete.getPays().equals(this.get(0).getPays())) {
            this.add(athlete);
        }
        else if (this.contains(athlete)) {
            throw new AlreadyInException();
        }

		else {
			System.err.println("Pas le même pays");
		}
    }

	/**
	 * @param Match match 
	 * @return int (somme des bareme des athlete de l'équipe)
	 */
	public int participer(Match match) {
		int sommeBareme = 0;
		for (Athlete athlete : this) {
			sommeBareme += match.getEpreuve().getSport().bareme(athlete);
		}

		return sommeBareme;
	}
}