package orderpads;

import products.RepositoryOfProducts;
import interfaces.InterfaceCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author isaia
 */
public class RepositoryOfOrderPad implements InterfaceCRUD{

    private OrderPad orderPad;
    private PreparedStatement statement;
    private String sql;
    
    
    @Override
    public String createDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList readDB(String search, int value) {
        ObservableList<OrderPad> listOrderPad = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            if(value == 0){
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Comanda");
            }
            else{
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Comanda");
            }
            while(rs.next()){
                listOrderPad.add(new OrderPad(rs.getInt("codComanda"), rs.getString("statusComanda")));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(RepositoryOfProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderPad;
    }

    @Override
    public String updateDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String countTotalOrderPadInUse(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Comanda WHERE statusComanda = 1");
        return result;
    }
    
        
    public String countTotalOrderPad(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Comanda");
        return result;
    }
    
}
