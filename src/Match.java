import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Match<T extends Participant> {
	private int numéroDeTour;
	private String nomDeTour;
	private List<Double> resultats;
	private Epreuve<T> epreuve;

	public Match(int nbTour, String nomTour, Epreuve<T> epreuve) {
		this.numéroDeTour = nbTour;
		this.nomDeTour = nomTour;
		this.epreuve = epreuve;
		this.epreuve.ajoutMatch(this);
		this.resultats = new ArrayList<>();
	}

	public int getNumeroDeTour() {
		return this.numéroDeTour;
	}

	public String getNomDeTour() {
		return this.nomDeTour;
	}

	public List<Double> getResultats() {
		if(this.resultats.isEmpty()){this.calculResultat();}
		return this.resultats;
	}
	
	public Epreuve<T> getEpreuve(){
		return this.epreuve;
	}

	/**
	 * Renvoie l'indice du minimum de la liste à l'indice donné
	 * @param liste La liste d'entier
	 * @param index L'indice de début
	 * @return int Lindice du minimum
	 */
	public static int indiemeMin(List<Double> liste, int index){
        Double min = null;
        int ind = index;
        for(int i = index; i<liste.size();++i){
            if(min == null || liste.get(i)<min){
                min = liste.get(i);
                ind = i;
            }
        }
        return ind;
    }

	/**
	 * Renvoie l'indice du maximum de la liste à l'indice donné
	 * @param liste La liste d'entier
	 * @param index L'indice de début
	 * @return int Lindice du maximum
	 */
	public static int indiemeMax(List<Double> liste, int index){
        Double max = null;
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
	 * Calcul les résultats des participants du match (index partagé avec la liste de participants de l'épreuve)
	 */
	private void calculResultat() {
		if(this.resultats.isEmpty()){
			List<T> participants = this.epreuve.getLesParticipants();
			for(T participant : participants){
				resultats.add((double) participant.participer(this));
			}
		}
	}
	/**
	 * Renvoie le résultat d'un participant pour un match
	 * @param T L'athlete / l'équipe dont on veut le résultat
	 * @return int le résultat de l'athlete / l'équipe, -1 s'il n'a pas participé
	 */	
	public double getResultatParticipant(T participant){
		if(this.resultats.isEmpty()){this.calculResultat();}
		int index = this.epreuve.getLesParticipants().indexOf(participant);
		if(index == -1){
			return -1;
		}
		else{
			return this.resultats.get(index);
		}	
	}

	@Override
	public String toString() {
		return this.nomDeTour + this.numéroDeTour;
	}
}