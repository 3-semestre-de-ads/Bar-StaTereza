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
 * Classe responsável por estabelecer a conexão com classe RepositoryOfUsers com a interface de CRUD de usuário.
 * @author Isaías de França Leite
 */
public class FXMLSystemUsersController implements Initializable {

    SceneChange sceneChange = new SceneChange();
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane stackPane;
 
    @FXML
    private void loadSceneInsertUser() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLSystemInsertUser.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
