package Executable;

import epreuves.*;
import database.*;
import exceptions.*;
import participants.*;
import sports.*;
import comparateurs.*;

import java.util.ArrayList;
import java.util.Collections;

public class JO {
    private ArrayList<Sport> lesSports;
    private ArrayList<Athlete> lesAthletes;
    private ArrayList<Equipe> lesEquipes;
    private ArrayList<Epreuve<? extends Participant>> lesEpreuves;
    private ArrayList<Pays> lesPays;

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
        this.lesSports = new ArrayList<>();
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
            this.lesPays.add(new Pays(commandePays));
            System.out.println("Le pays " + commandePays + " a bien été rajouté");
        }
        else{
            System.out.println("Le pays n'a pas été rajouté");
        }
    }
    
}
