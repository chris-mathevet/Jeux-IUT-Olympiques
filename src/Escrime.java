import java.util.Random;

public class Escrime extends Sport {

	public Escrime() {
		super(1, "Escrime");
	}

	@Override
	public int bareme(Athlete athlete){
		Random e = new Random();
		return (int)(athlete.getAgilite() * 4 + athlete.getForce() + (e.nextInt(20)+1) * 3 + athlete.getEndurance() * 2);
	}

}