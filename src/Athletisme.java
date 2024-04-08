// By julian

import java.util.Random;

/**
 * 
 */
public class Athletisme extends Sport {

	/**
	 * Default constructor
	 */
	public Athletisme() {
		super();
	}

	/**
	 * @param Athlete athlete 
	 * @return
	 */
	public int bareme(Athlete athlete){
		// (agilité + endurance)*random(1 => 2) + force ==> (0*0)*0+0 a (20*20)*2+20
		Random e = new Random();
		return (int)((athlete.getAgilite() * 4 + athlete.getForce() + (e.nextInt(20)+1) * 8 + athlete.getEndurance() * 3)/16);
	}
}