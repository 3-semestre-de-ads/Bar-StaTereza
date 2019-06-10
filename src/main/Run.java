package main;

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
    
    // Nome da empresa do cliente.
    private String nameCompany = "Butecu's Sta. Tereza";
    // Icone da empresa do cliente.
    private String urlIconImage = "/images/logo_company.png";
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/FXMLLoginHome.fxml"));
        Scene scene = new Scene(root);
        Image applicationIcon = new Image(getClass().getResourceAsStream(urlIconImage)) {};
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setTitle(nameCompany);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    
    /**
     * Execultar o sistema.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
