import java.util.List;

/**
 * 
 */
public class Match_individuel extends Match {

	/**
	 * Default constructor
	 */
	public Match_individuel(int nbTour, String nomTour, Epreuve epreuve) {
		this.nbTour = nbTour;
		this.nomTour = nomTour;
		this.epreuve = epreuve;
	}

	/**
	 * @return
	 */
	public List<Integer> resultat() {
		// TODO implement here
		return null;
	}

	public int getResultatAthlete(Athlete athlete){
		return 0;
	}

}