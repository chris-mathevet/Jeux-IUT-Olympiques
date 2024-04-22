import java.util.List;

/**
 * 
 */
public abstract class Match {
	private int numéroDeTour;
	private String nomDeTour;
	private Epreuve epreuve;

	/**
	 * @param nbTour 
	 * @param nomTour 
	 * @param epreuve Epreuve
	 */
	public Match(int nbTour, String nomTour, Epreuve epreuve) {
		this.numéroDeTour = nbTour;
		this.nomDeTour = nomTour;
		this.epreuve = epreuve;
	}

	/**
	 * @return
	 */
	public Epreuve getEpreuve() {
		return this.epreuve;
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
}