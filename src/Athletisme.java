import java.util.Random;

public class Athletisme extends Sport {

	public Athletisme() {
		super(1, "Athletisme");
	}

	@Override
	public int bareme(Athlete athlete){
		Random e = new Random();
		return (int)((athlete.getAgilite() * 5 + athlete.getForce() *2 + (e.nextInt(20)+1) * 9 + athlete.getEndurance() * 3)/10);
	}
}