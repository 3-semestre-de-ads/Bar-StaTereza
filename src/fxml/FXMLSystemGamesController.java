package fxml;

import classes.Game;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import repositories.RepositoryOfGames;

/**
 * FXML Controller class
 *
 * @author Isaías de França Leite.
 */
public class FXMLSystemGamesController implements Initializable {
    
    // Instancia do RepositoryOfGames.
    RepositoryOfGames repositoryOfGames = new RepositoryOfGames();
    
    // Mensagem de confirmação.
    Alert alertConfirmation= new Alert(Alert.AlertType.CONFIRMATION);
    
    // Mensagem de informação.
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    
    // TableView para visualizar todos jogos.
    @FXML
    private TableView<Game> tableGames;
    @FXML private TableColumn<Game, String> tableColumnCodeGame;
    @FXML private TableColumn<Game, String> tableColumnNameGame;
    @FXML private TableColumn<Game, String> tableColumnCategoryGame;
    @FXML private TableColumn<Game, String> tableColumnDescriptionGame;
    @FXML private TableColumn tableColumnUpdate;
    @FXML private TableColumn tableColumnDelete;
    
    // Filtro
    @FXML private ComboBox comboBoxFilter;
    private List<String> listFilter = new ArrayList<>();
    ObservableList<String> observableListFilter = FXCollections.observableArrayList();
    
    // Valor total de jogos cadastrados.
    @FXML private Text totalGames;
    @FXML private Text totalGamesAllocated;
    
    // Pesquisar.
    @FXML private TextField searchGame;
   
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listGames(null, 0);
        filter();
    }
    
    // Função responsável por fazer a transição para tela de inserir jogos.
    @FXML private void loadSceneInsertGames() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/FXMLSystemInsertGame.fxml"));
        fxmlloader.setController(new FXMLSystemInsertGameController(null, "C"));
        sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
    }
    // Função responsável contar quantidade de jogos cadastrados.
    private void countTotalGames(){
        totalGames.setText("GAMES: "+repositoryOfGames.countTotalGames());
    }
    
    // Função responsável contar quantidade de jogos alocados.
    private void countTotalGamesAllocated(){
        totalGamesAllocated.setText("JOGOS ALOCADOS ("+repositoryOfGames.countTotalGamesAllocated()+")");
    }
        //
    @FXML private void search(){
        if(searchGame.getText().equals("")){
            alertInformation.setContentText("Preencha o campo de pesquisa");
            alertInformation.showAndWait();
        }
        else{
            comboBoxFilter.getSelectionModel().select("PERSONALIZADO");
            listGames(searchGame.getText(),1);
            totalGames.setText("GAMES: "+repositoryOfGames.countTotalGames(searchGame.getText()));
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
            searchGame.setText(null);
            listGames(null,0);
        }
    };
   
    // Função responsável por listar todos jogos no TableView
    private void listGames(String search, int value){
        countTotalGames();
        countTotalGamesAllocated();
        tableColumnCodeGame.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnNameGame.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnCategoryGame.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableColumnDescriptionGame.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        // Criar o botão para editar os dados de jogo.
        Callback<TableColumn<Game,String>,TableCell<Game,String>> cellFactoryUpdate = (param) -> {
            
            final TableCell<Game,String> cell = new TableCell<Game,String>(){
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
                            Game game = getTableView().getItems().get(getIndex());
                            alertConfirmation.setTitle("Editar Jogo");
                            alertConfirmation.setHeaderText(null);
                            alertConfirmation.setContentText("Tem certeza que deseja editar esse jogo ?");
                            Optional <ButtonType> actionDelete = alertConfirmation.showAndWait();
                            // Verificar se o botão Ok foi pressionado.
                            if(actionDelete.get() == ButtonType.OK){
                                try {
                                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/FXMLSystemInsertGame.fxml"));
                                    fxmlloader.setController(new FXMLSystemInsertGameController(game,"U"));
                                    sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLSystemGamesController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        // Criar um botão para deletar o jogo.
        Callback<TableColumn<Game,String>,TableCell<Game,String>> cellFactoryDelete = (param) -> {
            
            final TableCell<Game,String> cell = new TableCell<Game,String>(){
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
                            alertConfirmation.setTitle("Deletar Jogo");
                            alertConfirmation.setHeaderText(null);
                            alertConfirmation.setContentText("Tem certeza que deseja deletar esse jogo ?");
                            Optional <ButtonType> actionDelete = alertConfirmation.showAndWait();
                            if(actionDelete.get() == ButtonType.OK){
                                // Verificar se o botão Ok foi pressionado.
                                repositoryOfGames.game = new Game(getTableView().getItems().get(getIndex()).getCode(),null,null,null);
                                repositoryOfGames.deleteDB();
                                listGames(search,value);
                            }
                        });
                        setGraphic(buttonUpdate);
                    }
                }
            };
            return cell;
        };
        tableColumnDelete.setCellFactory(cellFactoryDelete);
        tableGames.setItems(repositoryOfGames.readDB(search,value));
    } 
}
