import java.util.Random;

public class VoleyBall extends Sport {

	public VoleyBall() {
		super(6, "Voley");	
	}
	
	@Override
	public int bareme(Athlete athlete){
		Random e = new Random();
		return (int)(athlete.getAgilite() * 3 + athlete.getForce() * 4 + (e.nextInt(20)+1) + athlete.getEndurance());
	}	
}