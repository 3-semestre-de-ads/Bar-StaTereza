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
 * Classe responsável por estabelecer a conexão com a interface Login-Help.
 * @author Isaías de França Leite
 */
public class FXMLLoginHelpController implements Initializable {
    
    SceneChange sceneChange = new SceneChange();
    
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;
    
    @FXML
    private void loadSceneHome() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/fxml/FXMLLoginHome.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
}
