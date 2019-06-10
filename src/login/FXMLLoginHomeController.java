package fxml;

import classes.Login;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * Classe responsável por estabelecer a conexão com a classe login e a interface login.
 * @author Isaías de França Leite
 */
public class FXMLLoginHomeController implements Initializable {
    
    //
    private String nameCompany = "Butecu's Sta. Tereza";
    private String urlIconImage = "/images/logo_company.png";
    
    //
    SceneChange sceneChange = new SceneChange();
    
    // 
    private Login login = new Login();
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    
    //
    @FXML private TextField username;
    @FXML private PasswordField password;
    
    //
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    @FXML private Button btnLogin;
    
    
    // 
    @FXML private void loadSceneUpdate() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLLoginUpdate.fxml");
    }
    
    //
    @FXML private void loadSceneHelp() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLLoginHelp.fxml");
    }
    
    //
    @FXML private void loadSceneAbout() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLLoginAbout.fxml");
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                login.user.setUsername(username.getText());
                login.user.setPassword(password.getText());
                if(login.verificationOfUser().equals("1")){
                    Stage primaryStage = new Stage();
                    Parent root = null;
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/FXMLSystem.fxml"));
                    fxmlloader.setController(new FXMLSystemController(login.user));
                    try {
                        root = fxmlloader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLLoginHomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    Image applicationIcon = new Image(getClass().getResourceAsStream(urlIconImage)) {};
                    primaryStage.getIcons().add(applicationIcon);
                    primaryStage.setTitle(nameCompany);
                    primaryStage.setScene(scene);
                    primaryStage.sizeToScene();
                    primaryStage.show();
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.close();
                }
                else{
                    alertInformation.setContentText("Usuário ou Senha Incorretos!");
                    alertInformation.showAndWait();
                }
            }
        });
    }
}