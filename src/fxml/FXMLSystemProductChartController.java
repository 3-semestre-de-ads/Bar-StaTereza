/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import report.Report;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
/**
 * FXML Controller class
 *
 * @author isaia
 */
public class FXMLSystemProductChartController implements Initializable {

    // 
    
        // Filtro
    @FXML private ComboBox comboBoxFilter;
    private List<String> listFilter = new ArrayList<>();
    ObservableList<String> observableListFilter = FXCollections.observableArrayList();
    public String value;
    Report report = new Report();
    ObservableList<PieChart.Data> pieChartData;
            
    private String typeDate;
    
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
        @FXML private BarChart barChart;

    @FXML private DatePicker datePicker;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setValue(LocalDate.now());
        filter();
  
    }
    

    @FXML public void calculateChart() throws SQLException{
        // Gráfico de Barra
        barChart.getData().clear();
        XYChart.Series set1 = new XYChart.Series();
        ResultSet rs = null;
                
        rs = report.totalSalesOfProducts("MONTH", datePicker.getValue().toString());
  

        while(rs.next()){
        set1.getData().add(new XYChart.Data(rs.getString("Produto"), rs.getDouble("TOTAL")));
        }
        
        barChart.getData().addAll(set1);
        
    }
        public void filter(){
        listFilter.add("DIA");
        listFilter.add("MÊS");
        listFilter.add("ANO");
        observableListFilter = FXCollections.observableArrayList(listFilter); 
        comboBoxFilter.setItems(observableListFilter);
        comboBoxFilter.getSelectionModel().selectFirst();
        comboBoxFilter.setOnAction(eventFilter);
    }
        
            // Seleção de evento.
    public EventHandler<ActionEvent> eventFilter = 
             (ActionEvent e) -> {
        if(comboBoxFilter.getValue().toString().equals("DIA")){
        }else{
            
        }
    };
}
