package backup;

import report.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * Classe responsável mostra os dados de status do bar.
 * @author Isaías de França Leite.
 */
public class FXMLSystemReportController implements Initializable {
    
    // 
//    Report report = new Report();
//    ObservableList<PieChart.Data> pieChartData;
//            
//    private String typeDate;
//
//    
//    @FXML private CategoryAxis x;
//    @FXML private NumberAxis y;
//        @FXML private BarChart<?,?> barChart;
//    @FXML private PieChart pieChart;
//    @FXML private DatePicker datePicker;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        datePicker.setValue(LocalDate.now());
    }
    

    public void calculateChart() throws SQLException{
//        typeDate = "MONTH";
//        String year = Integer.toString(datePicker.getValue().getYear());
//        
//        // Gráfico de Pizza.
//        pieChartData = FXCollections.observableArrayList(
//              new PieChart.Data("Janeiro", report.totalSalesValue(typeDate, year+"-01-01")),
//              new PieChart.Data("Fevereiro", report.totalSalesValue(typeDate, year+"-02-01")),
//              new PieChart.Data("Março", report.totalSalesValue(typeDate, year+"-03-01")),
//              new PieChart.Data("Abril", report.totalSalesValue(typeDate, year+"-04-01")),
//              new PieChart.Data("Maio", report.totalSalesValue(typeDate, year+"-05-01")),
//              new PieChart.Data("Junho", report.totalSalesValue(typeDate, year+"-06-01")),
//              new PieChart.Data("Julho", report.totalSalesValue(typeDate, year+"-07-01")),
//              new PieChart.Data("Agosto", report.totalSalesValue(typeDate, year+"-08-01")),
//              new PieChart.Data("Setembro", report.totalSalesValue(typeDate, year+"-09-01")),
//              new PieChart.Data("Outubro", report.totalSalesValue(typeDate, year+"-10-01")),
//              new PieChart.Data("Novembro", report.totalSalesValue(typeDate, year+"-11-01")),
//              new PieChart.Data("Dezembro", report.totalSalesValue(typeDate, year+"-12-01"))  
//        );
    }
}
