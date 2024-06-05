package comparateurs;

import java.util.Comparator;

import participants.Pays;

public class ComparateurMedailles implements Comparator<Pays>{

    public ComparateurMedailles(){
        super();
    }

    @Override
    public int compare(Pays p1, Pays p2) {
        int diffOr = p2.getMedailleOr() - p1.getMedailleOr();
        int diffAr = p2.getMedailleArgent() - p1.getMedailleArgent();
        int diffBr = p2.getMedailleBronze() - p1.getMedailleBronze();
        if (diffOr != 0){
            return diffOr;
        }
        else{
            if (diffAr != 0){
                return diffAr;
            }
            else{
                return diffBr;
            }
        }
    }
}
