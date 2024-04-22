import java.util.ArrayList;
import java.util.List;

public class Match_individuel extends Match {
	private Epreuve_Individuelle epreuve;

	public Match_individuel(int nbTour, String nomTour, Epreuve_Individuelle epreuve) {
		super(nbTour, nomTour);
		this.epreuve = epreuve;
	}

	public Epreuve_Individuelle getEpreuve(){
		return this.epreuve;
	}

	/**
	 * Rencoie les résultats des participants du match (index partagé avec la liste de participants de l'épreuve)
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

	/**
	 * Renvoie le résultat d'un Athlete pour un match
	 * @param athlete L'athlete dont on veut le résultat
	 * @return int le résultat de l'athlete, -1 s'il n'a pas participé
	 */	
	public int getResultatAthlete(Athlete athlete){
		int index = this.epreuve.getLesParticipants().indexOf(athlete);
		if(index == -1){
			return -1;
		}
		else{
			return super.getResultats().get(index);
		}	
	}
}