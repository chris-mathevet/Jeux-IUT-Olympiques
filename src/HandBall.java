import java.util.Random;

public class HandBall extends Sport {

	public HandBall(int coefForce, int coefEndurance, int coefAgilite, int coefRandom) {
		super(7, "Handball", coefForce, coefEndurance, coefAgilite, coefRandom);
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