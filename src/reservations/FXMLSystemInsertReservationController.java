package reservations;

import functions.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Classe responsável por definir os atributos e metódos necessários para inserir ou alterar reservas.
 * @author Isaías de França Leite
 */
public class FXMLSystemInsertReservationController implements Initializable {
    
    // Mensagem de status de error.
    Alert alertError = new Alert(Alert.AlertType.ERROR);
    
    // Mensagem de status de .
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);

    // HELP
    Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
    
    // Titulo do menu de acordo com a escolha da função.
    @FXML private Label titleTypeFunction;

    // Instancia do RepositoryOfReservation.
    RepositoryOfReservation repositoryOfReservation = new RepositoryOfReservation();
    
    // Campos necessários para inserir os dados no banco de dados.
    @FXML private ComboBox comboBoxTable;
    @FXML private TextField textFieldCustomer;
    @FXML private DatePicker datePickerDate;
    @FXML private TextArea textAreaObservation;
    @FXML private Button buttonTypeFunction;
   
    // Transição de tela.
    SceneChange sceneChange = new SceneChange();
    @FXML private AnchorPane anchorPane;
    @FXML private StackPane stackPane;
    
    // Variáveis do constructor.
    private Reservation reservation;
    private String typeFunction;

    /**
     * O contructor da classe FXMLSystemInsertReservationController.
     * @param reservation - um objeto do tipo Reservation.
     * @param typeFunction - tipo de função que a tela vai execultar.
     */
    public FXMLSystemInsertReservationController(Reservation reservation, String typeFunction) {
        this.reservation = reservation;
        this.typeFunction = typeFunction;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        operationsCreateAndUpdate();
    }
    
     /**
     * Transição da tela de inserir reservas para telas de lista reservas.
     * @throws IOException 
     */
    @FXML private void loadSceneReservation() throws IOException {
        sceneChange.sceneTransition(stackPane, anchorPane, "/reservations/FXMLSystemReservations.fxml");
    }
    
    /**
    * Tela de Help de reserva.
    */
    @FXML private void help(){
        alertHelp.setTitle("AJUDA");
        alertHelp.setHeaderText(" ");
        if(typeFunction.equals("C")){
            alertHelp.setGraphic(new ImageView(("/images/information-help-insertreservation.jpg")));
        }else{
            alertHelp.setGraphic(new ImageView(("/images/information-help-updatereservation.jpg")));
        }
        alertHelp.showAndWait();
    }
    
    /**
     * Função de inserir ou alterar reservas.
     */
    public void operationsCreateAndUpdate(){
        listTables();
        datePickerDate.setValue(LocalDate.now());
        if(typeFunction.equals("C")){
            titleTypeFunction.setText("ADICIONAR RESERVA");
            buttonTypeFunction.setText("ADICIONAR");
            buttonTypeFunction.setOnAction((ActionEvent event) -> {
                if(!"".equals(comboBoxTable.getValue()) && !"".equals(textFieldCustomer.getText())){
                    java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(datePickerDate.getValue());
                    repositoryOfReservation.reservation = new Reservation(0,Integer.parseInt(comboBoxTable.getValue().toString()),textFieldCustomer.getText(),gettedDatePickerDate,textAreaObservation.getText());
                    String resultQuery = repositoryOfReservation.createDB();
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
                        loadSceneReservation();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSystemInsertReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
            titleTypeFunction.setText("EDITAR RESERVA");
            buttonTypeFunction.setText("EDITAR");
            comboBoxTable.setValue(reservation.getCodeTable());
            textFieldCustomer.setText(reservation.getCustomer());
            LocalDate date = localeTime(reservation.getDate().toString());
            datePickerDate.setValue(date);
            textAreaObservation.setText(reservation.getObservation());
            buttonTypeFunction.setOnAction((ActionEvent event) -> {
                if(!"".equals(comboBoxTable.getValue()) && !"".equals(textFieldCustomer.getText())){
                    java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(datePickerDate.getValue());
                    repositoryOfReservation.reservation = new Reservation(reservation.getCode(),Integer.parseInt(comboBoxTable.getValue().toString()),textFieldCustomer.getText(),gettedDatePickerDate,textAreaObservation.getText());
                    String resultQuery = repositoryOfReservation.updateDB();
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
                        loadSceneReservation();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSystemInsertReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    /**
     * Função de Listar mesas no ComboBox.
     */
    private void listTables(){
        comboBoxTable.setItems(repositoryOfReservation.tables());
        comboBoxTable.getSelectionModel().selectFirst();
    }

    /**
     * Função de formatar datas.
     * @param timeString - valor da data.
     * @return
     */
    public static final LocalDate localeTime (String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(timeString, formatter);
        return localDate;
    }
}
