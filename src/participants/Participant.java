package participants;

import epreuves.Match;

public interface Participant {

	/**
	 * @param Match<Participant> Le match d'équipe ou d'athlete dans lequel le participant va participer
	 * @return int Le nombre de point que le joueur a réalisé
	 */
	public int participer(Match<? extends Participant> match);

	public Pays getPays();
}