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
 * Classe responsável por estabelecer a conexão com classe repositoryOfProducts com a interface de produtos.
 * @author Isaías de França Leite
 */
public class FXMLSystemProductsController implements Initializable {
    
    SceneChange sceneChange = new SceneChange();
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane stackPane;
 
    @FXML
    private void loadSceneInsertProduct() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLSystemInsertProduct.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
