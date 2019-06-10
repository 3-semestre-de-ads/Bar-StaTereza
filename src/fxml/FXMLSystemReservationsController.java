package fxml;

import classes.Reservation;
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
import repositories.RepositoryOfReservation;

/**
 * Classe responsável por estabelecer a conexão com a classe repositoryOfReservation com a interface de reserva. 
 * @author Isaías de França Leite
 */
public class FXMLSystemReservationsController implements Initializable {
    
    // Instaciar o RepositoryOfReservation
    RepositoryOfReservation repositoryOfReservation = new RepositoryOfReservation();
    
    // Mensagem de confirmação
    Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    
    // Mensagem de informação
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    
    // TableView para visualizar todos reservas.
    @FXML
    private TableView<Reservation> tableReservation;
    @FXML private TableColumn<Reservation, String> tableColumnCodeReservation;
    @FXML private TableColumn<Reservation, String> tableColumnMesa;
    @FXML private TableColumn<Reservation, String> tableColumnCustomer;
    @FXML private TableColumn<Reservation, String> tableColumnDate;
    @FXML private TableColumn<Reservation, String> tableColumnObservation;
    @FXML private TableColumn tableColumnUpdate;
    @FXML private TableColumn tableColumnDelete;
    
    // Filtro
    @FXML private ComboBox comboBoxFilter;
    private List<String> listFilter = new ArrayList<>();
    ObservableList<String> observableListFilter = FXCollections.observableArrayList();
    
    // Valor total de reserva cadastrados.
    @FXML private Text totalReservation;
    
    // Valor total de reserva cadastra para hoje.
    @FXML private Text totalReservationToday;
    
    // Pesquisar.
    @FXML private TextField searchReservation;
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listReservation(null,0);
       filter();
       statusReservation();
    }    
    
    // Função responsável por fazer a transição para tela de inserir reserva.
    @FXML private void loadSceneInsertReservation() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/FXMLSystemInsertReservation.fxml"));
        fxmlloader.setController(new FXMLSystemInsertReservationController(null, "C"));
        sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
    }
    
    // Função responsável por contar a quantidade de reserva cadastrados.
    private void countTotalReservation(){
        totalReservation.setText("RESERVAS: "+repositoryOfReservation.countTotalReservation());
    }
    
    // Função responsável por contar a quantidade de reservas cadastrada para dia atual.
    private void countTotalReservationToday(){
        totalReservationToday.setText("RESERVA PARA HOJE ("+repositoryOfReservation.countTotalReservationToday()+")");
    }
    
    //
    @FXML private void search(){
        if(searchReservation.getText().equals("")){
            alertInformation.setContentText("Preencha o campo de pesquisa");
            alertInformation.showAndWait();
        }
        else{
            comboBoxFilter.getSelectionModel().select("PERSONALIZADO");
            listReservation(searchReservation.getText(),1);
            totalReservation.setText("RESERVAS: "+repositoryOfReservation.countTotalReservation(searchReservation.getText()));
        }
    }
    
    /**
     *
     */
    public void filter(){
        listFilter.add("TODOS");
        listFilter.add("PERSONALIZADO");
        observableListFilter = FXCollections.observableArrayList(listFilter); 
        comboBoxFilter.setItems(observableListFilter);
        comboBoxFilter.getSelectionModel().selectFirst();
        comboBoxFilter.setOnAction(eventFilter);
    }
    
    // Seleção de evento.
    private EventHandler<ActionEvent> eventFilter = 
             (ActionEvent e) -> {
        if(comboBoxFilter.getValue().toString().equals("TODOS")){
            searchReservation.setText(null);
            listReservation(null,0);
        }
    };
    
    //
    private void listReservation(String search, int value){
        countTotalReservation();
        countTotalReservationToday();
        tableColumnCodeReservation.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnMesa.setCellValueFactory(new PropertyValueFactory<>("codeTable"));
        tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnObservation.setCellValueFactory(new PropertyValueFactory<>("observation"));
        
        // Criar o botão para editar os dados de reserva.
        Callback<TableColumn<Reservation,String>,TableCell<Reservation,String>> cellFactoryUpdate = (param) -> {
            
            final TableCell<Reservation,String> cell = new TableCell<Reservation,String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else
                    {
                        final Button buttonUpdate = new Button("EDITAR");
                        buttonUpdate.setOnAction(event -> {
                            Reservation reservation = getTableView().getItems().get(getIndex());
                            alertConfirmation.setTitle("Editar Reserva");
                            alertConfirmation.setHeaderText(null);
                            alertConfirmation.setContentText("Tem certeza que deseja editar esse reserva ?");
                            Optional <ButtonType> actionDelete = alertConfirmation.showAndWait();
                            // Verificar se o botão Ok foi pressionado.
                            if(actionDelete.get() == ButtonType.OK){
                                try {
                                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/FXMLSystemInsertReservation.fxml"));
                                    fxmlloader.setController(new FXMLSystemInsertReservationController(reservation,"U"));
                                    sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLSystemReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } 
                        });
                        setGraphic(buttonUpdate);
                    }
                }
            };
            return cell;
        };
        tableColumnUpdate.setCellFactory(cellFactoryUpdate);
        
        // Criar um botão para deletar o reserva.
        Callback<TableColumn<Reservation,String>,TableCell<Reservation,String>> cellFactoryDelete = (param) -> {
            
            final TableCell<Reservation,String> cell = new TableCell<Reservation,String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else
                    {
                        final Button buttonUpdate = new Button("DELETAR");
                        buttonUpdate.setOnAction(event -> {
                            alertConfirmation.setTitle("Deletar Reserva");
                            alertConfirmation.setHeaderText(null);
                            alertConfirmation.setContentText("Tem certeza que deseja deletar esse reserva ?");
                            Optional <ButtonType> actionDelete = alertConfirmation.showAndWait();
                            if(actionDelete.get() == ButtonType.OK){
                                // Verificar se o botão Ok foi pressionado.
                                repositoryOfReservation.reservation = new Reservation(getTableView().getItems().get(getIndex()).getCode(),0,null,null,null);
                                repositoryOfReservation.deleteDB();
                                listReservation(search,value);
                            }
                        });
                        setGraphic(buttonUpdate);
                    }
                }
            };
            return cell;
        };
        tableColumnDelete.setCellFactory(cellFactoryDelete);
        tableReservation.setItems(repositoryOfReservation.readDB(search,value));
    }
    
    private void statusReservation(){
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        tableReservation.setRowFactory(event -> {
            return new TableRow<Reservation>() {
                @Override
                public void updateItem(Reservation item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (item == null) {
                        setStyle(null);
                    } else if (item.getDate().equals(date)) {
                        setStyle("-fx-text-background-color:  #ffffff;-fx-background-color:  #FF3D3D;");
                    } else if(item.getDate().before(date)){
                        setStyle("-fx-text-background-color:  #ffffff;-fx-background-color:  #CBCACA;");
                    }else {
                        setStyle(null);
                    }
                }
            };
       });
    }
   
}
