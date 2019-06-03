package fxml;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import repositories.RepositoryOfReports;

/**
 * Classe responsável por estabelecer a conexão entre a classe RepositoryOfAnalyze e a interface de gráfica de analyze.
 * @author Isaías de França Leite
 */
public class FXMLSystemReportController implements Initializable {
    
    // 
    RepositoryOfReports repositoryOfReports = new RepositoryOfReports();
    ObservableList<PieChart.Data> pieChartData;
            
    private String typeDate;
    @FXML private BarChart<?,?> barChart;
    @FXML private PieChart pieChart;
    @FXML private DatePicker datePicker;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setValue(LocalDate.now());
    }
    

    @FXML public void calculateChart(){
        typeDate = "MONTH";
        String year = Integer.toString(datePicker.getValue().getYear());
        
        // Gráfico de Pizza.
        pieChartData = FXCollections.observableArrayList(
              new PieChart.Data("Janeiro", repositoryOfReports.totalSalesValue(typeDate, year+"-01-01")),
              new PieChart.Data("Fevereiro", repositoryOfReports.totalSalesValue(typeDate, year+"-02-01")),
              new PieChart.Data("Março", repositoryOfReports.totalSalesValue(typeDate, year+"-03-01")),
              new PieChart.Data("Abril", repositoryOfReports.totalSalesValue(typeDate, year+"-04-01")),
              new PieChart.Data("Maio", repositoryOfReports.totalSalesValue(typeDate, year+"-05-01")),
              new PieChart.Data("Junho", repositoryOfReports.totalSalesValue(typeDate, year+"-06-01")),
              new PieChart.Data("Julho", repositoryOfReports.totalSalesValue(typeDate, year+"-07-01")),
              new PieChart.Data("Agosto", repositoryOfReports.totalSalesValue(typeDate, year+"-08-01")),
              new PieChart.Data("Setembro", repositoryOfReports.totalSalesValue(typeDate, year+"-09-01")),
              new PieChart.Data("Outubro", repositoryOfReports.totalSalesValue(typeDate, year+"-10-01")),
              new PieChart.Data("Novembro", repositoryOfReports.totalSalesValue(typeDate, year+"-11-01")),
              new PieChart.Data("Dezembro", repositoryOfReports.totalSalesValue(typeDate, year+"-12-01"))  
        );
        
        
        
        
        pieChart.setData(pieChartData);
        
        
        // Gráfico de Barra
        barChart.getData().clear();
        XYChart.Series set1 = new XYChart.Series();
        XYChart.Series set2 = new XYChart.Series();
        set1.getData().add(new XYChart.Data("Janeiro", repositoryOfReports.totalSalesValue(typeDate, year+"-01-01")));
        set2.getData().add(new XYChart.Data("Fevereiro", repositoryOfReports.totalSalesValue(typeDate, year+"-01-01")));
        barChart.getData().addAll(set1,set2);
    }
}
