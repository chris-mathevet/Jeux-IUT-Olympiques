public class NePeuxPasInscrireException extends Exception {
    
    public NePeuxPasInscrireException(String message){
        super(message);
    }

    public NePeuxPasInscrireException(){
        super("Ne peut pas s'inscrire");
    }
}
