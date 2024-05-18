package participants;

import epreuves.Match;

public class Athlete implements Participant{
	private String nom;
	private String prenom;
	private char sexe;
	private int force;
	private int endurance;
	private int agilite;
	private Pays lePays;

	/** Créer un athlete pour les JO
	 * 
	 * @param String Le nom de l'athlete 
	 * @param String Le prenom de l'athlete
	 * @param char 'F' ou 'H'
	 * @param int La force de l'athlete comprise entre 1 et 20
	 * @param int L'endurance de l'athlete comprise entre 1 et 20
	 * @param int L'agilité de l'athlete comprise entre 1 et 20
	 * @param Pays Le pays de l'athlete
	 */
	public Athlete(String nom, String prenom, char sexe, int force, int endurance, int agilite, Pays pays) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		if (sexe != 'F' || sexe != 'H'){
			this.sexe = 'H';
		}
		this.force = force;
		this.endurance = endurance;
		this.agilite = agilite;
		this.lePays = pays;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public char getSexe() {
		return this.sexe;
	}

	public int getForce() {
		return this.force;
	}

	public int getEndurance() {
		return this.endurance;
	}

	public int getAgilite() {
		return this.agilite;
	}
	
	@Override
	public Pays getPays() {
		return this.lePays;
	}

	@Override
	public int participer(Match<? extends Participant> match) {
		return match.getEpreuve().getSport().bareme(this);
	}

	@Override 
	public boolean equals(Object o){
		if(o == null){return false;}
		if(o == this){return true;}
		if(!(o instanceof Athlete)){return false;}
		Athlete athlete = (Athlete) o;
		return athlete.nom.equals(this.nom) && athlete.prenom.equals(this.prenom) && this.sexe == athlete.sexe && this.lePays == athlete.lePays;
	}

	@Override
	public String toString(){
		return "nom: " + this.nom + " prénom: " + this.prenom + " sexe: " + this.sexe + " pays: " + this.lePays +  " force: " + this.force + " agilité: " + this.agilite + " endurance: " + this.endurance;
	}
}