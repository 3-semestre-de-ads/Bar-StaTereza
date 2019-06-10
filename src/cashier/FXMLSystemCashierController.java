package cashier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Classe responsável por estabelecer a conexão entre a classe Cashier e a interface de gráfica de cashier.
 * @author Isaías de França Leite
 */
public class FXMLSystemCashierController implements Initializable {
    
    // Botões
    @FXML Button btnMoney;
    @FXML Button btnCredit;
    @FXML Button btnDebit;
    
    // Classe do caixa
    Cashier cashier = new Cashier();
    
    // Comanda
    @FXML private TextField orderPad;
    
    // Total da comanda
    @FXML private Text totalOrderPad;
    
    // Troco
    @FXML VBox vbox;
    @FXML Text textChange;
    @FXML TextField textFieldMoney;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       calculateOderPad();
       vbox.setVisible(false);
    } 
    
    /**
     * Função responsável por calcular todos itens da comanda.
     */
    @FXML private void calculateOderPad(){
        totalOrder();
    }
    
    /**
     * Função de calcular valor total da comanda.
     */
    private void totalOrder(){
        if(!"".equals(orderPad.getText())){
            totalOrderPad.setText("R$: "+String.format("%.2f", cashier.totalOrder(orderPad.getText())));
        }
        else{
            totalOrderPad.setText("R$: 0,00");
        }
    }
      
    /**
     * Função de calcular pagamento a dinheiro.
     */
    @FXML private void btnMoney(){
        vbox.setVisible(true);
        btnMoney.setStyle("-fx-text-fill: #51E775; -fx-background-color:  #000000;");
        btnCredit.setStyle("");
        btnDebit.setStyle("");
    }
    
    /**
     * Função de calcular troco.
     */
    @FXML private void calculeChange(){
    }
    
    /**
     * Função de calcular pagamento crédito.
     */
    @FXML private void btnCredit(){
        vbox.setVisible(false);
        btnMoney.setStyle("");
        btnCredit.setStyle("-fx-text-fill: #76C8FF; -fx-background-color:  #000000;");
        btnDebit.setStyle("");
    }
    
    /**
     * Função de calcular pagamento debito.
     */
    @FXML private void btnDebit(){
        vbox.setVisible(false);
        btnMoney.setStyle("");
        btnCredit.setStyle("");
        btnDebit.setStyle("-fx-text-fill: #F5F983; -fx-background-color:  #000000;");
    }  
}
