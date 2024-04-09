import java.util.ArrayList;
import java.util.List;


public class Pays {
	private String nom;
	List<Object> lesEquipes;

	/**
	 * @param nom
	 */
	public Pays(String nom) {
		this.nom = nom;
		this.lesEquipes = new ArrayList<>();
	}

	/**
	 * @return
	 */
	public String getNomPays() {
		return this.nom;
	}

}