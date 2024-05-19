package exceptions;

public class NotSameGenderException extends Exception{

    public NotSameGenderException(String message) {
        super(message);
    }    
    
    public NotSameGenderException() {
        super("Le sexe est différent");
    }
}
