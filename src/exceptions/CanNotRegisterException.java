package exceptions;

public class CanNotRegisterException extends Exception {
    
    public CanNotRegisterException(String message){
        super(message);
    }

    public CanNotRegisterException(){
        super("Ne peut pas s'inscrire");
    }
}
