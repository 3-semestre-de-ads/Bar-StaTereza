package orderpads;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import orders.Order;
import orders.RepositoryOfOrders;

/**
 * Classe responsável por definir atributos e metódos de comanda.
 * @author Isaias de França Leite.
 */
public class FXMLSystemOrdersController implements Initializable {
    
    // Instanciar de pedido.
    RepositoryOfOrders repositoryOfOrders = new RepositoryOfOrders();
    
    // Tabela de pedidos
    @FXML 
    private TableView<Order> tableOrder;
    @FXML private TableColumn<Order, String> tableColumnCodeOrder;
    @FXML private TableColumn<Order, String> tableColumnNameProduct;
    @FXML private TableColumn<Order, String> tableColumnAmountProduct;
    @FXML private TableColumn<Order, String> tableColumnPriceProduct;
    @FXML private TableColumn<Order, String> tableColumnTotalOrder;
    
    // Comanda & Mesa
    @FXML private Label labelCodeOrderPad;
    @FXML private Text textCodeTable;
    
    // Total da comanda
    @FXML private Text totalOrderPad;
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    // Comanda
    private OrderPad orderPad;
    /**
     * Constructor da classe FXMLSystemOrdersController.
     * @param orderPad - objeto da comanda
     */
    public FXMLSystemOrdersController(OrderPad orderPad){
        this.orderPad = orderPad;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        repositoryOfOrders.codeOrderPad = Integer.toString(orderPad.getCode());
        listOrders(null, 0);
        setOrderPadAndTable();
    }
    /**
     * Transição de Tela: Tela de OrderPad.
     */
    @FXML private void loadSceneOrderPad() throws IOException{
        sceneChange.sceneTransition(stackPane, anchorPane, "/orderpads/FXMLSystemOrderPad.fxml");
    }
    
    @FXML private void loadSceneProductOrder() throws IOException{
        sceneChange.sceneTransition(stackPane, anchorPane, "/orderpads/FXMLSystemProductOrder.fxml");
    }
    
    /**
     * Função de setar o valor da comanda no titulo da página.
     */
    private void setOrderPadAndTable(){
        labelCodeOrderPad.setText("COMANDA: "+Integer.toString(orderPad.getCode()));
        textCodeTable.setText("MESA: "+repositoryOfOrders.searchTable());
        totalOrderPad.setText("R$: "+String.format("%.2f", repositoryOfOrders.totalOrder()));
    }
    
     /**
     * Função de listar pedidos;
     * @param search
     * @param value 
     */
    private void listOrders(String search, int value){
        tableColumnCodeOrder.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnNameProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableColumnAmountProduct.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableColumnPriceProduct.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumnTotalOrder.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableOrder.setItems(repositoryOfOrders.readDB(search, value));
    }
}