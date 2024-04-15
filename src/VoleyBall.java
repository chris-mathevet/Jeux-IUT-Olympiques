// By julian

import java.util.Random;

/**
 * 
 */
public class VoleyBall extends Sport {

	/**
	 * Default constructor pour instencie le voley
	 */
	public VoleyBall() {
		super(6, "Voley");	
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
		// (agilité * force)*random(1 => 2) + endurance == >
		Random e = new Random();
		return (int)((athlete.getAgilite() * 2 + athlete.getForce() * 3 + (e.nextInt(20)+1) + athlete.getEndurance())/7);
	}	
}