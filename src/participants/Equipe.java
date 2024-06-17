package participants;

import java.util.ArrayList;

import epreuves.Manche;
import exceptions.AlreadyInException;
import exceptions.NotSameCountryException;
import exceptions.NotSameGenderException;

public class Equipe extends ArrayList<Athlete> implements Participant{
	private String nom;

	/**
	 * @param String le nom de l'équipe
	 */
	public Equipe(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * @return Pays le Pays de l'équipe, ou null si l'équipe est vide
	 */
	@Override
	public Pays getPays(){
		if (! this.isEmpty()){
			return this.get(0).getPays();
		}
		return null;
	}

	/**
	 * @return char le sexe de l'équipe (F, H); ou ' ' so l'équipe est vide
	 */
	@Override
	public char getSexe(){
		if (! this.isEmpty()){
			return this.get(0).getSexe();
		}
		return ' ';
	}

	/**
     * Permet d'ajouter un athlete dans l'équipe, si il n'est pas déjà dedans sinon on renvoie l'exception "AlreadyInException" créer à cet effet.
     * @param Athlete athlete 
     * @return void
     */

	 public void ajouter(Athlete athlete) throws AlreadyInException, NotSameCountryException, NotSameGenderException{

		if (this.isEmpty()) {
			this.add(athlete);
			this.getPays().getLesEquipes().add(this);
		}
		else{
			if (this.contains(athlete)) {
				throw new AlreadyInException("Cet athlete est déjà dans cette équipe.");
			}
			else{
				if (!(athlete.getPays().equals(this.get(0).getPays()))) {
					throw new NotSameCountryException("Vous ne pouvez pas ajouté un athlete d'un pays différent des autres membres.");
				}
				else if(athlete.getSexe() != this.getSexe()){
					throw new NotSameGenderException("Vous ne pouvez pas ajouté un athlete d'un sexe différent des autres membres.");
				}
				else{
					this.add(athlete);
				}
			}
		}
		
    }

	/**
	 * @param Manche manche 
	 * @return int (somme des bareme des athlete de l'équipe)
	 */
	@Override
	public int participer(Manche<? extends Participant> manche) {
		int sommeBareme = 0;
		for (Athlete athlete : this) {
			sommeBareme += athlete.participer(manche);
		}
		return sommeBareme;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null){return false;}
		if(o == this){return true;}
		if(! (o instanceof Equipe)){return false;}
		Equipe equipe = (Equipe) o;
		return this.nom.equals(equipe.getNom());
	}

	@Override
	public String toString() {
		String res = this.nom +" : \n";
		for (Athlete athlete : this){
			res += athlete + ",\n";
		}
		return res;
	}
}