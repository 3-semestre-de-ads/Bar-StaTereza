package fxml;

import functions.SceneChange;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Classe responsável por estabelecer a comunicação entre as outras interfaces gráficas do sistema.
 * @author Isaías de França Leite.
 */
public class FXMLSystemController implements Initializable {
    
    @FXML
    private BorderPane borderpane;
    
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
    
    private String styleButtonOn = "-fx-text-fill: #323538; -fx-background-color:  #F1F1F1;";
    private String styleButtonOff = ".buttonMenu;";
    
    @FXML
    private void loadSceneCashier(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemCashier.fxml");
        changeColorButtonOff();
        btnCashier.setStyle(styleButtonOn);
    
    }
    
    @FXML
    private void loadSceneReservations(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemReservations.fxml");
        changeColorButtonOff();
        btnReservations.setStyle(styleButtonOn);
    }
    
    @FXML
    private void loadSceneTables(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemTables.fxml");
        changeColorButtonOff();
        btnTables.setStyle(styleButtonOn);
    }
    
    @FXML
    private void loadSceneCommands(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemOrderPad.fxml");
        changeColorButtonOff();
        btnCommands.setStyle(styleButtonOn);
    }
    
    @FXML
    private void loadSceneProducts(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemProducts.fxml");
        changeColorButtonOff();
        btnProducts.setStyle(styleButtonOn);
    }
    
    @FXML
    private void loadSceneGames(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemGames.fxml");
        changeColorButtonOff();
        btnGames.setStyle(styleButtonOn);
    }
    
    @FXML
    private void loadSceneAnalyze(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemReport.fxml");
        changeColorButtonOff();
        btnReport.setStyle(styleButtonOn);
    }
    
    @FXML
    private void loadSceneUsers(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemUsers.fxml");
        changeColorButtonOff();
        btnUsers.setStyle(styleButtonOn);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSceneCashier();
    }
    
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
