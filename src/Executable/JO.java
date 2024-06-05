package Executable;

import epreuves.*;
import database.*;
import exceptions.*;
import participants.*;
import sports.*;
import comparateurs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JO {
    private List<Sport> lesSports;
    private List<Athlete> lesAthletes;
    private List<Equipe> lesEquipes;
    private List<Epreuve<? extends Participant>> lesEpreuves;
    private List<Pays> lesPays;

    private enum tris{NATUREL, MEDAILLES, TOTAL}

    private void triPays(tris leTri){
        switch (leTri) {
            case MEDAILLES:
                Collections.sort(lesPays, new ComparateurMedailles());
                break;
            
            case TOTAL:
                Collections.sort(lesPays, new ComparateurTotal());
                break;
        
            default:
                Collections.sort(lesPays);
                break;
        }
    }    

    public void init(){
        this.lesSports = Arrays.asList(new VoleyBall(), new HandBall(), new Escrime(), new Natation(), new Athletisme());
        this.lesAthletes = new ArrayList<>();
        this.lesEquipes = new ArrayList<>();
        this.lesEpreuves = new ArrayList<>();
        this.lesPays = new ArrayList<>();
        // Import de la BD
    }   

    public void creerPays(){
        System.out.println("Vous allez créer un pays\nEntrez un nom de Pays.");
        String commandePays = System.console().readLine();
        commandePays = commandePays.strip();
        System.out.println("Voulez vous vraiment créer le pays : " + commandePays + " (Y/N)");
        String commandeVerif = System.console().readLine();
        commandeVerif = commandeVerif.strip().toUpperCase();
        if(commandeVerif.equals("Y")){
            Pays pays = new Pays(commandePays);
            if( ! (lesPays.contains(pays))){
                this.lesPays.add(pays);
                System.out.println("Le pays " + commandePays + " a bien été rajouté.");
            }
            else{
                System.err.println("Ce pays existe déjà.");
            }
            
        }
        else{
            System.out.println("Le pays n'a pas été rajouté.");
        }
    }
}
