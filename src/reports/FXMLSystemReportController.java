package reports;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Classe responsável mostra os dados de status do bar.
 * @author Isaías de França Leite.
 */
public class FXMLSystemReportController implements Initializable {
    
    // Classe de relatório.
    Report report = new Report();
    
    // Text - Status
    @FXML private Text text01;
    @FXML private Text text02;
    @FXML private Text text03;
    @FXML private Text text04;
    @FXML private Text text05;
    @FXML private Text text06;
    @FXML private Text text07;
    @FXML private Text text08;
    @FXML private Text text09;
    @FXML private Text text10;
    @FXML private Text text11;
    @FXML private Text text12;
    @FXML private Text text13;
    @FXML private Text text14;
    
   // Data do pdf
    @FXML private DatePicker datePicker;
    
    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorRoot;
    @FXML private StackPane parentContainer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Data Atual
        datePicker.setValue(LocalDate.now());
        // Relatório 
        reportCount();
    }
    
    /**
     * Transição de tela: Tela de status.
     */
    @FXML private void loadSceneReport() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/reports/FXMLSystemReport.fxml");
    }
    
    /**
     * Transição de tela: Tela de relatório de vendas.
     */
    @FXML private void loadSceneReportSalesChart() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/reports/FXMLReportSalesChart.fxml");
    }
    
    /**
     * Transição de tela: Tela de relatório de produtos.
     */
    @FXML private void loadSceneReportProductsChart() throws IOException {
        sceneChange.sceneTransition(parentContainer, anchorRoot, "/reports/FXMLReportProductChart.fxml");
    }
    
    /**
    * Tela de Help de relatório.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        alertHelp.setGraphic(new ImageView(("/images/information-help-report.jpg")));
        alertHelp.showAndWait();
    }
    /**
     * Status do sistema geral.
     */
    private void reportCount(){
        text01.setText(text01.getText()+report.reportCountSQL(1));
        text02.setText(text02.getText()+report.reportCountSQL(2));
        text03.setText(text03.getText()+report.reportCountSQL(3));
        text04.setText(text04.getText()+report.reportCountSQL(4));
        text05.setText(text05.getText()+report.reportCountSQL(5));
        text06.setText(text06.getText()+report.reportCountSQL(6));
        text07.setText(text07.getText()+report.reportCountSQL(7));
        text08.setText(text08.getText()+report.reportCountSQL(8));
        text09.setText(text09.getText()+report.reportCountSQL(9));
        text10.setText(text10.getText()+report.reportCountSQL(10));
        text11.setText(text11.getText()+report.reportCountSQL(11));
        text12.setText(text12.getText()+report.reportCountSQL(12));
        text13.setText(text13.getText()+report.reportCountSQL(13));
        text14.setText(text14.getText()+report.totalSales("DAY", "2019-05-25"));
    }
}
  