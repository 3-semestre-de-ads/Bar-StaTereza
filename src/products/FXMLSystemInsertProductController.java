package products;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe reponsável por definir todos atributos e metódos para inseir ou alterar produtos.
 * @author Isaías de França leite.
 */
public class FXMLSystemInsertProductController implements Initializable {
    
    // Instanciar RepositoryOfProducts.
    RepositoryOfProducts repositoryOfProducts = new RepositoryOfProducts();
    
    // Mensagem de status de informação 
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    
    // Mensagem de status de error
    Alert alertError = new Alert(Alert.AlertType.INFORMATION);
    
    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    
    // Campos necessários para inserir os dados no banco de dados.
    @FXML private TextField textFieldName;
    @FXML private ComboBox comboBoxCategory;
    private List<String> categoryProduct = new ArrayList<>();
    private ObservableList<String> listCategory;
    @FXML private TextField textFieldPrice;
    @FXML private TextArea textAreaDescription;
    @FXML private Button buttonTypeFunction;
    
    // Titulo do menu de acordo com a escolha da função.
    @FXML private Label titleTypeFunction;
    
    // Transição de tela
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    // Variáveis do constructor.
    private Product product;
    private String typeFunction;

    /**
     * O contructor da classe FXMLSystemInsertProductController.
     * @param product
     * @param typeFunction
     */
    public FXMLSystemInsertProductController(Product product, String typeFunction) {
        this.product = product;
        this.typeFunction = typeFunction;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listCategoryGame();
        operationsCreateAndUpdate();
    }
    
    /**
     * Transição de tela: tela de FXMLSystemProducts.
     * @throws IOException 
     */
    @FXML private void loadSceneProducts() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/products/FXMLSystemProducts.fxml");
    }
    
    /**
    * Tela de Help de inserir produtos.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        if(typeFunction.equals("C")){
            alertHelp.setGraphic(new ImageView(("/images/information-help-insertproduct.jpg")));
        }else{
            alertHelp.setGraphic(new ImageView(("/images/information-help-updateproduct.jpg")));
        }
        alertHelp.showAndWait();
    }
    
    /**
     * Listar todos os valores para o comboBox de categoria de produtos.
     */
    public void listCategoryGame(){
        categoryProduct.add("Bebidas");
        categoryProduct.add("Comidas");
        listCategory = FXCollections.observableArrayList(categoryProduct);
        comboBoxCategory.setItems(listCategory);
    } 

    /**
     * Função de inserir ou alterar produtos.
     */
    public void operationsCreateAndUpdate(){
        comboBoxCategory.getSelectionModel().selectFirst();
        if(typeFunction.equals("C")){
            titleTypeFunction.setText("ADICIONAR PRODUTO");
            buttonTypeFunction.setText("ADICIONAR");
            buttonTypeFunction.setOnAction((ActionEvent event) -> {
                if(true){
                    repositoryOfProducts.product =  new Product(0,textFieldName.getText(),comboBoxCategory.getValue().toString(),Double.parseDouble(textFieldPrice.getText()),textAreaDescription.getText());
                    String resultQuery = repositoryOfProducts.createDB();
                    // Verificar se os dados foram cadastrados.
                    if(resultQuery.equals("CREATE")){
                        alertInformation.setTitle("EDITAR");
                        alertInformation.setHeaderText(null);
                        alertInformation.setContentText("Dados cadastrados com sucesso");
                        alertInformation.showAndWait();
                    }
                    else{
                        alertError.setTitle("ERROR EDITAR");
                        alertError.setHeaderText(null);
                        alertError.setContentText(resultQuery);
                        alertError.showAndWait();
                    };
                    try {
                        loadSceneProducts();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSystemInsertProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    alertInformation.setTitle("ATENÇÃO");
                    alertInformation.setContentText("Preencha os campos obrigatórios!");
                    alertInformation.showAndWait();
                }
            });
        }
        else
        {
            titleTypeFunction.setText("EDITAR PRODUTO");
            buttonTypeFunction.setText("EDITAR");
            textFieldName.setText(product.getName());
            comboBoxCategory.setValue(product.getCategory());
            textFieldPrice.setText(Double.toString(product.getPrice()));
            textAreaDescription.setText(product.getDescription());
            buttonTypeFunction.setOnAction((ActionEvent event) -> {
                if(true){
                    repositoryOfProducts.product =  new Product(product.getCode(),textFieldName.getText(),comboBoxCategory.getValue().toString(),Double.parseDouble(textFieldPrice.getText()),textAreaDescription.getText());
                    String resultQuery = repositoryOfProducts.updateDB();
                    if(resultQuery.equals("UPDATE")){
                        alertInformation.setTitle("EDITAR");
                        alertInformation.setHeaderText(null);
                        alertInformation.setContentText("Dados alterados com sucesso");
                        alertInformation.showAndWait();
                    }
                    else{
                        alertError.setTitle("ERROR EDITAR");
                        alertError.setHeaderText(null);
                        alertError.setContentText(resultQuery);
                        alertError.showAndWait();
                    }
                    try {
                        loadSceneProducts();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSystemInsertProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    alertInformation.setTitle("ATENÇÃO");
                    alertInformation.setContentText("Preencha os campos obrigatórios!");
                    alertInformation.showAndWait();
                }
            });
        }
    }
}
