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
	public int getResultatEquipe(Equipe equipe){
		int index = this.epreuve.getLesParticipants().indexOf(equipe);
		if(index == -1){
			return -1;
		}
		else{
			return super.getResultats().get(index);
		}	
	}
	public List<Integer> resultat() {
		if(super.getResultats().isEmpty()){
			List<Integer> res = super.getResultats();
			List<Equipe> participants = this.epreuve.getLesParticipants();
			for(Equipe equipe : participants){
				res.add(equipe.participer(this));
			}
			return res;
		}
		return super.getResultats();
	}
}