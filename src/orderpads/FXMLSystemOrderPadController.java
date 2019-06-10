package orderpads;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * Classe reponsável por definir os atributos e metódos de comanda.
 * @author Isaías de França Leite.
 */
public class FXMLSystemOrderPadController implements Initializable {
    
    // Instanciar RepositoryOfOrderPad
    RepositoryOfOrderPad repositoryOfOrderPad = new RepositoryOfOrderPad();
    
    // Mensagem de confirmação para abrir comanda.
    Alert alertCofirmation= new Alert(Alert.AlertType.CONFIRMATION);
    
    // Mensagem de informação de erros.
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);

    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    
    // Filtro
    @FXML private ComboBox comboBoxFilter;
    private List<String> listFilter = new ArrayList<>();
    ObservableList<String> observableListFilter = FXCollections.observableArrayList();
    
    // Valor total de produtos cadastrados.
    @FXML private Text totalOrderPad;
    @FXML private Text totalOrderPadInUse;
    
    // Pesquisar.
    @FXML private TextField searchOrderPad;
    
    // Tabela de Comanda
    @FXML 
    private TableView<OrderPad> tableOrderPad;
    @FXML private TableColumn<OrderPad, String> tableColumnCodeOrderPad;
    @FXML private TableColumn<OrderPad, String> tableColumnStatusOrderPad;
    @FXML private TableColumn tableColumnView;
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listOrderPad(null, 0);
        filter();
        statusOrderPad();
    }
    
    /**
     * Função de contar total de comanda em uso.
     */
    private void countTotalOrderPadInUse(){
        totalOrderPadInUse.setText("COMANDA EM USO: ("+repositoryOfOrderPad.countTotalOrderPadInUse()+")");
    }
    
    /**
     * Função de contar total de comanda.
     */
    private void countTotalOrderPad(){
        totalOrderPad.setText("COMANDA: "+repositoryOfOrderPad.countTotalOrderPad());
    }
    
    /**
     * Função de pesquisar.
     */
    @FXML private void search(){
        if(searchOrderPad.getText().equals("")){
            alertInformation.setContentText("Preencha o campo de pesquisa");
            alertInformation.showAndWait();
        }
        else{
            comboBoxFilter.getSelectionModel().select("PERSONALIZADO");
            listOrderPad(searchOrderPad.getText(),1);
            totalOrderPad.setText("COMANDA: "+repositoryOfOrderPad.countTotalOrderPad(searchOrderPad.getText()));
        }
    }
    
    /**
     * Filtro de pesquisa.
     */
    public void filter(){
        listFilter.add("TODOS");
        listFilter.add("PERSONALIZADO");
        observableListFilter = FXCollections.observableArrayList(listFilter); 
        comboBoxFilter.setItems(observableListFilter);
        comboBoxFilter.getSelectionModel().selectFirst();
        comboBoxFilter.setOnAction(eventFilter);
    }
    
    /**
     * Seleção de evento.
     */
    private EventHandler<ActionEvent> eventFilter = 
             (ActionEvent e) -> {
        if(comboBoxFilter.getValue().toString().equals("TODOS")){
            searchOrderPad.setText(null);
            listOrderPad(null,0);
        }
    };
    
    /**
     * Função de listar comandas.
     */
    private void listOrderPad(String search, int value){
        countTotalOrderPad();
        countTotalOrderPadInUse();
        tableColumnCodeOrderPad.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnStatusOrderPad.setCellValueFactory(new PropertyValueFactory<>("status"));
        // Criar um botão para deletar o produto.
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
                        final Button buttonUpdate = new Button("ABRIR");
                        buttonUpdate.setOnAction(event -> {
                            OrderPad orderPad = getTableView().getItems().get(getIndex());
                            alertCofirmation.setTitle("Abrir Comanda");
                            alertCofirmation.setHeaderText(null);
                            alertCofirmation.setContentText("Tem certeza que deseja abrir está comanda?");
                            Optional <ButtonType> actionView = alertCofirmation.showAndWait();
                            if(actionView.get() == ButtonType.OK){
                                try {
                                    if(orderPad.getStatus().equals("1")){
                                       FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/orderpads/FXMLSystemOrders.fxml"));
                                       fxmlloader.setController(new FXMLSystemOrdersController(orderPad));
                                       sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
                                    }
                                    else{
                                        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/orderpads/FXMLSystemAddTables.fxml"));
                                        fxmlloader.setController(new FXMLSystemAddTablesController(orderPad));
                                        sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLSystemOrderPadController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        setGraphic(buttonUpdate);
                    }
                }
            };
            return cell;
        };
        tableColumnView.setCellFactory(cellFactoryView);
        tableOrderPad.setItems(repositoryOfOrderPad.readDB(search, value)); 
    }
    
    /**
     *  Função que fornecer o status da comanda.
     */
    private void statusOrderPad(){
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        tableOrderPad.setRowFactory(event -> {
            return new TableRow<OrderPad>() {
                @Override
                public void updateItem(OrderPad item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (item == null) {
                        setStyle(null);
                    } else if (item.getStatus().equals("1")) {
                        setStyle("-fx-text-background-color:  #ffffff;-fx-background-color:  #FF3D3D;");
                    }else{
                        setStyle(null);
                    }
                }
            };
       });
    }
}
