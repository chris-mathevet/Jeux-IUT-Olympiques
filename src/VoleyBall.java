// By julian

import java.util.Random;

/**
 * 
 */
public class VoleyBall extends Sport {

	/**
	 * Default constructor
	 */
	public VoleyBall() {
		super(6, "Voley");	
	}
	
	public int getNbParEquipe(){ return super.getNbParEquipe();}
	
	public String getSport(){ return super.getSport();}


	/**
	 * @param Athlete athlete 
	 * @return
	 */
	public int bareme(Athlete athlete){
		// (agilité * force)*random(1 => 2) + endurance == >
		Random e = new Random();
		return (int)((athlete.getAgilite() * 2 + athlete.getForce() * 3 + (e.nextInt(20)+1) + athlete.getEndurance())/7);
	}	
}