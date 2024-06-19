package MVC.modele;

import epreuves.*;
import exceptions.*;
import participants.*;
import sports.*;
import comparateurs.*;
import database.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
    
import executable.LibCreation;
import javax.xml.crypto.AlgorithmMethod;

public class ModeleJOBD {
    private Requete requete;
    public enum Tris{NATUREL, MEDAILLES, TOTAL}

    public ModeleJOBD(){
        ConnexionMySql co;
        try {
            co = new ConnexionMySql();
        } catch (SQLException e) {
            co = null;
            e.printStackTrace();
        }
        this.requete = new Requete(co);
    }

    public List<Athlete> getLesAthletes() {
        try {
            return this.requete.selectAthlete();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Equipe> getLesEquipes() {
        try {
            return this.requete.sele();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}