package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * Classe responsável por estabelecer a conexão entre a classe RepositoryOfAnalyze e a interface de gráfica de analyze.
 * @author Isaías de França Leite
 */
public class FXMLSystemReportController implements Initializable {
    
    @FXML private PieChart pieChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData;
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Isaías",10),
                new PieChart.Data("Weber",40),
                new PieChart.Data("Segio",20),
                new PieChart.Data("Jannifer",15),
                new PieChart.Data("Ricardo",15));
        pieChart.setData(pieChartData);
    }    
}
