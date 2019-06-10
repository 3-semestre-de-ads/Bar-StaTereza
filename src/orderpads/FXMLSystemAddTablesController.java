package orderpads;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import reservations.RepositoryOfReservation;

/**
 * Classe responsável por definir todos os atributos e metódos de adicionar tabela.
 * @author Isaías de França Leite
 */
public class FXMLSystemAddTablesController implements Initializable {
    
    // Instaciar RepositoryOfReservation
    RepositoryOfReservation repositoryOfReservation = new RepositoryOfReservation();
    
    // Instaciar RepositoryOfOrderPad
    RepositoryOfOrderPad repositoryOfOrderPad = new RepositoryOfOrderPad();
    
    // Mensagem de confirmação para abrir comanda.
    Alert alertCofirmation= new Alert(Alert.AlertType.CONFIRMATION);
    
    // Mesa
    @FXML private ComboBox comboBoxTable;
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    // Campos
    @FXML private Text textOrderPad;
    
    // Comanda
    private OrderPad orderPad;

    /**
     * Construtor da classe FXMLSystemAddTablesController.
     * @param orderpad - objeto comanda.
     */
    public FXMLSystemAddTablesController(OrderPad orderpad){
        this.orderPad = orderpad;    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textOrderPad.setText("COMANDA: "+Integer.toString(orderPad.getCode()));
        listTables();
    }
    
    /**
     * Transição de Tela: Tela de Orders.
     */
    @FXML private void loadSceneOrders() throws IOException{
        alertCofirmation.setTitle("ADICIONAR MESA");
        alertCofirmation.setHeaderText(null);
        alertCofirmation.setContentText("Tem certeza que deseja associar comanda com a mesa?");
        Optional <ButtonType> actionView = alertCofirmation.showAndWait();
        if(actionView.get() == ButtonType.OK){
            repositoryOfOrderPad.addOrder(comboBoxTable.getValue().toString(), Integer.toString(orderPad.getCode()));
            repositoryOfOrderPad.statusOrderPad(Integer.toString(orderPad.getCode()));
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/orderpads/FXMLSystemOrders.fxml"));
            fxmlloader.setController(new FXMLSystemOrdersController(orderPad));
            sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
        }
    }

    /**
     * Transição de Tela: Tela de OrderPad.
     */
    @FXML private void loadSceneOrderPad() throws IOException{
        alertCofirmation.setTitle("CANCELAR");
        alertCofirmation.setHeaderText(null);
        alertCofirmation.setContentText("Tem certeza que deseja cancelar operação?");
        Optional <ButtonType> actionView = alertCofirmation.showAndWait();
        if(actionView.get() == ButtonType.OK){
            sceneChange.sceneTransition(stackPane, anchorPane, "/orderpads/FXMLSystemOrderPad.fxml");
        }
    }
    
    /**
     * Função de Listar mesas no ComboBox.
     */
    private void listTables(){
        comboBoxTable.setItems(repositoryOfReservation.tables());
        comboBoxTable.getSelectionModel().selectFirst();
    }
}
