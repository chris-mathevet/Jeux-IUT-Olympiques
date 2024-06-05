package comparateurs;

import java.util.Comparator;

import participants.Pays;

public class ComparateurTotal implements Comparator<Pays>{

    public ComparateurTotal(){
        super();
    }

    private int sommeMedaille(Pays pays){
        return pays.getMedailleArgent() + pays.getMedailleBronze() + pays.getMedailleOr();
    }

    @Override
    public int compare(Pays p1, Pays p2) {
        return this.sommeMedaille(p2)-this.sommeMedaille(p1);
    }    
}
