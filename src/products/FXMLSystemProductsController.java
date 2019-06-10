package products;

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
 * Classe responsável por definir todos atributos e metódos para listar produtos.
 * @author Isaías de França Leite
 */
public class FXMLSystemProductsController implements Initializable {
    
    // Instancia do RepositoryOfProducts.
    RepositoryOfProducts repositoryOfProducts = new RepositoryOfProducts();
    
    // Mensagem de confirmação para alterar ou deletar produtos.
    Alert alertCofirmation= new Alert(Alert.AlertType.CONFIRMATION);
    
    // Mensagem de informação de erros.
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);

    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    
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
    
    // Filtro
    @FXML private ComboBox comboBoxFilter;
    private List<String> listFilter = new ArrayList<>();
    ObservableList<String> observableListFilter = FXCollections.observableArrayList();
    
    // Valor total de produtos cadastrados.
    @FXML private Text totalProducts;
    
    // Pesquisar.
    @FXML private TextField searchProduct;
    
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listProducts(null,0);
        filter();
    }
    
    /**
     * Função responsável por fazer a transição para tela de inserir produtos.
     * @throws IOException 
     */
    @FXML private void loadSceneInsertProduct() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/products/FXMLSystemInsertProduct.fxml"));
        fxmlloader.setController(new FXMLSystemInsertProductController(null, "C"));
        sceneChange.sceneTransition(stackPane, anchorPane, fxmlloader);
    }
    
    /**
     * Função responsável contar quantidade de produtos cadastrados.
     */
    private void countTotalProducts(){
        totalProducts.setText("PRODUTOS: "+repositoryOfProducts.countTotalProducts());
    }
    
    /**
     * Função de pesquisa.
     */
    @FXML private void search(){
        if(searchProduct.getText().equals("")){
            alertInformation.setContentText("Preencha o campo de pesquisa");
            alertInformation.showAndWait();
        }
        else{
            comboBoxFilter.getSelectionModel().select("PERSONALIZADO");
            listProducts(searchProduct.getText(),1);
            totalProducts.setText("PRODUTOS: "+repositoryOfProducts.countTotalProducts(searchProduct.getText()));
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
            searchProduct.setText(null);
            listProducts(null,0);
        }
    };
    
    /**
     * Função responsável por listar todos produtos no TableView.
     * @param search - valor de pesquisa.
     * @param value - tipo de pesquisa.
     */
    private void listProducts(String search, int value){
        countTotalProducts();
        tableColumnCodeProduct.setCellValueFactory(new PropertyValueFactory<>("code"));
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
                            alertCofirmation.setTitle("Editar Produto");
                            alertCofirmation.setHeaderText(null);
                            alertCofirmation.setContentText("Tem certeza que deseja editar esse produto ?");
                            Optional <ButtonType> actionDelete = alertCofirmation.showAndWait();
                            // Verificar se o botão Ok foi pressionado.
                            if(actionDelete.get() == ButtonType.OK){
                                try {
                                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/products/FXMLSystemInsertProduct.fxml"));
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
                            alertCofirmation.setTitle("Deletar Produto");
                            alertCofirmation.setHeaderText(null);
                            alertCofirmation.setContentText("Tem certeza que deseja deletar esse produto ?");
                            Optional <ButtonType> actionDelete = alertCofirmation.showAndWait();
                            if(actionDelete.get() == ButtonType.OK){
                                // Verificar se o botão Ok foi pressionado.
                                repositoryOfProducts.product = new Product(getTableView().getItems().get(getIndex()).getCode(),null,null,0,null);
                                String result = repositoryOfProducts.deleteDB();
                                if(!result.equals("DELETE")){
                                    alertInformation.setContentText("Produto Utilizado");
                                    alertInformation.showAndWait();
                                }
                                listProducts(search, value);
                            }
                        });
                        setGraphic(buttonUpdate);
                    }
                }
            };
            return cell;
        };
        tableColumnDelete.setCellFactory(cellFactoryDelete);
        tableProducts.setItems(repositoryOfProducts.readDB(search, value)); 
    }
    
    /**
    * Tela de Help de produtos.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        alertHelp.setGraphic(new ImageView(("/images/information-help-products.jpg")));
        alertHelp.showAndWait();
    }
}
