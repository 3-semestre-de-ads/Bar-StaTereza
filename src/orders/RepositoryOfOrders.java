package orders;

import interfaces.InterfaceCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe Responsável por realizar os pedidos de produtos
 * @author Isaías de França Leite.
 */
public class RepositoryOfOrders implements InterfaceCRUD{
    
    private Order order;
    private PreparedStatement statement;
    private String sql;
    public String codeOrderPad;
    
    @Override
    public String createDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Função de listar pedidos de produtos.
     * @param search - o valor pesquisado.
     * @param value - 
     * @return - resultado da operação.
     */
    @Override
    public ObservableList readDB(String search, int value){
        ObservableList<Order> listOrder = FXCollections.observableArrayList();
        try {
            ResultSet rs = null;
            if(value == 0){
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT it.codItemPedido, pr.nomeProduto, it.qtdProduto, it.precoPedido, (it.precoPedido*it.qtdProduto) AS totalPedido FROM pedido p, itemPedido it, Produto pr where p.codPedido = it.codPedido and pr.codProduto = it.codProduto and p.codPedido = (select top 1 codPedido from Pedido where codComanda = "+codeOrderPad+" order by codPedido desc);");
            }
            else{
                rs = connectionDB.getConnection().createStatement().executeQuery("");
            }
            while(rs.next()){
               listOrder.add(new Order(rs.getInt("codItemPedido"),rs.getString("nomeProduto"),rs.getInt("qtdProduto"),rs.getDouble("precoPedido"), rs.getDouble("totalPedido")));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(RepositoryOfOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrder;
    }
    
    @Override
    public String updateDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Função de contar a quantidade de pedido na comanda.
     * @return - resultado da operação.
     */
    public String countTotalOrder(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM pedido p, itemPedido it, Produto pr where p.codPedido = it.codPedido and pr.codProduto = it.codProduto and p.codPedido = (select top 1 codPedido from Pedido where codComanda = "+search+" order by codPedido desc);");
        return result;
    }
    
    /**
     * Função de calcular valor total da comanda.
     * @return - resultado da operação.
     */
    public double totalOrder(){
        double result = Double.parseDouble(connectionDB.executeQuery("SELECT ISNULL(SUM(it.qtdProduto*it.precoPedido),0) FROM pedido p, itemPedido it, Produto pr where p.codPedido = it.codPedido and pr.codProduto = it.codProduto and p.codPedido = (select top 1 codPedido from Pedido where codComanda = "+codeOrderPad+" order by codPedido desc);"));
        return result;
    }
    
    /**
     * Função de mostra a mesa da comanda.
     * @return - resultado da operação.
     */
    public String searchTable(){
        String result = connectionDB.executeQuery("SELECT DISTINCT codMesa FROM pedido p where p.codPedido = (select top 1 codPedido from Pedido where codComanda = "+codeOrderPad+" order by codPedido desc);");
        return result;
    }
}
