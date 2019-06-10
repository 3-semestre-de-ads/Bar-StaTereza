package report;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe responsável por gerar um relatório de vendas.
 * @author Isaías de França Leite.
 */
public class FXMLReportSalesChartController implements Initializable {
    
    // Classe de relatório.
    Report report = new Report();
    
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
    
        /**
     * Filtro de opções do tipo de valor da data que o gráfico pode retorna como valor.
     */
    private void typeDate(){
        listDate.add("MÊS");
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
            case "MÊS":
                System.out.println("MÊS");
                typeDate = "MONTH";
                break;
            default:
                System.out.println("ANO");
                typeDate = "YEAR";
                break;
        }
    };
    
    @FXML private void calculateChartPie() throws SQLException{
        if(typeDate == "YEAR"){
            pieChartData = FXCollections.observableArrayList(
                  new PieChart.Data("Janeiro", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-01-01")),
                  new PieChart.Data("Fevereiro", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-02-01")),
                  new PieChart.Data("Março", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-03-01")),
                  new PieChart.Data("Abril", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-04-01")),
                  new PieChart.Data("Maio", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-05-01")),
                  new PieChart.Data("Junho", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-06-01")),
                  new PieChart.Data("Julho", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-07-01")),
                  new PieChart.Data("Agosto", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-08-01")),
                  new PieChart.Data("Setembro", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-09-01")),
                  new PieChart.Data("Outubro", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-10-01")),
                  new PieChart.Data("Novembro", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-11-01")),
                  new PieChart.Data("Dezembro", report.totalSales(typeDate, Integer.toString(datePicker.getValue().getYear())+"-12-01")) 
            );
            pieChart.setData(pieChartData);
        }
    }
}
