package fxml;

import classes.User;
import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Classe responsável por estabelecer a comunicação entre as outras interfaces gráficas do sistema.
 * @author Isaías de França Leite.
 */
public class FXMLSystemController implements Initializable {
    
    public FXMLSystemController(){}
    // Mensagem de Confirmação.
    Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    //
    private String nameCompany = "Butecu's Sta. Tereza";
    private String urlIconImage = "/images/logo_company.png";
    
// HELP
    String Information = "";
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);

    
    // Configurações
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    // Troca de telas.
    @FXML private BorderPane borderpane;
    SceneChange scene = new SceneChange();
    
    // Botões´para transição de telas.
    @FXML private Button btnCashier;
    @FXML private Button btnReservations;
    @FXML private Button btnTables;
    @FXML private Button btnCommands;
    @FXML private Button btnProducts;
    @FXML private Button btnGames;
    @FXML private Button btnReport;
    @FXML private Button btnUsers;
    
    // Botões de usuário.
    @FXML private ImageView btnLogout;
    
    // CSS dos botões do menu.
    private String styleButtonOn = "-fx-text-fill: #323538; -fx-background-color:  #F1F1F1;";
    private String styleButtonOff = ".buttonMenu;";
    
    // Váriaveis do usuário.
    private User user;
    @FXML private Text textUser;
    @FXML private Text textTypeOfUser;
    
    /**
     * Constructor da classe FXMLSystemController.
     * @param fxmlLoginHomeController
     * @param user
     */
    public FXMLSystemController(User user){
        this.user = user;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSceneCashier();
        
        textUser.setText(user.getName());
        if(user.getTypeOfPermission().equals("A")){
             textTypeOfUser.setText("ADMINISTRADOR");
        }else if(user.getTypeOfPermission().equals("c")){
             textTypeOfUser.setText("COLABORADOR");
        }else{
             textTypeOfUser.setText("TÉCNICO");
        }
    }
    
    @FXML private void loadSceneCashier(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemCashier.fxml");
        changeColorButtonOff();
        btnCashier.setStyle(styleButtonOn);
    }
    
    @FXML private void loadSceneReservations(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemReservations.fxml");
        changeColorButtonOff();
        btnReservations.setStyle(styleButtonOn);
    }
    
    @FXML private void loadSceneTables(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemTables.fxml");
        changeColorButtonOff();
        btnTables.setStyle(styleButtonOn);
    }
    
    @FXML public void loadSceneCommands(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemOrderPad.fxml");
        changeColorButtonOff();
        btnCommands.setStyle(styleButtonOn);
    }
    
    @FXML private void loadSceneProducts(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemProducts.fxml");
        changeColorButtonOff();
        btnProducts.setStyle(styleButtonOn);
    }
    
    @FXML private void loadSceneGames(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemGames.fxml");
        changeColorButtonOff();
        btnGames.setStyle(styleButtonOn);
    }
    
    @FXML private void loadSceneAnalyze(){
        scene.sceneTransition(borderpane, "/report/FXMLSystemReport.fxml");
        changeColorButtonOff();
        btnReport.setStyle(styleButtonOn);
    }
    
    @FXML private void loadSceneUsers(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemUsers.fxml");
        changeColorButtonOff();
        btnUsers.setStyle(styleButtonOn);
    }
    
    // Tela de Configurações de usuários
    @FXML private void loadSceneConfiguration() throws IOException{
        scene.sceneTransition(stackPane, anchorPane, "/fxml/FXMLSystemConfiguration.fxml");
    }
    
    // Tela de Help de usuários
    @FXML private void help(){
        alertHelp.setContentText(Information);
        alertHelp.showAndWait();
    }
    
    // Logout do sistema.
    @FXML private void logout() throws IOException{
        alertConfirmation.setTitle("Logout");
        alertConfirmation.setContentText("Tem certeza que deseja fazer logout?");
        Optional <ButtonType> actionLogout = alertConfirmation.showAndWait();
        if(actionLogout.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLLoginHome.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            Image applicationIcon = new Image(getClass().getResourceAsStream(urlIconImage)) {};
            primaryStage.getIcons().add(applicationIcon);
            primaryStage.setTitle(nameCompany);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.sizeToScene();
            primaryStage.show();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.close();
        }
    }
    
    // Função para mudar cor do botão do menu em off.
    private void changeColorButtonOff(){
        btnCashier.setStyle(styleButtonOff);
        btnReservations.setStyle(styleButtonOff);
        btnTables.setStyle(styleButtonOff);
        btnCommands.setStyle(styleButtonOff);
        btnProducts.setStyle(styleButtonOff);
        btnGames.setStyle(styleButtonOff);
        btnReport.setStyle(styleButtonOff);
        btnUsers.setStyle(styleButtonOff);
    }
 }  
