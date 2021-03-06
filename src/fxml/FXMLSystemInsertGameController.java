package fxml;

import classes.Game;
import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import repositories.RepositoryOfGames;

/**
 * FXML Controller class
 *
 * @author Isaías de França Leite
 */
public class FXMLSystemInsertGameController implements Initializable {
    
    // Mensagem de confirmação para alterar ou deletar.
    Alert alertSystem = new Alert(Alert.AlertType.CONFIRMATION);
    
    // Mensagem de status de error
    Alert alertError = new Alert(Alert.AlertType.INFORMATION);
    
    // Titulo do menu de acordo com a escolha da função.
    @FXML private Label titleTypeFunction;

    // Instancia do RepositoryOfGames.
    RepositoryOfGames repositoryOfGames = new RepositoryOfGames();
    
    // TextField para inserir os dados no banco de dados.
    @FXML private TextField textFieldNameGame;
    @FXML private ComboBox comboBoxCategoryGame;
    private List<String> categoryGame = new ArrayList<>();
    @FXML private TextArea textFieldDescription;
    @FXML private Button buttonTypeFunction;
    private ObservableList<String> listCategory;
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    // Variáveis do constructor.
    private Game game;
    private String typeFunction;
    
    /**
     * O contructor da classe FXMLSystemInsertGameController.
     * @param game - um objeto do tipo game.
     * @param typeFunction - tipo de função que a tela vai execultar.
     */
    public FXMLSystemInsertGameController(Game game, String typeFunction)
    {
        this.game = game;
        this.typeFunction = typeFunction;
    }
    
    public FXMLSystemInsertGameController(){
        
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listCategoryGame();
        operationsCreateAndUpdate();
        
    }    
   
    /**
     * Transição da tela de inserir jogos para telas de lista jogos.
     * @throws IOException 
     */
    @FXML private void loadSceneGames() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/fxml/FXMLSystemGames.fxml");
    }
    

    /**
     * Listar todos os valores para o comboBox de categoria de games.
     */
    public void listCategoryGame(){
        categoryGame.add("Bebidas");
        categoryGame.add("Comidas");
        listCategory = FXCollections.observableArrayList(categoryGame);
        comboBoxCategoryGame.setItems(listCategory);
    } 
    
    /**
     *
     */
    public void operationsCreateAndUpdate(){
        comboBoxCategoryGame.getSelectionModel().selectFirst();
        if(typeFunction.equals("C")){
            titleTypeFunction.setText("ADICIONAR JOGO");
            buttonTypeFunction.setText("ADICIONAR");
            buttonTypeFunction.setOnAction((ActionEvent event) -> {
                if(!"".equals(textFieldNameGame.getText())){
                    repositoryOfGames.game =  new Game(0, textFieldNameGame.getText(), comboBoxCategoryGame.getValue().toString(), textFieldDescription.getText());
                    String resultQuery = repositoryOfGames.createDB();
                    // Verificar se os dados foram cadastrados.
                    if(resultQuery.equals("CREATE")){
                        alertError.setHeaderText(null);
                        alertError.setContentText("Dados cadastrados com sucesso");
                    }
                    else{
                        alertError.setContentText(resultQuery);
                    }
                    alertError.showAndWait();
                    try {
                        loadSceneGames();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSystemInsertGameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    alertError.setContentText("Preencha os campos obrigatórios!");
                    alertError.showAndWait();
                }
            });
        }
        else
        {
            titleTypeFunction.setText("EDITAR JOGO");
            buttonTypeFunction.setText("EDITAR");
            textFieldNameGame.setText(game.getName());
            comboBoxCategoryGame.setValue(game.getCategory());
            textFieldDescription.setText(game.getDescription());
            buttonTypeFunction.setOnAction((ActionEvent event) -> {
                if(!"".equals(textFieldNameGame.getText())){
                    repositoryOfGames.game =  new Game(game.getCode(), textFieldNameGame.getText(), comboBoxCategoryGame.getValue().toString(), textFieldDescription.getText());
                    String resultQuery = repositoryOfGames.updateDB();
                    if(resultQuery.equals("UPDATE")){
                        alertError.setHeaderText(null);
                        alertError.setContentText("Dados alterados com sucesso");
                    }
                    else{
                        alertError.setContentText(resultQuery);
                    }
                        alertError.showAndWait();
                    try {
                        loadSceneGames();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSystemInsertGameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    alertError.setContentText("Preencha os campos obrigatórios!");
                    alertError.showAndWait();
                }
            });
        }
    }
}
