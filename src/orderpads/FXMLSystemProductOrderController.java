/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderpads;

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
import products.FXMLSystemInsertProductController;
import products.FXMLSystemProductsController;
import products.Product;
import products.RepositoryOfProducts;

/**
 * Classe responsável por definir os atributos e metódos para adicionar produtos na comanda.
 * @author Isaías de França Leite.
 */
public class FXMLSystemProductOrderController implements Initializable {
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
        tableProducts.setItems(repositoryOfProducts.readDB(search, value)); 
    }  
}
