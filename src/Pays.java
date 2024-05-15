import java.util.ArrayList;
import java.util.List;


public class Pays {
	private String nom;
	List<Object> lesEquipes;

	/**
	 * @param String le nom du pays
	 */
	public Pays(String nom) {
		this.nom = nom;
		this.lesEquipes = new ArrayList<>();
	}

	/**
	 * @return String le nom du pays
	 */
	public String getNomPays() {
		return this.nom;
	}

}