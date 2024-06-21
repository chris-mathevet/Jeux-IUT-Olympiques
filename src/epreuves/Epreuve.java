package epreuves;

import java.util.ArrayList;
import java.util.List;

import exceptions.AlreadyInException;
import exceptions.CanNotRegisterException;
import exceptions.NotSameGenderException;
import participants.*;
import sports.Sport;

public class Epreuve<T extends Participant> {
	private String description;
	private Sport leSport;
	private char sexe;
	private List<Manche<T>> lesManches;
	private List<T> lesParticipants;
	private T premier; 
	private T second; 
	private T troisieme; 
	private List<Double> lesResultats;
	private List<T> leClassement;

	/**
	 * @param String Description de l'épreuve 
	 * @param Sport Le sport correspondant à cette épreuve
	 * @param char Le sexe des participants de l'épreuve
	 */
	public Epreuve(String description, Sport sport, char sexe) {
		this.description = description;
		this.leSport = sport;
		this.sexe = sexe;
		if (this.sexe != 'F'){
			this.sexe = 'H';
		}
		this.lesManches = new ArrayList<>();
		this.lesParticipants = new ArrayList<>();
		this.premier = null;
		this.second = null;
		this.troisieme = null;
		this.leClassement = new ArrayList<>();
		this.lesResultats = new ArrayList<>();
	}

	public List<T> getLesParticipants() {
		return this.lesParticipants;
	}

	public Sport getSport() {
		return this.leSport;
	}

	public String getDescription() {
		return this.description;
	}
	
	public char getSexe() {
		return this.sexe;
	}

	public T getPremier() {
		return this.premier;
	}

	public T getSecond() {
		return this.second;
	}

	public T getTroisieme() {
		return this.troisieme;
	}

	public List<Manche<T>> getLesManches() {
		return this.lesManches;
	}

	public List<T> getLeClassement() {
		if((this.lesParticipants.size()>2)){
			if(this.leClassement.isEmpty()){this.classement();}
			this.majMedailles();
			return this.leClassement;
		}

		return null;
	}

	private void setPremier(T premier) {
		this.premier = premier;
	}

	private void setSecond(T second) {
		this.second = second;
	}

	private void setTroisieme(T troisieme) {
		this.troisieme = troisieme;
	}

	/** Ajoute un Manche a la liste de Manche
	 * @param Manche<T> le Manche a ajouté
	 */
	public void ajoutManche(Manche<T> manche){
		this.lesManches.add(manche);
	}

	/**
	 * Inscrit une équipe ou un athlete à une épreuve
	 * @param Athlete L'athlete à inscrire a l'épreuve 
	 */
	public void inscrire(T participant) 
	 throws AlreadyInException, CanNotRegisterException, NotSameGenderException{
		int taille = leSport.getNbParEquipe();
		boolean peutInscrire = false;
		boolean estAthlete = participant instanceof Athlete;
		// Voit si le participant peut s'inscrire ou non
		if(estAthlete){
			peutInscrire = (taille == 1);
		}
		else{
			peutInscrire = (taille == ((Equipe) participant).size());
		}
		// S'il ne peut pas s'inscrire, léve l'exeption ne peut pas s'inscrire
		if( ! peutInscrire){
			if(estAthlete){
				throw new CanNotRegisterException("Un athelete ne peux s'inscrire à une épreuve collective.");
			}
			else{
				throw new CanNotRegisterException("Cet équipe ne peut pas s'incrire, taille équipe: " + ((Equipe) participant).size() + " taille requise: " + taille);
			}
		}
		// Sinon
		else{
			if(this.lesParticipants.contains(participant)){
				if(estAthlete){
					throw new AlreadyInException("Cet athlete est déjà inscrit à cette épreuve");
				}
				else{
					throw new AlreadyInException("Cette équipe est déjà inscrite à cette épreuve");
				}
			}
			else if(participant.getSexe() != this.getSexe()) {
				if(estAthlete){
					throw new NotSameGenderException("Cet athlete n'est pas du même sexe que l'épreuve");
				}
				else{
					throw new NotSameGenderException("Cette équipe n'est pas du même sexe que l'épreuve");
				}
			}
			else{
				this.lesParticipants.add(participant);
			}
		}
	}

	/**
	 * Renvoie la liste des résultats de chaque Manches cumulés
	 * @return List<Double> La liste cumulé
	 */
	private List<Double> cumulResultats(){
		List<Double> resultats = new ArrayList<>();
		List<Double> resultatManche = new ArrayList<>();

		// Cumul les résultats des différents Manches
		for (Manche<T> manche : lesManches){
			if(resultats.isEmpty()){
				resultats = new ArrayList<>(manche.getResultats());
			}
			else{
				resultatManche = manche.getResultats();
				for(int i = 0; i<resultats.size();++i){
					resultats.set(i, resultats.get(i) + resultatManche.get(i));
				}
			}
		}
		System.out.println(resultats);
		return resultats;
	}

