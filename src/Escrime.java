// By julian

import java.util.Random;

/**
 * 
 */
public class Escrime extends Sport {

	/**
	 * Default constructor pour instencie l'escrime
	 */
	public Escrime() {
		super(1, "Escrime");
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
		// (agilité * endurance)*random(1 => 2) - force ==> (0*0)*0-0 a (20*20)*2-20
		Random e = new Random();
		// return (int)((athlete.getAgilite() * 5 + athlete.getForce() + (e.nextInt(20)+1) * 3 + athlete.getEndurance() * 2)/11);
		return (int)(athlete.getAgilite() * 4 + athlete.getForce() + (e.nextInt(20)+1) * 3 + athlete.getEndurance() * 2);
	}

}