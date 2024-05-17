import java.util.Random;

public class Athletisme extends Sport {

	public Athletisme() {
		super(1, "Athletisme", 1,3,5,1);
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