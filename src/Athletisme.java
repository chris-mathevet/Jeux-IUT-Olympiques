import java.util.Random;

public class Athletisme extends Sport {
	private static final int modifierTemps100m = 10; 
	private static final int modifierTemps4x100m = 30; 

	public Athletisme() {
		super(1, "Athletisme", 1,3,5,1,true);
	}

	public static int getModifierTemp100(){
		return Athletisme.modifierTemps100m;
	}

	public static int getModifiertemps4x100m() {
		return Athletisme.modifierTemps4x100m;
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