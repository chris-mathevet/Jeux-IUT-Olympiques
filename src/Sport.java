/**
 * 
 */
public abstract class Sport{
	private int nbParEquipe;

	/**
	 * @param nbParticipant
	 */
	public Sport(int nbParticipant) {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public abstract int getNbParEquipe();

	/**
	 * @return
	 */
	public abstract String getSport();

	/**
	 * @param Athlete athlete 
	 * @return
	 */
	public abstract int bareme(Athlete athlete);

}