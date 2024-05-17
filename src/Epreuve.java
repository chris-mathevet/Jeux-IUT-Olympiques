import java.util.ArrayList;
import java.util.List;

public class Epreuve<T extends Participant> {
	private String description;
	private Sport leSport;
	private char sexe;
	private List<Match<T>> lesMatchs;
	private List<T> lesParticipants;
	private T premier; 
	private T second; 
	private T troisieme; 
	private List<T> leClassement;

	/**
	 * @param String Description de l'épreuve 
	 * @param Sport Le sport correspondant à cette épreuve
	 * @param char Le sexe des participants de l'épreuve
	 */
	public Epreuve(String description, Sport sport, char sexe) {
		this.description = description;
		this.leSport = sport;
		this.sexe = sexe;
		this.lesMatchs = new ArrayList<>();
		this.lesParticipants = new ArrayList<>();
		this.premier = null;
		this.second = null;
		this.troisieme = null;
		this.leClassement = null;
	}

	public List<T> getLesParticipants() {
		return this.lesParticipants;
	}

	public Sport getSport() {
		return this.leSport;
	}

	public String getDescription() {
		return this.description;
	}
	
	public char getSexe() {
		return this.sexe;
	}

	public T getPremier() {
		return this.premier;
	}

	public T getSecond() {
		return this.second;
	}

	public T getTroisieme() {
		return this.troisieme;
	}

	public List<Match<T>> getLesMatchs() {
		return this.lesMatchs;
	}

	public List<T> getLeClassement() {
		if(this.leClassement.isEmpty()){this.classement();}
		return this.leClassement;
	}

	private void setPremier(T premier) {
		this.premier = premier;
	}

	private void setSecond(T second) {
		this.second = second;
	}

	private void setTroisieme(T troisieme) {
		this.troisieme = troisieme;
	}

	/** Ajoute un match a la liste de match
	 * @param Match<T> le match a ajouté
	 */
	public void ajoutMatch(Match<T> match){
		this.lesMatchs.add(match);
	}

	/**
	 * Inscrit une équipe ou un athlete à une épreuve
	 * @param Athlete L'athlete à inscrire a l'épreuve 
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
		for (Match<T> match : lesMatchs){
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
	private void classement() {
		List<Integer> resultats = this.cumulResultats();
		this.leClassement = new ArrayList<>(this.lesParticipants);
		int indMinMax = 0;
		Integer tmp = null;

		// Fabrication du classement selon le cumul de résultat, 
		// si il est en temps, le résultat est calculé selon la méthode du minimum (plus petit temps en premier)
		// sinon le résultat est calculé selon la méthode du maximum (plus grand nombre de points en premier)
		if(this.getSport().getEstTemsp()){
			for (int i = 0; i<resultats.size();++i){
				indMinMax = Match.indiemeMin(resultats, i); // Indice du min
				// Permutation du min et de l'actuel
				tmp = resultats.get(i);
                resultats.set(i, resultats.get(indMinMax));
                resultats.set(indMinMax,tmp);
				// MAJ du classement
				this.leClassement.set(i, this.lesParticipants.get(indMinMax));
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
				this.leClassement.set(i, this.lesParticipants.get(indMinMax));
			}
		}
	}

	/** Met a jour le podium de l'épreuve (attributs)
	 */
	public void majPodium(){
		if(this.leClassement != null){
			this.setPremier(this.leClassement.get(0));
			this.setSecond(this.leClassement.get(1));
			this.setTroisieme(this.leClassement.get(2));
		}
	}

	/** Met a jour le nombre de médaille des pays du podium
	 */
	public void majMedailles(){
		if(this.leClassement != null){
			if(this.premier == null){
				this.majPodium();
				this.premier.getPays().addMedailleOr(1);
				this.second.getPays().addMedailleArgent(1);
				this.troisieme.getPays().addMedailleBronze(1);
			}
			else{
				this.premier.getPays().addMedailleOr(-1);
				this.second.getPays().addMedailleArgent(-1);
				this.troisieme.getPays().addMedailleBronze(-1);
				this.majPodium();
				this.premier.getPays().addMedailleOr(1);
				this.second.getPays().addMedailleArgent(1);
				this.troisieme.getPays().addMedailleBronze(1);
			}
		}
	}

	@Override
	public String toString() {
		return this.description + " sport: " + this.leSport.toString() + " sexe: " + this.sexe;
	}
}