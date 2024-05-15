


public class AlreadyInException extends Exception{
    
    public AlreadyInException(String message) {
        super(message);
    }    
    
    public AlreadyInException() {
        super("a");
    }
    
}
