package users;

import functions.SceneChange;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe responsável por definir todos os atributos e metódos necessários inserir ou alterar usuários.
 * @author Isaías de França Leite.
 */
public class FXMLSystemInsertUserController implements Initializable {
    
    // Instancia do RepositoryOfUsers.
    RepositoryOfUsers repositoryOfUsers = new RepositoryOfUsers();
    
    // Mensagem de status de error.
    Alert alertError = new Alert(Alert.AlertType.ERROR);
    
    // Mensagem de status de informação 
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    
    // Titulo do menu de acordo com a escolha da função.
    @FXML private Label titleTypeFunction;
    
    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    
    // Campos necessários para inserir os dados no banco de dados.
    @FXML private TextField textFieldName;
    @FXML private TextField textFieldusername;
    @FXML private Button buttonTypeFunction;
    
    // TypeOfPermission
    private String permission = "B";
    @FXML private ComboBox comboBoxTypeOfPermission;
    private List<String> listTypeOfPermission = new ArrayList<>();
    ObservableList<String> observableListTypeOfPermission = FXCollections.observableArrayList();
    
    // Transição de tela
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    //
    private User user;
    private String typeFunction;
    
    /**
     * Constructor de FXMLSystemInsertUserController.
     * @param user - valor de usuário
     * @param typeFunction - tipo de função.
     */
    public FXMLSystemInsertUserController(User user, String typeFunction) {
        this.user = user;
        this.typeFunction = typeFunction;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeOfPermission();
        try {
            operationsCreateAndUpdate();
        } catch (Exception ex) {
            Logger.getLogger(FXMLSystemInsertUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    /**
     * Transição de tela: tela de FXMLSystemUsers.
     * @throws IOException 
     */
    @FXML private void loadSceneUsers() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/users/FXMLSystemUsers.fxml");
    }
    
    /**
    * Tela de Help de inserir usuários.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        if(typeFunction.equals("C")){
            alertHelp.setGraphic(new ImageView(("/images/information-help-insertuser.jpg")));
        }else{
            alertHelp.setGraphic(new ImageView(("/images/information-help-updateuser.jpg")));
        }
        alertHelp.showAndWait();
    }
    
    /**
     * Filtro de tipos de usuários
     */
    private void typeOfPermission(){
        listTypeOfPermission.add("COLABORADOR");
        listTypeOfPermission.add("ADMINISTRADOR");
        observableListTypeOfPermission = FXCollections.observableArrayList(listTypeOfPermission); 
        comboBoxTypeOfPermission.setItems(observableListTypeOfPermission);
        comboBoxTypeOfPermission.getSelectionModel().selectFirst();
        comboBoxTypeOfPermission.setOnAction(eventFilter);
    }
    
    /**
     * Seleção de evento.
     */
    private EventHandler<ActionEvent> eventFilter = 
            (ActionEvent e) -> {
        if(comboBoxTypeOfPermission.getValue().toString().equals("COLABORADOR")){
            permission = "B";
        }else{
            permission = "A";
        }
    };
    
    /**
     * Função responsável por inserir ou alterar usuários.
     * @throws java.lang.Exception
     */
    public void operationsCreateAndUpdate() throws Exception{
        if(typeFunction.equals("C")){
            titleTypeFunction.setText("ADICIONAR USUÁRIO");
            buttonTypeFunction.setText("ADICIONAR");
            buttonTypeFunction.setOnAction((ActionEvent event) -> {
                if(!"".equals(textFieldName.getText()) && !"".equals(textFieldusername.getText())){
                    MessageDigest md;
                    String senhaHex = null;
                    String username = textFieldusername.getText();
                    try {
                        md = MessageDigest.getInstance("SHA-256");
                        byte messageDigest[] = md.digest(username.getBytes("UTF-8"));
                        StringBuilder sb = new StringBuilder();
                        for(byte b: messageDigest){
                            sb.append((String.format("%02X", 0xFF & b)));
                        }
                         senhaHex = sb.toString();
                         repositoryOfUsers.user = new User(0, textFieldName.getText(), textFieldusername.getText(), senhaHex, permission);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(FXMLSystemInsertUserController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(FXMLSystemInsertUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String resultQuery = repositoryOfUsers.createDB();
                    // Verificar se os dados foram cadastrados.
                    if(resultQuery.equals("CREATE")){
                        alertInformation.setTitle("Error de Cadastro");
                        alertInformation.setHeaderText(null);
                        alertInformation.setContentText("Dados cadastrados com sucesso");
                        alertInformation.showAndWait();
                    }
                    else{
                        alertError.setContentText(resultQuery);
                        alertError.showAndWait();
                    }
                    try {
                        loadSceneUsers();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSystemInsertUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    alertInformation.setTitle("ATENÇÃO");
                    alertInformation.setContentText("Preencha os campos obrigatórios!");
                    alertInformation.showAndWait();
                }
            });
        }
        else
        {
            titleTypeFunction.setText("EDITAR USUÁRIO");
            buttonTypeFunction.setText("EDITAR");
            textFieldName.setText(user.getName());
            textFieldusername.setText(user.getUsername());
            if(user.getTypeOfPermission().equals("B")){
                permission = "B";
                comboBoxTypeOfPermission.getSelectionModel().select("COLABORADOR");
            }
            else{
                permission = "A";
                comboBoxTypeOfPermission.getSelectionModel().select("ADMINISTRADOR");
            }
            buttonTypeFunction.setOnAction((ActionEvent event) -> {
                if(!"".equals(textFieldName.getText()) && !"".equals(textFieldusername.getText())){
                                        MessageDigest md;
                    String senhaHex = null;
                    String username = textFieldusername.getText();
                    try {
                        md = MessageDigest.getInstance("SHA-256");
                        byte messageDigest[] = md.digest(username.getBytes("UTF-8"));
                        StringBuilder sb = new StringBuilder();
                        for(byte b: messageDigest){
                            sb.append((String.format("%02X", 0xFF & b)));
                        }
                         senhaHex = sb.toString();
                         repositoryOfUsers.user = new User(user.getCode(), textFieldName.getText(), textFieldusername.getText(), senhaHex, permission);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(FXMLSystemInsertUserController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(FXMLSystemInsertUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String resultQuery = repositoryOfUsers.updateDB();
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
                        loadSceneUsers();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSystemInsertUserController.class.getName()).log(Level.SEVERE, null, ex);
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
