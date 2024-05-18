package exceptions;

public class NotSameCountryException extends Exception{
    
    public NotSameCountryException(String message) {
        super(message);
    }    
    
    public NotSameCountryException() {
        super("Le pays est différent");
    }
}
