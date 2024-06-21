package MVC.tableClass;

import MVC.vues.Roles;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import participants.Pays;

public class UserTableau{
    private SimpleStringProperty pseudo;
    private SimpleStringProperty mail;
    private SimpleStringProperty role;
    private ComboBox<String> boxRole;




    public UserTableau(String pseudo, String mail, String role){
        this.pseudo = new SimpleStringProperty(pseudo);
        this.mail = new SimpleStringProperty(mail);
        this.role = new SimpleStringProperty(role);
        this.boxRole = new ComboBox<>();
        this.boxRole.getItems().addAll(Roles.VISITEUR.getRoleStr(),Roles.ORGANISATEUR.getRoleStr(),Roles.ADMIN.getRoleStr());
        this.boxRole.setValue(role);
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

    
    public ComboBox<String> boxRoleProperty() {
        return boxRole;
    }

    public void setBoxRole(ComboBox<String> boxRole) {
        this.boxRole = boxRole;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ""+this.pseudo + this.mail + this.role + this.boxRole.getValue();
    }
}
