package Executable;

import epreuves.*;
import database.*;
import exceptions.*;
import participants.*;
import sports.*;

import java.util.ArrayList;

public class JO {
    private ArrayList<Sport> lesSports;
    private ArrayList<Athlete> lesAthletes;
    private ArrayList<Equipe> lesEquipes;
    private ArrayList<Epreuve<? extends Participant>> lesEpreuves;
    private ArrayList<Pays> lesPays;

    private enum tris{NATUREL, GOLD, TOTAL}

    private void triPays(tris leTri){
        switch (leTri) {
            case GOLD:
                
                break;
            
            case TOTAL:
                
                break;
        
            default:
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
    
}
