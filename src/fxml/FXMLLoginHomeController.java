package fxml;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe responsável por estabelecer a conexão com a classe login e a interface login.
 * @author Isaías de França Leite
 */
public class FXMLLoginHomeController implements Initializable {
    
    SceneChange sceneChange = new SceneChange();
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane stackPane;
 
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

    }    
    
}
