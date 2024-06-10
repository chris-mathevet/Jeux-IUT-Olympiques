package exceptions;

public class DoesntExistException extends Exception{
    public DoesntExistException(){
        super("N'existe pas");
    }

    public DoesntExistException(String message){
        super(message);
    }
}
