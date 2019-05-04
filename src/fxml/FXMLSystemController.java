package fxml;

import functions.SceneChange;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * Classe responsável por estabelecer a comunicação entre as outras interfaces gráficas do sistema.
 * @author Isaías de França Leite.
 */
public class FXMLSystemController implements Initializable {

    @FXML
    private BorderPane borderpane;
    
    SceneChange scene = new SceneChange();
    
    @FXML
    private void loadSceneCashier(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemCashier.fxml");
    }
    
    @FXML
    private void loadSceneReservations(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemReservations.fxml");
    }
    
    @FXML
    private void loadSceneTables(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemTables.fxml");
    }
    
    @FXML
    private void loadSceneCommands(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemCommands.fxml");
    }
    
    @FXML
    private void loadSceneProducts(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemProducts.fxml");
    }
    
    @FXML
    private void loadSceneGames(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemGames.fxml");
    }
    
    @FXML
    private void loadSceneAnalyze(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemAnalyze.fxml");
    }
    
    @FXML
    private void loadSceneUsers(){
        scene.sceneTransition(borderpane, "/fxml/FXMLSystemUser.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  

}
