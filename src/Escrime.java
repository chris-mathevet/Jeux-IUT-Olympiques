// By julian

import java.util.Random;

/**
 * 
 */
public class Escrime extends Sport {

	/**
	 * Default constructor
	 */
	public Escrime() {
		super(1, "Escrime");
	}
	public int getNbParEquipe(){ return super.getNbParEquipe();}
	
	public String getSport(){ return super.getSport();}

	/**
	 * @param Athlete athlete 
	 * @return
	 */
	public int bareme(Athlete athlete){
		// (agilité * endurance)*random(1 => 2) - force ==> (0*0)*0-0 a (20*20)*2-20
		Random e = new Random();
		return (int)((athlete.getAgilite() * 5 + athlete.getForce() + (e.nextInt(20)+1) * 3 + athlete.getEndurance() * 2)/11);
	}

}