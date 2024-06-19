package MVC.modele;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


import database.*;

public class ModeleConnexion {
    private String identifiant;
    private String mdp;
    private String mdpVerif;
    private String mail;
    /**true si mode Connexion, sinon false (Inscription)*/
    private boolean estConnexion;

    ConnexionMySql co;

    Requete requete ;

    public ModeleConnexion(){
        this.identifiant = "";
        this.mdp = "";
        this.mdpVerif = "";
        this.mail = "";
        this.estConnexion = true;

        try {
            
            this.co = new ConnexionMySql("meunier");
            this.requete = new Requete(co);

        } catch (SQLException e) {
            System.err.println("probleme connexion base de donnée");
        }
    }
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setMdpVerif(String mdpVerif) {
        this.mdpVerif = mdpVerif;
    }

    public void changementMode() {
        this.estConnexion = ! this.estConnexion;
    }

    public boolean getEstConnexion(){
        return this.estConnexion;
    }

    private boolean contient(String contenu, String doitContenir, int nb){
        int nbOcc = 0;
        for (int i = 0; i<contenu.length(); ++i){
            if(doitContenir.contains(contenu.charAt(i) + "")){++nbOcc;}
        }
        return nbOcc>=nb;
    }

    // Partie verif MAIL __________________________________________________________

    /** Renvoie vrai si le mail est bien constitué (au moins un @ et un . après)
     * @return boolean
     */
    public boolean mailVerif(){
        int verif = 0;
        for (int i = 0; i<this.mail.length(); ++i){
            if(verif == 0 && this.mail.charAt(i)=='@'){++verif;}
            if(verif == 1 && this.mail.charAt(i)=='.'){++verif;}
        }
        return verif>1;
    }

    /** Renvoie vrai si le mail n'existe pas dans la BD
     * @return boolean
     */

     public boolean mailNonExistant(){
        Set<String> lesMails = new HashSet<>();
        try {
            lesMails = requete.selectUserMail();

        } catch (SQLException e) {
            System.err.println("Probleme lors de la recuperation des Emails ! \n l'Erreur :" + e);
        }
        if(!(lesMails.contains(this.mail))){return true;}
        return false;
    }

    /** Renvoie si le mail est valide (toutes vérifications)
     * @return boolean
     */
    public boolean mailCorecte(){
        return this.mailVerif() && this.mailNonExistant();
    }

    // Partie verif IDENTIFIANT __________________________________________________________

    /** Renvoie vrai si l'identifiant n'existe pas dans la BD
     * @return
     */
    public boolean identifiantNonExistant(){
        Set<String> lesUsers = new HashSet<>();
        try {
            lesUsers = requete.selectUser();

        } catch (SQLException e) {
            System.err.println("Probleme lors de la recuperation des Users ! \n l'Erreur :" + e);
        }
        if(!(lesUsers.contains(this.identifiant))){return true;}
        return false;
    }

    /**
     * Renvoie vrai si le mdp contient 8 caractères
     * @return
     */
    public boolean identifiantContient8(){
        return this.identifiant.length()>=8;
    }

    /** Renvoie si l'identifiant est valide (toutes vérifications)
     * @return boolean
     */
    public boolean identifiantCorect(){
        return this.identifiantNonExistant() && this.identifiantContient8();
    }

    // Partie verif MDP __________________________________________________________

    /**
     * Renvoie vrai si le mdp contient 8 caractères
     * @return
     */
    public boolean mdpContient8(){
        return this.mdp.length()>=8;
    }

    /**
     * Renvoie vrai si le mdp contient une majuscule
     * @return
     */
    public boolean mdpContientMaj(){
        return this.contient(this.mdp, "ABCDEFGHIJKLMONPQRSTUVWXYZ", 1);
    }

    /**
     * Renvoie vrai si le mdp contient une minuscule
     * @return
     */
    public boolean mdpContientMin(){
        return this.contient(this.mdp, "abcdefghijklmnopqrstuvwxyz", 1);
    }
    
    /**
     * Renvoie vrai si le mdp contient un chiffre
     * @return
     */
    public boolean mdpContientChifre(){
        return this.contient(this.mdp, "0123456789", 1);
    }

    /**
     * Renvoie vrai si le mdp contient un caractère spécial
     * @return
     */
    public boolean mdpContientSpecial(){
        return this.contient(this.mdp, "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~", 1);
    }

    /** Renvoie si l'identifiant est valide (toutes vérifications)
     * @return boolean
     */
    public boolean mdpCorecteInscription(){
        boolean res = true;
        if(! this.mdpContient8()){res = false;}
        if(! this.mdpContientMaj()){res = false;}
        if(! this.mdpContientMin()){res = false;}
        if(! this.mdpContientChifre()){res = false;}
        if(! this.mdpContientSpecial()){res = false;}
        return res;
    }
 
    // Partie verif MDPVERIF __________________________________________________________

    /**
     * Renvoie vrai si mdp et mdpverif sont les même 
     * @return
     */
    public boolean mdpVerif(){
        return this.mdp.equals(this.mdpVerif);
    }

    // FIN PARTIES __________________________________________________________

    /**
     * Crypte le message
     * @return
     */
    private int cryptage(){
        return this.mdp.hashCode()*97;
    }

    /**
     * Renvoie vrai si toutes les informations permettent l'inscription
     * @return
     */
    public boolean peutSinscrire(){
        if( ! this.estConnexion){
            return this.mailVerif() && this.mdpVerif() && this.mdpCorecteInscription()  && this.identifiantCorect();
        }
        return false;
    }

    /**
     * Renvoie le mdp hashé si on peut s'iscrire, sinon 0
     * @return
     */
    public int inscrire(){
        int mdp = 0;
        if(this.peutSinscrire()){
            mdp = this.cryptage();
        }
        return mdp;
    }

    /**
     * Renvoie vrai si l'utilisateur peut se connecter
     * @return
     */
    public boolean peutSeConnecter(){
        if(this.estConnexion){
            if(! this.identifiantNonExistant()){return false;}
            return true;
        }
        return false;
    }

    /**
     * Renvoie vrai si la connexion s'est efféctué
     * @return
     */
    public boolean connexion(){
        return true;
    }
}
