package login;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe responsável por estabelecer a verificação e comunicação do usuário com o sistema.
 * @author Isaías de França Leite.
 */
public class FXMLLoginAboutController implements Initializable {

    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorRoot;
    @FXML private StackPane parentContainer;
 
    /**
     * Transição de tela: Tela de FXMLLoginHome.
    */
    @FXML private void loadSceneHome() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/login/FXMLLoginHome.fxml");
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 
}
