package main;

import users.User;
import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
    
    // Mensagem de Confirmação.
    Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    
    // Nome da empresa do cliente.
    private String nameCompany = "Butecu's Sta. Tereza";
    
    // Icone da empresa do cliente.
    private String urlIconImage = "/images/logo_company.png";
    
    // HELP
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
        }else if(user.getTypeOfPermission().equals("B")){
            textTypeOfUser.setText("COLABORADOR");
            btnReport.setDisable(true);
            btnUsers.setDisable(true);
        }else{
            textTypeOfUser.setText("TÉCNICO");
        }
    }
    
    /**
    * Transição de tela: Tela de FXMLSystemCashier.
    * */
    @FXML private void loadSceneCashier(){
        scene.sceneTransition(borderpane, "/cashier/FXMLSystemCashier.fxml");
        changeColorButtonOff();
        btnCashier.setStyle(styleButtonOn);
    }
    
    /**
    * Transição de tela: Tela de FXMLSystemReservations.
    * */
    @FXML private void loadSceneReservations(){
        scene.sceneTransition(borderpane, "/reservations/FXMLSystemReservations.fxml");
        changeColorButtonOff();
        btnReservations.setStyle(styleButtonOn);
    }
    
    /**
    * Transição de tela: Tela de FXMLSystemTables.
    * */
    @FXML private void loadSceneTables(){
        scene.sceneTransition(borderpane, "/tables/FXMLSystemTables.fxml");
        changeColorButtonOff();
        btnTables.setStyle(styleButtonOn);
    }
    
    /**
    * Transição de tela: Tela de FXMLSystemOrderPad.
    * */
    @FXML public void loadSceneCommands(){
        scene.sceneTransition(borderpane, "/orderpads/FXMLSystemOrderPad.fxml");
        changeColorButtonOff();
        btnCommands.setStyle(styleButtonOn);
    }
    
    /**
    * Transição de tela: Tela de FXMLSystemProducts.
    * */
    @FXML private void loadSceneProducts(){
        scene.sceneTransition(borderpane, "/products/FXMLSystemProducts.fxml");
        changeColorButtonOff();
        btnProducts.setStyle(styleButtonOn);
    }
    
    /**
    * Transição de tela: Tela de FXMLSystemGames.
    * */
    @FXML private void loadSceneGames(){
        scene.sceneTransition(borderpane, "/games/FXMLSystemGames.fxml");
        changeColorButtonOff();
        btnGames.setStyle(styleButtonOn);
    }
    
    /**
    * Transição de tela: Tela de FXMLSystemReport.
    * */
    @FXML private void loadSceneReport(){
        scene.sceneTransition(borderpane, "/reports/FXMLSystemReport.fxml");
        changeColorButtonOff();
        btnReport.setStyle(styleButtonOn);
    }
 
    /**
    * Transição de tela: Tela de FXMLSystemUsers.
    * */
    @FXML private void loadSceneUsers(){
        scene.sceneTransition(borderpane, "/users/FXMLSystemUsers.fxml");
        changeColorButtonOff();
        btnUsers.setStyle(styleButtonOn);
    }
    
    /**
    * Tela de Configurações de usuários.
    * */
    @FXML private void loadSceneConfiguration() throws IOException{
        scene.sceneTransition(stackPane, anchorPane, "/main/FXMLSystemConfiguration.fxml");
    }
    
    /**
    * Tela de Help do usuário.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        alertHelp.setGraphic(new ImageView(("/images/information-help-user.jpg")));
        alertHelp.showAndWait();
    }
    
    /**
    * Tela de sobre o desenvolvedor do sistema.
    */
    @FXML private void about(){
        alertHelp.setTitle("DEVIL TRIGGER");
        alertHelp.setHeaderText(" ");
        alertHelp.setGraphic(new ImageView(("/images/information-help-deviltrigger.jpg")));
        alertHelp.showAndWait();
    }
    
    /**
     * Logout do sistema.
     * @throws IOException 
     */
    @FXML private void logout() throws IOException{
        alertConfirmation.setTitle("Logout");
        alertConfirmation.setContentText("Tem certeza que deseja fazer logout?");
        Optional <ButtonType> actionLogout = alertConfirmation.showAndWait();
        if(actionLogout.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("/login/FXMLLoginHome.fxml"));
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
    
    /**
     * Função para mudar cor do botão do menu em off.
     */
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
