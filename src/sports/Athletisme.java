package sports;

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
}