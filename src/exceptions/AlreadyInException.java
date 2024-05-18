package exceptions;

public class AlreadyInException extends Exception{
    
    public AlreadyInException(String message) {
        super(message);
    }    
    
    public AlreadyInException() {
        super("Déjà dedans");
    }   
}
