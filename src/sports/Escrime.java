package sports;

import java.util.Random;

import participants.Athlete;

public class Escrime extends Sport {

	public Escrime() {
		super(1, "Escrime", 1, 2, 4, 3,false);
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