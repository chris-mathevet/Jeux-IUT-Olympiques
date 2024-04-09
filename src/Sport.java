/**
 * abstract class Sport
 */
public abstract class Sport {
	private int nbParEquipe;
	private String nomSport;

	/**
	 * constructor
	 * 
	 * @param nbParEquipe int
	 * @param nomSport    String
	 */
	public Sport(int nbParEquipe, String nomSport) {
		this.nbParEquipe = nbParEquipe;
		this.nomSport = nomSport;
	}

	public int getNbParEquipe() {
		return this.nbParEquipe;
	}

	public String getSport() {
		return this.nomSport;
	}

	/**
	 * @param Athlete athlete
	 * @return int
	 */
	public abstract int bareme(Athlete athlete);

}