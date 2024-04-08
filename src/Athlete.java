/**
 * 
 */
public class Athlete implements Participant{
	private String nom;
	private String prenom;
	private char sexe;
	private int force;
	private int endurance;
	private int agilite;
	private Pays lePays;

	/**
	 * @param nom 
	 * @param prenom 
	 * @param sexe 
	 * @param force 
	 * @param endurance 
	 * @param agilite
	 */
	public void Athletes(String nom, String prenom, char sexe, int force, int endurance, int agilite) {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public String getNom() {
		// TODO implement here
		return "";
	}

	/**
	 * @return
	 */
	public String getPrenom() {
		// TODO implement here
		return "";
	}

	/**
	 * @return
	 */
	public String getSexe() {
		// TODO implement here
		return "";
	}

	/**
	 * @return
	 */
	public int getForce() {
		// TODO implement here
		return 0;
	}

	/**
	 * @return
	 */
	public int getEndurance() {
		// TODO implement here
		return 0;
	}

	/**
	 * @return
	 */
	public int getAgilite() {
		// TODO implement here
		return 0;
	}
	
	public Pays getPays() {
		return this.lePays;
	}

	@Override
	public int participer(Match match) {
		// TODO Auto-generated method stub
		return 0;
	}
}