import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Epreuve<T> {
	private String description;
	private Sport leSport;
	private char sexe;
	private List<Match> lesMatchs;
	private List<T>  lesParticipants;

	/**
	 * @param description String 
	 * @param sport Sport 
	 * @param sexe
	 */
	public Epreuve(String description, Sport sport, char sexe) {
		this.description = description;
		this.leSport = sport;
		this.sexe = sexe;
		this.lesMatchs = new ArrayList<>();
		this.lesParticipants = new ArrayList<>();
	}

	public List<T> getLesParticipants() {
		return this.lesParticipants;
	}

	public Sport getSport() {
		return this.leSport;
	}

	public void ajoutMatch(Match match){
		this.lesMatchs.add(match);
	}

	/**
	 * Inscrit une équipe ou un athlete à une épreuve
	 * @param Athlete l'athlete à inscrire
	 */
	public void inscrire(T participant) {
		if (! this.lesParticipants.contains(participant)){
			this.lesParticipants.add(participant);
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
		for (Match match : lesMatchs){
			if(resultats.isEmpty()){
				resultats = new ArrayList<>(match.getResultats());
			}
			else{
				resultatMatch = match.getResultats();
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
	 * @return List<T> Le classement pour une épreuve
	 */
	public List<T> classement(boolean is_timed) {
		List<Integer> resultats = this.cumulResultats();
		List<T> classement = new ArrayList<>(this.lesParticipants);
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