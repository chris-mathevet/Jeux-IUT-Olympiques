/**
 * abstract class Sport
 */
public abstract class Sport {
	private int nbParEquipe;
	private String nomSport;

	/**
	 * constructor instancie les sports
	 * 
	 * @param nbParEquipe int
	 * @param nomSport    String
	 */
	public Sport(int nbParEquipe, String nomSport) {
		this.nbParEquipe = nbParEquipe;
		this.nomSport = nomSport;
	}

	/**
	 * return le nombre de personne dans l'equipe pour ce sportt 
	 * @return String
	 */
	public int getNbParEquipe() {
		return this.nbParEquipe;
	}
	/**
	 * retourne le nom du sport 
	 * @return String
	 */
	public String getSport() {
		return this.nomSport;
	}

	/**
	 * retourne le nombre de point que l'athlete a eu en fonction du bareme donné
	 * @param Athlete athlete
	 * @return int
	 */
	public abstract int bareme(Athlete athlete);

}