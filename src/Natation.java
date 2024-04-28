// By julian

import java.util.Random;

/**
 * 
 */
public class Natation extends Sport {

	/**
	 * Default constructor pour instencie la natation
	 */
	public Natation() {
		super(1, "Natation");
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
		// agilité + (endurance*random(1 => 2) * force) ==> 0+(0*0*0) à 20+(20*2*20) 
		Random e = new Random();
		// return (int)((athlete.getAgilite() * 2 + athlete.getForce() + (e.nextInt(20)+1) * 2 + athlete.getEndurance() * 5)/10);
		return (int)(athlete.getAgilite() * 2 + athlete.getForce() + (e.nextInt(20)+1) * 2 + athlete.getEndurance() * 5);
	}
}