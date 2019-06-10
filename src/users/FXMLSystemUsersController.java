package users;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * Classe responsável por definir os atributos e metódos necessários para listar usuários.
 * @author Isaías de França Leite
 */
public class FXMLSystemUsersController implements Initializable {

    // Instancia do RepositoryOfUsers.
    RepositoryOfUsers repositoryOfUsers = new RepositoryOfUsers();
    
    // Mensagem de confirmação.
    Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    
    // Mensagem de informação.
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    
    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    
    // TableView para visualizar todos usuários.
    @FXML
    private TableView<User> tableUser;
    @FXML private TableColumn<User, String> tableColumnCodeUser;
    @FXML private TableColumn<User, String> tableColumnNameUser;
    @FXML private TableColumn<User, String> tableColumnUsername;
    @FXML private TableColumn<User, String> tableColumnTypeOfPermission;
    @FXML private TableColumn tableColumnUpdate;
    @FXML private TableColumn tableColumnDelete;
    
    // Filtro
    @FXML private ComboBox comboBoxFilter;
    private List<String> listFilter = new ArrayList<>();
    ObservableList<String> observableListFilter = FXCollections.observableArrayList();
    
    // Valor total de usuários cadastrados.
    @FXML private Text totalUser;
    
    // Pesquisar.
    @FXML private TextField searchUser;
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listUser(null, 0);
       filter();
    }   
    
    /**
     * Transição de tela.
     */
    @FXML private void loadSceneInsertUser() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/users/FXMLSystemInsertUser.fxml"));
        fxmlloader.setController(new FXMLSystemInsertUserController(null, "C"));
        sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
    }
    
    /**
     * Função responsável por contar quantidade total usuários. 
     */
    private void countTotalUser(){
        totalUser.setText("USUÁRIOS: "+repositoryOfUsers.countTotalUsers());
    }
    
   /**
    * Função de pesquisar.
    */
    @FXML private void search(){
        if(searchUser.getText().equals("")){
            alertInformation.setContentText("Preencha o campo de pesquisa");
            alertInformation.showAndWait();
        }
        else{
            comboBoxFilter.getSelectionModel().select("PERSONALIZADO");
            listUser(searchUser.getText(),1);
            totalUser.setText("GAMES: "+repositoryOfUsers.countTotalUsers(searchUser.getText()));
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
            searchUser.setText(null);
            listUser(null,0);
        }
    };
    
    /**
     * Função responsável por listar usuários.
     * @param search - valor pesquisado.
     * @param value - tipo de pesquisa.
     */
    private void listUser(String search, int value){
        countTotalUser();
        tableColumnCodeUser.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnNameUser.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnTypeOfPermission.setCellValueFactory(new PropertyValueFactory<>("typeOfPermission"));
        
        // Criar o botão para editar os dados de usuários.
        Callback<TableColumn<User,String>,TableCell<User,String>> cellFactoryUpdate = (param) -> {
            
            final TableCell<User,String> cell = new TableCell<User,String>(){
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
                            User user = getTableView().getItems().get(getIndex());
                            alertConfirmation.setTitle("Editar Usuário");
                            alertConfirmation.setHeaderText(null);
                            alertConfirmation.setContentText("Tem certeza que deseja editar esse usuário ?");
                            Optional <ButtonType> actionDelete = alertConfirmation.showAndWait();
                            // Verificar se o botão Ok foi pressionado.
                            if(actionDelete.get() == ButtonType.OK){
                                try {
                                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/users/FXMLSystemInsertUser.fxml"));
                                    fxmlloader.setController(new FXMLSystemInsertUserController(user,"U"));
                                    sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLSystemUsersController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        // Criar um botão para deletar o usuários.
        Callback<TableColumn<User,String>,TableCell<User,String>> cellFactoryDelete = (param) -> {
            
            final TableCell<User,String> cell = new TableCell<User,String>(){
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
                            alertConfirmation.setTitle("Deletar Usuário");
                            alertConfirmation.setHeaderText(null);
                            alertConfirmation.setContentText("Tem certeza que deseja deletar esse usuário ?");
                            Optional <ButtonType> actionDelete = alertConfirmation.showAndWait();
                            if(actionDelete.get() == ButtonType.OK){
                                // Verificar se o botão Ok foi pressionado.
                                repositoryOfUsers.user = new User(getTableView().getItems().get(getIndex()).getCode(),null,null,null, null);
                                repositoryOfUsers.deleteDB();
                                listUser(search,value);
                            }
                        });
                        setGraphic(buttonUpdate);
                    }
                }
            };
            return cell;
        };
        tableColumnDelete.setCellFactory(cellFactoryDelete);
        tableUser.setItems(repositoryOfUsers.readDB(search,value));
    }
    
    /**
    * Tela de Help de usuários.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        alertHelp.setGraphic(new ImageView(("/images/information-help-users.jpg")));
        alertHelp.showAndWait();
    }
}
