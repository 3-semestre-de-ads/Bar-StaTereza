package report;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe responsável mostra os dados de status do bar.
 * @author Isaías de França Leite.
 */
public class FXMLSystemReportController implements Initializable {
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorRoot;
    @FXML private StackPane parentContainer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    /**
     * Transição de tela: Tela de status.
     */
    @FXML private void loadSceneReport() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/report/FXMLSystemReport.fxml");
    }
    
    /**
     * Transição de tela: Tela de relatório de vendas.
     */
    @FXML private void loadSceneReportSalesChart() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/report/FXMLReportSalesChart.fxml");
    }
    
    /**
     * Transição de tela: Tela de relatório de produtos.
     */
    @FXML private void loadSceneReportProductsChart() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/report/FXMLReportProductChart.fxml");
    }
}
  