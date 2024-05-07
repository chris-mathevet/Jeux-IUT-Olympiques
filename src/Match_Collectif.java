import java.util.List;

/**
 * 
 */
public class Match_Collectif extends Match {
	private Epreuve_Collective epreuve;
	/**
	 * Default constructor
	 */
	public Match_Collectif(int nbTour, String nomTour, Epreuve_Collective epreuve) {
		super(nbTour, nomTour);
		this.epreuve = epreuve;
		this.epreuve.ajoutMatch(this);
	}
	public Epreuve_Collective getEpreuve() {
		return epreuve;
	}
	
	/**
	 * Renvoie le résultat d'une équipe pour un match
	 * @param equipe L'équipe dont on veut le résultat
	 * @return int le résultat de l'équipe, -1 s'il n'a pas participé
	 */	

	public int getResultatEquipe(Equipe equipe){
		int index = this.epreuve.getLesEquipes().indexOf(equipe);
		if(index == -1){
			return -1;
		}
		else{
			return super.getResultats().get(index);
		}	
	}
	/**
	 * Rencoie les résultats des équipes du match (index partagé avec la liste d'équipe de l'épreuve)
	 * @return List<Integer> les résultats des équipes pour un matchs
	 */
	public List<Integer> resultat() {
		if(super.getResultats().isEmpty()){
			List<Integer> res = super.getResultats();
			List<Equipe> equipes = this.epreuve.getLesEquipes();
			for(Equipe equipe : equipes){
				res.add(equipe.participer(this));
			}
			return res;
		}
		return super.getResultats();
	}
}