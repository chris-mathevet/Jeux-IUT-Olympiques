package MVC.tableClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import participants.Pays;

public class UserTableau{
    private SimpleStringProperty pseudo;
    private SimpleStringProperty mail;
    private SimpleStringProperty role;



    public UserTableau(String pseudo, String mail, String role){
        this.pseudo = new SimpleStringProperty(pseudo);
        this.mail = new SimpleStringProperty(mail);
        this.role = new SimpleStringProperty(role);
    }


    
    public SimpleStringProperty pseudoProperty() {
        return pseudo;
    }

    public void setPseudo(SimpleStringProperty pseudo) {
        this.pseudo = pseudo;
    }

    public SimpleStringProperty mailProperty() {
        return mail;
    }

    public void setMail(SimpleStringProperty mail) {
        this.mail = mail;
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public void setRole(SimpleStringProperty role) {
        this.role = role;
    }

 
}
