/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import classes.Product;
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
public class FXMLSystemInsertProductController implements Initializable {
    
    SceneChange sceneChange = new SceneChange();
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane stackPane;
 
    @FXML
    private void loadSceneProducts() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLSystemProducts.fxml");
    }
    
    // Variáveis do constructor.
    private Product product;
    private String typeFunction;

    /**
     * O contructor da classe FXMLSystemInsertProductController.
     * @param product
     * @param typeFunction
     */
    public FXMLSystemInsertProductController(Product product, String typeFunction) {
        this.product = product;
        this.typeFunction = typeFunction;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }     
}
