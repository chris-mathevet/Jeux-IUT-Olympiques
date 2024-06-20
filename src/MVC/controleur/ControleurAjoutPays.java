package MVC.controleur;

import MVC.modele.ModeleJO;
import MVC.modele.ModeleJO.Tris;
import MVC.vues.AppliJO;
import exceptions.AlreadyExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControleurAjoutPays implements EventHandler<ActionEvent>{
    private AppliJO vue;
    private TextField nomPays;
    private ModeleJO modele;
    private ComboBox<String> combo;
    private VBox boxErreur;
    private HBox ereurExiste;

    public ControleurAjoutPays(AppliJO vue, ModeleJO modele, TextField nomPays, ComboBox<String> combo, VBox boxErreur){
        this.vue = vue;
        this.modele = modele;
        this.nomPays = nomPays;
        this.combo = combo;
        this.boxErreur = boxErreur;
        this.ereurExiste = new HBox();

        this.ereurExiste.setSpacing(4);
        this.ereurExiste.setAlignment(Pos.CENTER);
        
        Text erreurExitText = new Text("Ce pays existe déjà");
        ImageView imageErreur = new ImageView("erreur.png");

        // erreurExitText.setWrappingWidth(boxErreur.getMaxWidth() - imageErreur.getFitWidth() - boxErreur.getSpacing());        
        erreurExitText.setFill(Color.web("#EB5252"));        
        erreurExitText.setFont(new Font("System", 13));   
        imageErreur.setPreserveRatio(true);
        imageErreur.setFitHeight(13);

        this.ereurExiste.getChildren().addAll(imageErreur, erreurExitText);
    }

    @Override
    public void handle(ActionEvent arg0) {
        this.boxErreur.getChildren().remove(this.ereurExiste);
        String nom = this.nomPays.getText();
        String stringTri = this.combo.getValue();
        Tris tri;
        switch (stringTri) {
            case "Alphabétique":
                tri = Tris.NATUREL;
                break;
            
            case "Total":
                tri = Tris.TOTAL;
                break;
        
            default:
                tri = Tris.MEDAILLES;
                break;
        }
        try {
            this.modele.creerPays(nom);
            this.vue.updateClassement(tri);
        } catch (AlreadyExistException e) {
            this.boxErreur.getChildren().add(this.ereurExiste);
        }
        ((Button) arg0.getSource()).setDisable(true);
    }
}
