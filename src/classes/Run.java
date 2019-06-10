package classes;

import javafx.scene.image.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe responsável por executar a aplicação.
 * @author Isaías de França Leite
 */
public class Run extends Application {
    
    
    private String nameCompany = "Butecu's Sta. Tereza";
    private String urlIconImage = "/images/logo_company.png";
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLLoginHome.fxml"));
        
        Scene scene = new Scene(root);
      
       //set icon of the application
        Image applicationIcon = new Image(getClass().getResourceAsStream(urlIconImage)) {};
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setTitle(nameCompany);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
