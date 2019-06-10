package tables;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

/**
 * Classe responsável por estabelecer a conexão da classe tables com a interface de mesas.
 * @author Isaías de França Leite
 */
public class FXMLSystemTablesController implements Initializable {

    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
    * Tela de Help de mesas.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        alertHelp.setGraphic(new ImageView(("/images/information-help-tables.jpg")));
        alertHelp.showAndWait();
    }
    
}
