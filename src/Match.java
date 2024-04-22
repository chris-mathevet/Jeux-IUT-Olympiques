import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public abstract class Match {
	private int numéroDeTour;
	private String nomDeTour;
	private List<Integer> resultats;

	/**
	 * @param nbTour 
	 * @param nomTour 
	 */
	public Match(int nbTour, String nomTour) {
		this.numéroDeTour = nbTour;
		this.nomDeTour = nomTour;
		this.resultats = new ArrayList<>();
	}

	/**
	 * @return
	 */
	public int getNumeroDeTour() {
		return this.numéroDeTour;
	}

	/**
	 * @return
	 */
	public String getNomDeTour() {
		return this.nomDeTour;
	}

	public List<Integer> getResultats() {
		return this.resultats;
	}
}