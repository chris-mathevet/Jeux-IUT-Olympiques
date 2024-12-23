package sports;

import java.util.Random;

import epreuves.Epreuve;
import participants.Athlete;

public abstract class Sport {
	private int nbParEquipe;
	private String nomSport;
	private int coefForce;
	private int coefEndurance;
	private int coefAgilite;
	private int coefRandom;
	private boolean estTemps;
	
	/**
	 * Constructeur instancie les sports
	 * 
	 * @param int Le nombre d'athlete par équipe pour ce sport
	 * @param String Le nom du Sport
	 * @param int Le coef force utilisé dans le bareme
	 * @param int Le coef endurance utilisé dans le bareme
	 * @param int Le coef agilité utilisé dans le bareme
	 * @param int Le coef random utilisé dans le bareme
	 * @param boolean si le résultat d'un sport est en temps
	 */
	public Sport(int nbParEquipe, String nomSport, int coefForce, int coefEndurance, int coefAgilite, int coefRandom, boolean estTemps) {
		this.nbParEquipe = nbParEquipe;
		this.nomSport = nomSport;
		
		this.coefForce = coefForce;
		this.coefEndurance = coefEndurance;
		this.coefAgilite = coefAgilite;
		this.coefRandom = coefRandom;
		this.estTemps = estTemps;
	}


	/**
	 * Retourne le nombre de personne dans l'equipe pour ce sport
	 * @return int
	 */
	public int getNbParEquipe() {
		return this.nbParEquipe;
	}
	/**
	 * Retourne le nom du sport 
	 * @return String
	 */
	public String getSport() {
		return this.nomSport;
	}
	/**
	 * Retourne le coef du paramettre Force dans le bareme pour ce sport
	 * @return int
	 */
	public int getCoefForce() {
		return this.coefForce;
	}
	/**
	 * Retourne le coef du paramettre Endurance dans le bareme pour ce sport
	 * @return int
	 */
	public int getCoefEndurance() {
		return this.coefEndurance;
	}
	/**
	 * Retourne le coef du paramettre Agilite dans le bareme pour ce sport
	 * @return int
	 */
	public int getCoefAgilite() {
		return this.coefAgilite;
	}
	/**
	 * Retourne le coef du paramettre Random dans le bareme pour ce sport
	 * @return int
	 */
	public int getCoefRandom() {
		return this.coefRandom;
	}

	/**
	 * Retourne si le résultat du sport doit être un temps
	 * @return boolean
	 */
	public boolean getEstTemps(){
		return this.estTemps;
	}

	
	/**
	 * change le coef du paramettre Force dans le bareme pour ce sport
	 * @return void
	 */
	public void setCoefForce(int coefForce) {
		this.coefForce = coefForce;
	}

	/**
	 * change le coef du paramettre Endurance dans le bareme pour ce sport
	 * @return void
	 */
	public void setCoefEndurance(int coefEndurance) {
		this.coefEndurance = coefEndurance;
	}

	/**
	 * change le coef du paramettre agilite dans le bareme pour ce sport
	 * @return void
	 */
	public void setCoefAgilite(int coefAgilite) {
		this.coefAgilite = coefAgilite;
	}

	/**
	 * change le coef du paramettre Random dans le bareme pour ce sport
	 * @return void
	 */
	public void setCoefRandom(int coefRandom) {
		this.coefRandom = coefRandom;
	}

	/**
	 * Retourne le nombre de point que l'athlete a eu en fonction du bareme donné (Avec les coefs)
	 * @param Athlete L'athlete dont on veut les points
	 * @return int Le nombre de point de l'athlete pour un sport
	 */
	public int bareme(Athlete athlete){
		Random e = new Random();
		return (int)(athlete.getAgilite() * this.getCoefAgilite() + 
					athlete.getForce() * this.getCoefForce() + 
					(e.nextInt(20)+1)* this.getCoefRandom() + 
					athlete.getEndurance()* this.getCoefEndurance());
	}

	@Override
	public String toString(){
		if (this.nbParEquipe>1){
			return this.nomSport + ",nb par equipe :" + this.nbParEquipe;
		}
		else{
			return this.nomSport + ",sport individuel";
		}
		
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null){return false;}
		if(obj == this){return true;}
		if (!(obj instanceof Sport)){return false;}
		Sport sport = (Sport) obj;
		return this.getSport().equals(sport.getSport()) && this.getNbParEquipe() == sport.getNbParEquipe();
	}
}