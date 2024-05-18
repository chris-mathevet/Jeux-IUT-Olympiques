package participants;

import java.util.ArrayList;

import epreuves.Match;
import exceptions.AlreadyInException;
import exceptions.NotSameCountryException;

public class Equipe extends ArrayList<Athlete> implements Participant{
	private String nom;

	/**
	 * @param String le nom de l'équipe
	 */
	public Equipe(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * @return Pays le Pays de l'équipe, ou null si l'équipe est vide
	 */
	@Override
	public Pays getPays(){
		if (! this.isEmpty()){
			return this.get(0).getPays();
		}
		return null;
	}

	/**
     * Permet d'ajouter un athlete dans l'équipe, si il n'est pas déjà dedans sinon on renvoie l'exception "AlreadyInException" créer à cet effet.
     * @param Athlete athlete 
     * @return void
     */

	 public void ajouter(Athlete athlete) throws AlreadyInException, NotSameCountryException{

		if (this.isEmpty()) {
			this.add(athlete);
			this.getPays().getLesEquipes().add(this);
		}
        else if ((!(this.contains(athlete))) && athlete.getPays().equals(this.get(0).getPays())){
            this.add(athlete);
        }
        else if (this.contains(athlete)) {
            throw new AlreadyInException();
        }

		else if (!(athlete.getPays().equals(this.get(0).getPays()))) {
			throw new NotSameCountryException();
		}
		
    }

	/**
	 * @param Match match 
	 * @return int (somme des bareme des athlete de l'équipe)
	 */
	@Override
	public int participer(Match<? extends Participant> match) {
		int sommeBareme = 0;
		for (Athlete athlete : this) {
			sommeBareme += athlete.participer(match);
		}
		return sommeBareme;
	}

	@Override
	public String toString() {
		return this.nom +", membre: " +super.toString();
	}
}