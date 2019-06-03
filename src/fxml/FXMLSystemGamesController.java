package fxml;

import classes.Game;
import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import repositories.RepositoryOfGames;

/**
 * FXML Controller class
 *
 * @author isaia
 */
public class FXMLSystemGamesController implements Initializable {
    
    // Instancia do RepositoryOfGames.
    RepositoryOfGames repositoryOfGames = new RepositoryOfGames();
    
    // Mensagem de confirmação para alterar ou deletar jogos.
    Alert alertSystem = new Alert(Alert.AlertType.CONFIRMATION);
    
    // TableView para visualizar todos jogos.
    @FXML
    private TableView<Game> tableGames;
    @FXML private TableColumn<Game, String> tableColumnCodeGame;
    @FXML private TableColumn<Game, String> tableColumnNameGame;
    @FXML private TableColumn<Game, String> tableColumnCategoryGame;
    @FXML private TableColumn<Game, String> tableColumnDescriptionGame;
    @FXML private TableColumn tableColumnUpdate;
    @FXML private TableColumn tableColumnDelete;
    
    // Valor total de jogos cadastrados.
    @FXML private Text totalGames;
    @FXML private Text totalGamesAllocated;
   
    // Transição de rela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listGames();
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
   
    // Função responsável por listar todos jogos no TableView
    private void listGames(){
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
                            alertSystem.setTitle("Editar Jogo");
                            alertSystem.setHeaderText(null);
                            alertSystem.setContentText("Tem certeza que deseja editar esse jogo ?");
                            Optional <ButtonType> actionDelete = alertSystem.showAndWait();
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
                            alertSystem.setTitle("Deletar Jogo");
                            alertSystem.setHeaderText(null);
                            alertSystem.setContentText("Tem certeza que deseja deletar esse jogo ?");
                            Optional <ButtonType> actionDelete = alertSystem.showAndWait();
                            if(actionDelete.get() == ButtonType.OK){
                                // Verificar se o botão Ok foi pressionado.
                                repositoryOfGames.game = new Game(getTableView().getItems().get(getIndex()).getCode(),null,null,null);
                                repositoryOfGames.deleteDB();
                                listGames();
                            }
                        });
                        setGraphic(buttonUpdate);
                    }
                }
            };
            return cell;
        };
        tableColumnDelete.setCellFactory(cellFactoryDelete);
        tableGames.setItems(repositoryOfGames.readDB());
    } 
}
