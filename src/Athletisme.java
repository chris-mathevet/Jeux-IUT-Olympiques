// By julian

import java.util.Random;

/**
 * 
 */
public class Athletisme extends Sport {

	/**
	 * Default constructor pour instencie l'athletisme
	 */
	public Athletisme() {
		super(1, "Athletisme");
	}
	/**
	 * return le nombre de personne dans l'equipe pour ce sport 
	 * @return String
	 */
	public int getNbParEquipe(){ return super.getNbParEquipe();}
	
	/**
	 * retourne le nom du sport 
	 * @return String
	 */
	public String getSport(){ return super.getSport();}


	/**
	 * retourne le nombre de point que l'athlete a eu en fonction du bareme donné
	 * @param Athlete athlete
	 * @return int
	 */
	public int bareme(Athlete athlete){
		// (agilité + endurance)*random(1 => 2) + force ==> (0*0)*0+0 a (20*20)*2+20
		Random e = new Random();
		return (int)((athlete.getAgilite() * 4 + athlete.getForce() + (e.nextInt(20)+1) * 8 + athlete.getEndurance() * 3)/16);
	}
}