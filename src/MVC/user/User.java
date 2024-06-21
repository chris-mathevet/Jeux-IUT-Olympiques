package MVC.user;

public class User{

    private String pseudo;
    private String mail;
    private String role;


    public User( String pseudo ,String mail ,String role){

        this.pseudo = pseudo;
        this.mail = mail;
        this.role = role;
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}