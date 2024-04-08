
import java.util.ArrayList;
import java.util.List;

public class Equipe implements Participant {
	private String nom;
	private List <Athlete> lesAthletes;


	/**
	 * @param nom
	 */
	public Equipe(String nom) {
		this.nom = nom;
		this.lesAthletes = new ArrayList<>();
	}

	/**
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param Match match 
	 * @return
	 */
	public int participer(Match match) {
		int sommeBareme = 0;
		for (Athlete athlete : this.lesAthletes) {
			sommeBareme += match.getEpreuve().getSport().bareme(athlete);
		}

		return sommeBareme;
	}
}