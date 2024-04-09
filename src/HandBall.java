// By julian

import java.util.Random;

/**
 * 
 */
public class HandBall extends Sport {

	/**
	 * Default constructor
	 */
	public HandBall() {
		super(7, "Handball");
	}
	public int getNbParEquipe(){ return super.getNbParEquipe();}
	
	public String getSport(){ return super.getSport();}

	/**
	 * @param Athlete athlete 
	 * @return
	 */
	public int bareme(Athlete athlete){
		// agilité*random(1 => 2) + (endurance * force) ==> 0*0+(0*0) à 20*2+(20*20)
		Random e = new Random();
		return (int)((athlete.getAgilite() + athlete.getForce() * 3 + (e.nextInt(20)+1) + athlete.getEndurance() * 2)/7);
	} 

}