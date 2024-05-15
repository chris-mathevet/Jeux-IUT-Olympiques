



public class NotSameCountryException extends Exception{
    
    public NotSameCountryException(String message) {
        super(message);
    }    
    
    public NotSameCountryException() {
        super("b");
    }
    
}
