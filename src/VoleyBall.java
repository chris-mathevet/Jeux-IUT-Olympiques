import java.util.Random;

public class VoleyBall extends Sport {

	public VoleyBall(int coefForce, int coefEndurance, int coefAgilite, int coefRandom) {
		super(6, "Voley", coefForce, coefEndurance, coefAgilite, coefRandom);	
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