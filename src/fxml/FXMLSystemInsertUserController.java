/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import classes.User;
import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author isaia
 */
public class FXMLSystemInsertUserController implements Initializable {

    SceneChange sceneChange = new SceneChange();
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane stackPane;
 
    private User user;
    private String typeFunction;

    public FXMLSystemInsertUserController(User user, String typeFunction) {
        this.user = user;
        this.typeFunction = typeFunction;
    }
    

    @FXML
    private void loadSceneUsers() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLSystemUsers.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
