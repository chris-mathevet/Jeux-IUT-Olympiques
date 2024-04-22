import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public abstract class Match {
	private int numéroDeTour;
	private String nomDeTour;
	private List<Integer> resultats;

	/**
	 * @param nbTour 
	 * @param nomTour 
	 */
	public Match(int nbTour, String nomTour) {
		this.numéroDeTour = nbTour;
		this.nomDeTour = nomTour;
		this.resultats = new ArrayList<>();
	}

	public static int indiemeMin(List<Integer> liste, int index){
        Integer min = null;
        int ind = index;
        for(int i = index; i<liste.size();++i){
            if(min == null || liste.get(i)<min){
                min = liste.get(i);
                ind = i;
            }
        }
        return ind;
    }

	public static int indiemeMax(List<Integer> liste, int index){
        Integer max = null;
        int ind = index;
        for(int i = index; i<liste.size();++i){
            if(max == null || liste.get(i)>max){
                max = liste.get(i);
                ind = i;
            }
        }
        return ind;
    }

	/**
	 * @return
	 */
	public int getNumeroDeTour() {
		return this.numéroDeTour;
	}

	/**
	 * @return
	 */
	public String getNomDeTour() {
		return this.nomDeTour;
	}

	public List<Integer> getResultats() {
		return this.resultats;
	}
}