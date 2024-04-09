// By julian

import java.util.Random;

/**
 * 
 */
public class Natation extends Sport {

	/**
	 * Default constructor
	 */
	public Natation() {
		super(1, "Natation");
	}
	public int getNbParEquipe(){ return super.getNbParEquipe();}
	
	public String getSport(){ return super.getSport();}

	/**
	 * @param Athlete athlete 
	 * @return	
	 */
	public int bareme(Athlete athlete){
		// agilité + (endurance*random(1 => 2) * force) ==> 0+(0*0*0) à 20+(20*2*20) 
		Random e = new Random();
		return (int)((athlete.getAgilite() * 2 + athlete.getForce() + (e.nextInt(20)+1) * 2 + athlete.getEndurance() * 5)/10);
	}


}