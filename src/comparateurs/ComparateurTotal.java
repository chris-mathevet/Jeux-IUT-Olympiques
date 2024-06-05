package comparateurs;

import java.util.Comparator;

import participants.Pays;

public class ComparateurTotal implements Comparator<Pays>{

    private int sommeMedaille(Pays pays){
        return pays.getMedailleArgent() + pays.getMedailleBronze() + pays.getMedailleOr();
    }


    
}
