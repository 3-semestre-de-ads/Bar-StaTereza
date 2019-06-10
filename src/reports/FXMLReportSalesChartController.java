package reports;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe responsável por gerar um relatório de vendas.
 * @author Isaías de França Leite.
 */
public class FXMLReportSalesChartController implements Initializable {
    
    // Classe de relatório.
    Report report = new Report();
    
    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    
    // Gráfico de Pizza.
    @FXML private PieChart pieChart;
    ObservableList<PieChart.Data> pieChartData;
   
    // Data do Gráfico
    @FXML private DatePicker datePicker;
    
    // Filtro do tipo de valor da data.
    @FXML private ComboBox comboBoxDate;
    private List<String> listDate = new ArrayList<>();
    ObservableList<String> observableListDate = FXCollections.observableArrayList();
    
    // Valor do tipo de valor de data.
    private String typeDate = "DAY";
    
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
        // Filtro de opções.
        typeDate();
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
    * Tela de Help de usuários.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        alertHelp.setGraphic(new ImageView(("/images/information-help-salesChart.jpg")));
        alertHelp.showAndWait();
    }
    
    /**
     * Filtro de opções do tipo de valor da data que o gráfico pode retorna como valor.
     */
    private void typeDate(){
        listDate.add("ANO");
        observableListDate = FXCollections.observableArrayList(listDate); 
        comboBoxDate.setItems(observableListDate);
        comboBoxDate.getSelectionModel().selectFirst();
        comboBoxDate.setOnAction(eventDate);
    }
    
    /**
     * Seleção de opções do tipo de valor da data.
     */   
    public EventHandler<ActionEvent> eventDate =
        (ActionEvent e) -> {
        switch (comboBoxDate.getValue().toString()) {
            case "ANO":
                typeDate = "MONTH";
                break;
            default:
                typeDate = "MONTH";
                break;
        }
    };
    
    /**
     * Função responsável por gerar gráfico de pizza.
     * @throws SQLException 
     */
    @FXML private void calculateChartPie() throws SQLException{
        String year = Integer.toString(datePicker.getValue().getYear());
            pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Janeiro (R$: "+report.totalSales("MONTH", year+"-01-01")+")", report.totalSales("MONTH", year+"-01-01")),
                new PieChart.Data("Fevereiro (R$: "+report.totalSales("MONTH", year+"-02-01")+")", report.totalSales("MONTH", year+"-02-01")),
                new PieChart.Data("Março (R$: "+report.totalSales("MONTH", year+"-03-01")+")", report.totalSales("MONTH", year+"-03-01")),
                new PieChart.Data("Abril (R$: "+report.totalSales("MONTH", year+"-04-01")+")", report.totalSales("MONTH", year+"-04-01")),
                new PieChart.Data("Maio (R$: "+report.totalSales("MONTH", year+"-05-01")+")", report.totalSales("MONTH", year+"-05-01")),
                new PieChart.Data("Junho (R$: "+report.totalSales("MONTH", year+"-06-01")+")", report.totalSales("MONTH", year+"-06-01")),
                new PieChart.Data("Julho (R$: "+report.totalSales("MONTH", year+"-07-01")+")", report.totalSales("MONTH", year+"-07-01")),
                new PieChart.Data("Agosto (R$: "+report.totalSales("MONTH", year+"-08-01")+")", report.totalSales("MONTH", year+"-08-01")),
                new PieChart.Data("Setembro (R$: "+report.totalSales("MONTH", year+"-09-01")+")", report.totalSales("MONTH", year+"-09-01")),
                new PieChart.Data("Outubro (R$: "+report.totalSales("MONTH", year+"-10-01")+")", report.totalSales("MONTH", year+"-10-01")),
                new PieChart.Data("Novembro (R$: "+report.totalSales("MONTH", year+"-11-01")+")", report.totalSales("MONTH", year+"-11-01")),
                new PieChart.Data("Dezembro (R$: "+report.totalSales("MONTH", year+"-12-01")+")", report.totalSales("MONTH", year+"-12-01"))
            );
            pieChart.setData(pieChartData);
        
    }
}
