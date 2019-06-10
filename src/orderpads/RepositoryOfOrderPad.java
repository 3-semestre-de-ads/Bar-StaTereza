package orderpads;

import products.RepositoryOfProducts;
import static interfaces.InterfaceCRUD.connectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por definir todos atributos e metódos da comanda.
 * @author Isaías de França Leite
 */
public class RepositoryOfOrderPad {

    private OrderPad orderPad;
    private PreparedStatement statement;
    private String sql;
    private List<String> listTables = new ArrayList<>();

    /**
     * Função de listar comandas.
     * @param search - valor pesquisado.
     * @param value - tipo de pesquisa.
     * @return - resultado da operação.
     */
    public ObservableList readDB(String search, int value) {
        ObservableList<OrderPad> listOrderPad = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            if(value == 0){
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Comanda");
            }
            else{
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Comanda WHERE codComanda ="+search);
            }
            while(rs.next()){
                listOrderPad.add(new OrderPad(rs.getInt("codComanda"), rs.getString("statusComanda")));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(RepositoryOfProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderPad;
    }
    
    /**
     * Função de vincular comanda na mesa (Criar um pedido).
     * @return - resultado da operação. 
     */
    public String addOrder(String codeTable, String codeOrderPad){
        sql = "insert into Pedido(codMesa,codComanda) values(?,?)";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, codeTable);
            statement.setString(2, codeOrderPad);
            int result = statement.executeUpdate();
            
            if(result == 1){
                return "CREATE";
            }
            else
                return "ERROR";
        } catch (SQLException ex) {
            return "ERROR: "+ex.toString();
        }
    }
    
    /**
     * Função de alterar o status da comanda para 1
     * @param string
     * @return - resultado da operação.
     */
    public String statusOrderPad(String codeOrderPad){
        sql = "update Comanda set statusComanda = 1 where codComanda = ?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, codeOrderPad);
            int result = statement.executeUpdate();
            
            if(result == 1){
                return "UPDATE";
            }
            else
                return "ERROR";
        } catch (SQLException ex) {
            return "ERROR: "+ex.toString();
        }
    }
    
        /**
     * Função de calcular o total de comanda.
     * @return - resultado da operação. 
     */
    public String countTotalOrderPadInUse(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Comanda WHERE statusComanda = 1");
        return result;
    }
    
    /**
     * Função de calcular o total de comanda.
     * @return - resultado da operação. 
     */
    public String countTotalOrderPad(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Comanda");
        return result;
    }
    
    /**
     * Função de calcular o total da comanda.
     * @param search - valor procurado.
     * @return - resultado da operação. 
     */
    public String countTotalOrderPad(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Comanda WHERE codComanda = "+search);
        return result;
    }
    
}
