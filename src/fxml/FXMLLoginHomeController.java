package fxml;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Classe responsável por estabelecer a conexão com a classe login e a interface login.
 * @author Isaías de França Leite
 */
public class FXMLLoginHomeController implements Initializable {
    
    SceneChange sceneChange = new SceneChange();
    
    

    @FXML private TextField username;
    @FXML private PasswordField password;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private Button btnLogin;
    
    @FXML
    private void loadSceneUpdate() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLLoginUpdate.fxml");
    }
    @FXML
    private void loadSceneHelp() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLLoginHelp.fxml");
    }
    @FXML
    private void loadSceneAbout() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLLoginAbout.fxml");
    }
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(username.getText().equals("1") && password.getText().equals("2")){
                    Stage primaryStage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/fxml/FXMLSystem.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLLoginHomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.sizeToScene();
                    primaryStage.show();

                    btnLogin.getScene().getWindow().hide();
                }
                else
                {
                    System.out.println(username.getText());
                }
            }
        });
    }    
}
