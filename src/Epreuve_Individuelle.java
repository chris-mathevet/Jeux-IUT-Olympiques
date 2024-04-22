import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Epreuve_Individuelle extends Epreuve {
	private List<Athlete> lesParticipants;
	private List<Match_individuel> lesMatchs;

	public Epreuve_Individuelle(String description, Sport sport, char sexe) {
		super(description, sport, sexe);
		this.lesParticipants = new ArrayList<>();
	}

	public List<Athlete> getLesParticipants() {
		return this.lesParticipants;
	}

	/**
	 * @return
	 */
	public List<Athlete> classement() {
		// Prendre tous les matchs pour chaque matchs, on récupère le résultats, et on fait un trie par rapport aux index de chaque équipe pour chaque match
		List<Athlete> classement = new ArrayList<>();
		int indexList = 0;
		int index_match = 0;
		for (Athlete athlete : lesParticipants){
			for(Match_individuel leMatch : lesMatchs){
				index_match = leMatch.resultat().indexOf(athlete);
				if(index_match == -1){ index_match = 0;}
				indexList += index_match;
			}
			indexList/= lesMatchs.size();
		}
		return null;
	}

	/**
	 * @param Athlete 
	 * @return
	 */
	public void inscrire(Athlete athlete) {
		if (! this.lesParticipants.contains(athlete)){
			this.lesParticipants.add(athlete);
		}
	}
}