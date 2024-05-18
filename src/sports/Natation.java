package sports;

import java.util.Random;

import participants.Athlete;

public class Natation extends Sport {
	private static final int modifierTemps100m = 42; 
	private static final int modifierTemps4x100m = 180; 

	public Natation() {
		super(1, "Natation", 1, 5, 2, 2,true);
	}

	public static int getModifierTemp100(){
		return Natation.modifierTemps100m;
	}

	public static int getModifiertemps4x100m() {
		return Natation.modifierTemps4x100m;
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