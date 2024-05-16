import java.util.Random;

public class Natation extends Sport {

	public Natation(int coefForce, int coefEndurance, int coefAgilite, int coefRandom) {
		super(1, "Natation", coefForce, coefEndurance, coefAgilite, coefRandom);
	}

	@Override
	public int bareme(Athlete athlete){
		Random e = new Random();
		return (int)(athlete.getAgilite() * super.getCoefAgilite() + 
					athlete.getForce() * super.getCoefForce() + 
					(e.nextInt(20)+1)* super.getCoefRandom() + 
					athlete.getEndurance()* super.getCoefEndurance());
	}
}