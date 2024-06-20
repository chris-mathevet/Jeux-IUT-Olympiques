package MVC.vues;

public enum Roles {
    VISITEUR ("visiteur"),ORGANISATEUR ("organisateur"),ADMIN ("admin");

    private final String nomRole;

    private Roles(String role){
        this.nomRole = role;
    }

    public String getRoleStr(){return this.nomRole;}

    public static Roles getRole(String role){
        switch (role) {
            case "organisateur":
                return ORGANISATEUR;

            case "admin":
                return ADMIN;
                
            default:
                return VISITEUR;
        }
    }
}
