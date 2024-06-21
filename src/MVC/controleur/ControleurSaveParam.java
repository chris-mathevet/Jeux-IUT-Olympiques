package MVC.controleur;


import MVC.vues.AppliJO;
import MVC.vues.Roles;

import java.sql.SQLException;
import java.util.List;

import MVC.modele.ModeleConnexion;
import MVC.modele.ModeleJO;
import MVC.tableClass.UserTableau;
import MVC.user.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ControleurSaveParam implements EventHandler<ActionEvent>{
    
    private AppliJO vue;
    private ModeleJO modele;

    public ControleurSaveParam(AppliJO vue, ModeleJO modele) {
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("save");
        // for (UserTableau truc : this.vue.getTableUser().getItems()) {
            
        //     System.out.println(truc);
        // }
        for (UserTableau user : this.vue.getTableUser().getItems()) {
            if (!((("" + user.roleProperty()).equals(user.boxRoleProperty().getValue())))) {
                
                try {
                    this.modele.getRequete().updateUser(user.pseudoProperty().getValue(), user.boxRoleProperty().getValue());

                } catch ( SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               
            }

        }
        this.modele.reload();
        this.vue.majAffichage();
        // try {
        //     this.vue.modeAccueil();    
        // } catch (Exception e) {
        //     System.out.println("Problème déconnexion");
        // }
    }
}
