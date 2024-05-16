/**
 * abstract class Sport
 */
public abstract class Sport {
	private int nbParEquipe;
	private String nomSport;

	/**
	 * Constructeur instancie les sports
	 * 
	 * @param int Le nombre d'athlete par équipe pour ce sport
	 * @param String Le nom du Sport
	 */
	public Sport(int nbParEquipe, String nomSport) {
		this.nbParEquipe = nbParEquipe;
		this.nomSport = nomSport;
	}

	/**
	 * Retourne le nombre de personne dans l'equipe pour ce sport
	 * @return int
	 */
	public int getNbParEquipe() {
		return this.nbParEquipe;
	}
	/**
	 * Retourne le nom du sport 
	 * @return String
	 */
	public String getSport() {
		return this.nomSport;
	}

	/**
	 * Retourne le nombre de point que l'athlete a eu en fonction du bareme donné (Avec les coefs)
	 * @param Athlete L'athlete dont on veut les points
	 * @return int Le nombre de point de l'athlete pour un sport
	 */
	public abstract int bareme(Athlete athlete);

	@Override
	public String toString(){
		if (this.nbParEquipe>1){
			return this.nomSport + ",nb par equipe :" + this.nbParEquipe;
		}
		else{
			return this.nomSport + ",sport individuel";
		}
		
	}
}