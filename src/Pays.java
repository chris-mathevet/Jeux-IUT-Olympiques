import java.util.ArrayList;
import java.util.List;


public class Pays {
	private String nom;
	List<Object> lesEquipes;
	private int medailleOr;
	private int medailleArgent;
	private int medailleBronze;

	/**
	 * @param String le nom du pays
	 */
	public Pays(String nom) {
		this.nom = nom;
		this.lesEquipes = new ArrayList<>();
		this.medailleOr = 0;
		this.medailleArgent = 0;
		this.medailleBronze = 0;
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

	public void setMedailleOr(int medailleOr) {
		this.medailleOr = medailleOr;
	}

	public void setMedailleArgent(int medailleArgent) {
		this.medailleArgent = medailleArgent;
	}

	public void setMedailleBronze(int medailleBronze) {
		this.medailleBronze = medailleBronze;
	}
}