package participants;

import epreuves.Manche;

public interface Participant {

	/**
	 * @param Manche<Participant> Le manche d'équipe ou d'athlete dans lequel le participant va participer
	 * @return int Le nombre de point que le joueur a réalisé
	 */
	public int participer(Manche<? extends Participant> manche);

	/**
	 * @return le Pays du participant
	 */
	public Pays getPays();
	
	/**
	 * @return le sexe du participant
	 */
	public char getSexe();
}