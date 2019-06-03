package fxml;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class FXMLLoginHomeController implements Initializable {
    
    SceneChange sceneChange = new SceneChange();
    
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;
 
    @FXML
    private void loadSceneUpdate() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/fxml/FXMLLoginUpdate.fxml");
    }
    @FXML
    private void loadSceneHelp() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/fxml/FXMLLoginHelp.fxml");
    }
    @FXML
    private void loadSceneAbout() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/fxml/FXMLLoginAbout.fxml");
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
