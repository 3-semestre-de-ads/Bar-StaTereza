package fxml;

import classes.Product;
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
import repositories.RepositoryOfProducts;

/**
 * Classe responsável por estabelecer a conexão com classe repositoryOfProducts com a interface de produtos.
 * @author Isaías de França Leite
 */
public class FXMLSystemProductsController implements Initializable {
    
    // Instancia do RepositoryOfProducts.
    RepositoryOfProducts repositoryOfProducts = new RepositoryOfProducts();
    
    // Mensagem de confirmação para alterar ou deletar produtos.
    Alert alertSystem = new Alert(Alert.AlertType.CONFIRMATION);
    
    // Mensagem de informação de erros.
    Alert alertSystemInformation = new Alert(Alert.AlertType.INFORMATION);
    
    // TableView para visualizar todos produtos.
    @FXML 
    private TableView<Product> tableProducts;
    @FXML private TableColumn<Product, String> tableColumnCodeProduct;
    @FXML private TableColumn<Product, String> tableColumnNameProduct;
    @FXML private TableColumn<Product, String> tableColumnCategoryProduct;
    @FXML private TableColumn<Product, String> tableColumnPriceProduct;
    @FXML private TableColumn<Product, String> tableColumnDescriptionProduct;
    @FXML private TableColumn tableColumnUpdate;
    @FXML private TableColumn tableColumnDelete;
    
    // Valor total de produtos cadastrados.
    @FXML private Text totalProducts;
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listProducts();
    }
    
    // Função responsável por fazer a transição para tela de inserir produtos.
    @FXML private void loadSceneInsertProduct() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/FXMLSystemInsertProduct.fxml"));
        fxmlloader.setController(new FXMLSystemInsertProductController(null, "C"));
        sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
    }
    
    // Função responsável contar quantidade de produtos cadastrados.
    private void countTotalProducts(){
        totalProducts.setText("PRODUTOS: "+repositoryOfProducts.countTotalProducts());
    }
    
    // Função responsável por listar todos produtos no TableView.
    private void listProducts(){
        countTotalProducts();
        tableColumnCodeProduct.setCellValueFactory(new PropertyValueFactory<>(""));
        tableColumnNameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnCategoryProduct.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableColumnPriceProduct.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumnDescriptionProduct.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        // Criar o botão para editar os dados de produtos.
        Callback<TableColumn<Product,String>,TableCell<Product,String>> cellFactoryUpdate = (param) -> {
            
            final TableCell<Product,String> cell = new TableCell<Product,String>(){
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
                            Product product = getTableView().getItems().get(getIndex());
                            alertSystem.setTitle("Editar Produto");
                            alertSystem.setHeaderText(null);
                            alertSystem.setContentText("Tem certeza que deseja editar esse produto ?");
                            Optional <ButtonType> actionDelete = alertSystem.showAndWait();
                            // Verificar se o botão Ok foi pressionado.
                            if(actionDelete.get() == ButtonType.OK){
                                try {
                                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/FXMLSystemInsertProduct.fxml"));
                                    fxmlloader.setController(new FXMLSystemInsertProductController(product,"U"));
                                    sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLSystemProductsController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        // Criar um botão para deletar o produto.
        Callback<TableColumn<Product,String>,TableCell<Product,String>> cellFactoryDelete = (param) -> {
            
            final TableCell<Product,String> cell = new TableCell<Product,String>(){
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
                            alertSystem.setTitle("Deletar Produto");
                            alertSystem.setHeaderText(null);
                            alertSystem.setContentText("Tem certeza que deseja deletar esse produto ?");
                            Optional <ButtonType> actionDelete = alertSystem.showAndWait();
                            if(actionDelete.get() == ButtonType.OK){
                                // Verificar se o botão Ok foi pressionado.
                                repositoryOfProducts.product = new Product(getTableView().getItems().get(getIndex()).getCode(),null,null,0,null);
                                String result = repositoryOfProducts.deleteDB();
                                if(!result.equals("DELETE")){
                                    alertSystemInformation.setContentText("Produto Utilizado");
                                    alertSystemInformation.showAndWait();
                                }
                                listProducts();
                            }
                        });
                        setGraphic(buttonUpdate);
                    }
                }
            };
            return cell;
        };
        tableColumnDelete.setCellFactory(cellFactoryDelete);
        
        tableProducts.setItems(repositoryOfProducts.readDB()); 
    } 
}
