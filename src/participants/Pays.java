package participants;

import java.util.ArrayList;
import java.util.List;

public class Pays {
	private String nom;
	private List<Equipe> lesEquipes;
	private List<Athlete> lesAthletes;
	private int medailleOr;
	private int medailleArgent;
	private int medailleBronze;

	/**
	 * @param String le nom du pays
	 */
	public Pays(String nom) {
		this.nom = nom;
		this.lesEquipes = new ArrayList<>();
		this.lesAthletes = new ArrayList<>();
		this.medailleOr = 0;
		this.medailleArgent = 0;
		this.medailleBronze = 0;
	}

	public List<Equipe> getLesEquipes() {
		return this.lesEquipes;
	}

	public List<Athlete> getLesAthletes() {
		return this.lesAthletes;
	}

	public String getNomPays() {
		return this.nom;
	}

	public int getMedailleOr() {
		return this.medailleOr;
	}

	public int getMedailleArgent() {
		return this.medailleArgent;
	}

	public int getMedailleBronze() {
		return this.medailleBronze;
	}

	public void addMedailleOr(int medailleOr) {
		this.medailleOr += medailleOr;
	}

	public void addMedailleArgent(int medailleArgent) {
		this.medailleArgent += medailleArgent;
	}

	public void addMedailleBronze(int medailleBronze) {
		this.medailleBronze += medailleBronze;
	}

	@Override
	public String toString() {
		return this.nom + " or: " + this.medailleOr + " argent: " + this.medailleArgent + " bronze: " + this.medailleBronze;
	}
}