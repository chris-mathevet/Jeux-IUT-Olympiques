package MVC.vues;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ApplicationJO extends Application{

    @Override
    public void init() throws Exception {
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("FXML/PageConnexion.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        StackPane racine = loader.load();
        Scene scene = new Scene(racine);
        stage.setTitle("Puissance 4");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }   
}