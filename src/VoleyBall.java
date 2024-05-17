import java.util.Random;

public class VoleyBall extends Sport {

	public VoleyBall() {
		super(6, "Voley", 4, 2, 3, 1);	
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