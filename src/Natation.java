import java.util.Random;

public class Natation extends Sport {

	public Natation() {
		super(1, "Natation");
	}

	@Override
	public int bareme(Athlete athlete){
		Random e = new Random();
		return (int)(athlete.getAgilite() * 2 + athlete.getForce() + (e.nextInt(20)+1) * 2 + athlete.getEndurance() * 5);
	}
}