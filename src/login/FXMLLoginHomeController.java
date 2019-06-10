package login;

import functions.SceneChange;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import main.FXMLSystemController;
/**
 * Classe responsável por estabelecer a conexão com a classe login e a interface login.
 * @author Isaías de França Leite.
 */
public class FXMLLoginHomeController implements Initializable {
    
    // Nome da empresa do cliente.
    private String nameCompany = "Butecu's Sta. Tereza";
    
    // Icone da empresa do cliente.
    private String urlIconImage = "/images/logo_company.png";
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
   
    // Classe login.
    private Login login = new Login();
    
    // Status de login.
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    
    // Variáveis necessária para login.
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button btnLogin;
    
    
    /**
     * Transição de tela: Tela de FXMLLoginUpdate.
    */
    @FXML private void loadSceneUpdate() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/login/FXMLLoginUpdate.fxml");
    }
    
    /**
     * Transição de tela: Tela de FXMLLoginHelp.
    */
    @FXML private void loadSceneHelp() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/login/FXMLLoginHelp.fxml");
    }
    
    /**
     * Transição de tela: Tela de FXMLLoginAbout.
    */
    @FXML private void loadSceneAbout() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/login/FXMLLoginAbout.fxml");
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MessageDigest md;
                String usernameLogin = password.getText();
                try {
                    md = MessageDigest.getInstance("SHA-256");
                    byte messageDigest[] = md.digest(usernameLogin.getBytes("UTF-8"));
                    StringBuilder sb = new StringBuilder();
                    for(byte b: messageDigest){
                        sb.append((String.format("%02X", 0xFF & b)));
                    }
                    String senhaHex = sb.toString();
                    login.user.setUsername(username.getText());
                    login.user.setPassword(senhaHex);
                    System.out.println(senhaHex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FXMLLoginHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(FXMLLoginHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(login.verificationOfUser().equals("1")){
                    Stage primaryStage = new Stage();
                    Parent root = null;
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/main/FXMLSystem.fxml"));
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