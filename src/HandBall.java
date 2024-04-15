// By julian

import java.util.Random;

/**
 * 
 */
public class HandBall extends Sport {

	/**
	 * Default constructor pour instencie le handBall
	 */
	public HandBall() {
		super(7, "Handball");
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
		// agilité*random(1 => 2) + (endurance * force) ==> 0*0+(0*0) à 20*2+(20*20)
		Random e = new Random();
		return (int)(athlete.getAgilite() * 2 + athlete.getForce() * 4 + (e.nextInt(20)+1) + athlete.getEndurance() * 3);

		// return (int)((athlete.getAgilite() + athlete.getForce() * 3 + (e.nextInt(20)+1) + athlete.getEndurance() * 2)/7);
	} 

}