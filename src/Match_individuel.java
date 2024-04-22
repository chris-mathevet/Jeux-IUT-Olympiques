import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Match_individuel extends Match {
	private Epreuve_Individuelle epreuve;

	/**
	 * Default constructor
	 */
	public Match_individuel(int nbTour, String nomTour, Epreuve_Individuelle epreuve) {
		super(nbTour, nomTour);
		this.epreuve = epreuve;
	}

	public Epreuve_Individuelle getEpreuve(){
		return this.epreuve;
	}
	

	/**
	 * @return List<Integer> les résultats des participant pour un matchs
	 */
	public List<Integer> resultat() {
		if(super.getResultats().isEmpty()){
			List<Integer> res = super.getResultats();
			List<Athlete> participants = this.epreuve.getLesParticipants();
			for(Athlete athlete : participants){
				res.add(athlete.participer(this));
			}
			return res;
		}
		return super.getResultats();
	}

	public int getResultatAthlete(Athlete athlete){
		return super.getResultats().get(this.epreuve.getLesParticipants().indexOf(athlete));
	}

}