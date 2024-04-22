import java.util.ArrayList;
import java.util.List;

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

	public void ajoutMatch(Match_individuel match){
		this.lesMatchs.add(match);
	}

	/**
	 * Inscrit un Athlete à une épreuve
	 * @param Athlete l'athlete à inscrire
	 */
	public void inscrire(Athlete athlete) {
		if (! this.lesParticipants.contains(athlete)){
			this.lesParticipants.add(athlete);
		}
	}

	/**
	 * Renvoie la liste des résultats de chaque matchs cumulés
	 * @return List<Integer> La liste cumulé
	 */
	private List<Integer> cumulResultats(){
		List<Integer> resultats = new ArrayList<>();
		List<Integer> resultatMatch = new ArrayList<>();

		// Cumul les résultats des différents matchs
		for (Match_individuel match : lesMatchs){
			if(resultats.isEmpty()){
				resultats = new ArrayList<>(match.resultat());
			}
			else{
				resultatMatch = match.resultat();
				for(int i = 0; i<resultats.size();++i){
					resultats.set(i, resultats.get(i) + resultatMatch.get(i));
				}
			}
		}
		return resultats;
	}

	/**
	 * Renvoie le classement pour l'épreuve
	 * @param boolean vrai si l'épreuve se fait selon un temps
	 * @return List<Athlete> Le classement pour une épreuve
	 */
	public List<Athlete> classement(boolean is_timed) {
		List<Integer> resultats = this.cumulResultats();
		List<Athlete> classement = new ArrayList<>(this.lesParticipants);
		int indMinMax = 0;
		Integer tmp = null;

		// Fabrication du classement selon le cumul de résultat, 
		// si il est en temps, le résultat est calculé selon la méthode du minimum (plus petit temps en premier)
		// sinon le résultat est calculé selon la méthode du maximum (plus grand nombre de points en premier)
		if(is_timed){
			for (int i = 0; i<resultats.size();++i){
				indMinMax = Match.indiemeMin(resultats, i); // Indice du min
				// Permutation du min et de l'actuel
				tmp = resultats.get(i);
                resultats.set(i, resultats.get(indMinMax));
                resultats.set(indMinMax,tmp);
				// MAJ du classement
				classement.set(i, this.lesParticipants.get(indMinMax));
			}
		}
		else{
			for (int i = 0; i<resultats.size();++i){
				indMinMax = Match.indiemeMax(resultats, i); // Indice du max
				// Permutation du max et de l'actuel
				tmp = resultats.get(i);
                resultats.set(i, resultats.get(indMinMax));
                resultats.set(indMinMax,tmp);
				// MAJ du classement
				classement.set(i, this.lesParticipants.get(indMinMax));
			}
		}
		return classement;
	}
}