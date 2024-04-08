/**
 * 
 */
public class Athlete{
	private String nom;
	private String prenom;
	private char sexe;
	private int force;
	private int endurance;
	private int agilite;

	/**
	 * @param nom 
	 * @param prenom 
	 * @param sexe 
	 * @param force 
	 * @param endurance 
	 * @param agilite
	 */

	public void Athletes(String nom, String prenom, char sexe, int force, int endurance, int agilite) {
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
}