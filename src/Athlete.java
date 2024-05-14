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

	public Athlete(String nom, String prenom, char sexe, int force, int endurance, int agilite) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.force = force;
		this.endurance = endurance;
		this.agilite = agilite;
	}

	/**
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @return
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * @return
	 */
	public char getSexe() {
		return this.sexe;
	}

	/**
	 * @return
	 */
	public int getForce() {
		return this.force;
	}

	/**
	 * @return
	 */
	public int getEndurance() {
		return this.endurance;
	}

	/**
	 * @return
	 */
	public int getAgilite() {
		return this.agilite;
	}
	
	public Pays getPays() {
		return this.lePays;
	}

	@Override
	public int participer(Match<? extends Participant> match) {
		return match.getEpreuve().getSport().bareme(this);
	}
}