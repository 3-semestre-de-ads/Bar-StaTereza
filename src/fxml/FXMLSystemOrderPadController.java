package fxml;

import classes.OrderPad;
import classes.Reservation;
import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import repositories.RepositoryOfOrderPad;

/**
 * Classe responsável por estabelecer a conexão entre a classe Commands e a interface de gráfica de commands.
 * @author Isaías de França Leite
 */
public class FXMLSystemOrderPadController implements Initializable {

    //
    RepositoryOfOrderPad repositoryOfOrderPad = new RepositoryOfOrderPad();
    // TableView para visualizar todos reservas.
    @FXML
    private TableView<OrderPad> tableOrderPad;
    @FXML private TableColumn<OrderPad, String> tableColumnCodeOrderPad;
    @FXML private TableColumn<OrderPad, String> tableColumnStatus;
    @FXML private TableColumn tableColumnView;
    
    @FXML private BorderPane borderpane2;
    SceneChange sceneChange = new SceneChange();
    
    // 
    @FXML private Text totalOrderPad;
    @FXML private Text totalOrderPadInUse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listOrderPad();
        statusOrderPad();
    }
    
    private void countTotalOrderPadUse(){
        totalOrderPadInUse.setText("COMANDA OCUPADA ("+repositoryOfOrderPad.countTotalOrderPadInUse()+")");
    }
    
    private void countTotalOrderPad(){
        totalOrderPad.setText("COMANDA: "+repositoryOfOrderPad.countTotalOrderPad());
    }
    
    
    // 
    private void listOrderPad(){
        countTotalOrderPad();
        countTotalOrderPadUse();
        tableColumnCodeOrderPad.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        // Criar um botão para deletar o reserva.
        Callback<TableColumn<OrderPad,String>,TableCell<OrderPad,String>> cellFactoryView = (param) -> {
            
            final TableCell<OrderPad,String> cell = new TableCell<OrderPad,String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else
                    {
                        final Button buttonView = new Button("+");
                        OrderPad orderPad = getTableView().getItems().get(getIndex());
                        buttonView.setOnAction(event -> {
                            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/FXMLSystemProductOrder.fxml"));
                            fxmlloader.setController(new FXMLSystemProductsOrderController(orderPad));
                            sceneChange.sceneTransition(borderpane2,"/fxml/FXMLSystemProductOrder.fxml");
                        });
                        setGraphic(buttonView);
                    }
                }
            };
            return cell;
        };
        tableColumnView.setCellFactory(cellFactoryView);
        tableOrderPad.setItems(repositoryOfOrderPad.readDB(null, 0));
    } 
    private void statusOrderPad(){
        tableOrderPad.setRowFactory(event -> {
            return new TableRow<OrderPad>() {
                @Override
                public void updateItem(OrderPad item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (item == null) {
                        setStyle(null);
                    } else if (item.getStatus().equals("1")) {
                        setStyle("-fx-text-background-color:  #ffffff;-fx-background-color:  #FF3D3D;");
                    }else {
                        setStyle(null);
                    }
                }
            };
       });
    }
}
