package report;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe responsável por gerar um relatório de produtos.
 * @author Isaías de França Leite.
 */
public class FXMLReportProductChartController implements Initializable {

    // Classe de relatório.
    Report report = new Report();
    
    // Gráfico de Barra.
    @FXML private BarChart barChart;
   
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
        listDate.add("DIA");
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
            case "DIA":
                System.out.println("DIA");
                typeDate = "DAY";
                break;
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
    
    /**
     * Gerar Gráfico de Barra.
     */  
    @FXML private void calculateChartBar() throws SQLException{
        barChart.getData().clear();
        XYChart.Series set1 = new XYChart.Series();
        ResultSet rs = null;   
        String value = null;
        rs = report.totalSalesOfProducts(typeDate, datePicker.getValue().toString());
        while(rs.next()){
            DecimalFormat df = new DecimalFormat("0.00");
            value = df.format(rs.getDouble("TOTAL"));
            set1.getData().add(new XYChart.Data(rs.getString("Produto")+" (R$: "+value+")", rs.getDouble("TOTAL")));
        }
        barChart.getData().addAll(set1);
    }
}
