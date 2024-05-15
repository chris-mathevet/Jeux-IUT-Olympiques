import java.util.Random;

public class HandBall extends Sport {

	public HandBall() {
		super(7, "Handball");
	}

	@Override
	public int bareme(Athlete athlete){
		Random e = new Random();
		return (int)(athlete.getAgilite() * 2 + athlete.getForce() * 4 + (e.nextInt(20)+1) + athlete.getEndurance() * 3);
	} 

}