	/**
	 * Renvoie la liste des moyennes des points de chaque participants
	 * @return List<Double> La liste de moyenne
	 */
	private List<Double> moyResultats(){
		List<Double> resultats = this.cumulResultats();
		int taille = this.lesManches.size();
		for(Double res : resultats){
			res /= taille;
		}
		return resultats;
	}

	/**
	 * Renvoie le classement pour l'épreuve
	 * @param boolean vrai si l'épreuve se fait selon un temps
	 * @return List<T> Le classement pour une épreuve
	 */
	private void classement() {
		this.lesResultats = this.moyResultats();
		System.out.println("Les res");
		System.out.println(this.lesResultats);
		this.leClassement = new ArrayList<>(this.lesParticipants);
		int indMinMax = 0;
		Double tmp = null;

		// Fabrication du classement selon les moyennes de résultat, 
		// si il est en temps, le résultat est calculé selon la méthode du minimum (plus petit temps en premier)
		// sinon le résultat est calculé selon la méthode du maximum (plus grand nombre de points en premier)
		if(this.getSport().getEstTemps()){
			for (int i = 0; i<this.lesResultats.size();++i){
				indMinMax = Manche.indiemeMin(this.lesResultats, i); // Indice du min
				// Permutation du min et de l'actuel
				tmp = this.lesResultats.get(i);
                this.lesResultats.set(i, this.lesResultats.get(indMinMax));
                this.lesResultats.set(indMinMax,tmp);
				// MAJ du classement
				this.leClassement.set(i, this.lesParticipants.get(indMinMax));
			}
		}
		else{
			for (int i = 0; i<this.lesResultats.size();++i){
				indMinMax = Manche.indiemeMax(this.lesResultats, i); // Indice du max
				// Permutation du max et de l'actuel
				tmp = this.lesResultats.get(i);
                this.lesResultats.set(i, this.lesResultats.get(indMinMax));
                this.lesResultats.set(indMinMax,tmp);
				// MAJ du classement
				this.leClassement.set(i, this.lesParticipants.get(indMinMax));
			}
		}
	}

	/** Met a jour le podium de l'épreuve (attributs)
	 */
	public void majPodium(){
		if( ! this.leClassement.isEmpty()){
			try{
				this.setPremier(this.leClassement.get(0));
				this.setSecond(this.leClassement.get(1));
				this.setTroisieme(this.leClassement.get(2));
			}
			catch(IndexOutOfBoundsException e){
				System.err.println("Probleme maj médailles");
				System.err.println(e.getMessage());
			}
		
		}
	}

	/** Met a jour le nombre de médaille des pays du podium
	 */
	public void majMedailles(){
		if( ! this.leClassement.isEmpty()){
			if(this.premier == null || this.second == null || this.troisieme == null){
				this.majPodium();
				try{
					this.premier.getPays().addMedailleOr(1);
					this.second.getPays().addMedailleArgent(1);
					this.troisieme.getPays().addMedailleBronze(1);

				} catch(Exception e){
					System.err.println("Probleme maj médailles, pas accès de participant pour cet épreuve");
					System.err.println(e.getMessage());
				}
			}
			else{
				try{
					this.premier.getPays().addMedailleOr(-1);
					this.second.getPays().addMedailleArgent(-1);
					this.troisieme.getPays().addMedailleBronze(-1);
					this.majPodium();
					this.premier.getPays().addMedailleOr(1);
					this.second.getPays().addMedailleArgent(1);
					this.troisieme.getPays().addMedailleBronze(1);
				} catch(Exception e){
					System.err.println("Probleme maj médailles, pas accès de participant pour cet épreuve");
					System.err.println(e.getMessage());
				}
			}
		}
		System.out.println();
	}

	public Double getResultatParticipant(T participant){
		if(this.getLeClassement() != null){
			return this.lesResultats.get(this.leClassement.indexOf(participant));
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null){return false;}
		if(obj == this){return true;}
		if (!(obj instanceof Epreuve)){return false;}
		Epreuve epreuve = (Epreuve) obj;
		return epreuve.getDescription().equals(this.getDescription()) && epreuve.getSexe() == this.getSexe();
	}

	@Override
	public String toString() {
		return this.description + " sport: " + this.leSport.toString() + " sexe: " + this.sexe;
	}
}