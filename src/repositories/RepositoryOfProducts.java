package repositories;

import classes.ConnectionDB;
import classes.Product;
import interfaces.InterfaceCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por realizar o CRUD dos produtos.
 * @author Isaías de França Leite
 */
public class RepositoryOfProducts implements InterfaceCRUD{
    
    public Product product;
    private PreparedStatement statement;
    private String sql;
    
    /**
     *
     * @return
     */
    public String createDB(){
        sql = "INSERT INTO Produto (nomeProduto, catProduto, preco, descProduto) VALUES (?,?,?,?)";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getDescription());
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
     *
     * @param search
     * @param value
     * @return
     */
    public ObservableList readDB(String search, int value){
        ObservableList<Product> listProduct = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            if(value == 0){
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Produto");
            }
            else{
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Produto WHERE nomeProduto LIKE '"+search+"' OR catProduto LIKE '"+search+"' OR preco LIKE '"+search+"' OR descProduto LIKE '"+search+"'");
            }
            while(rs.next()){
                listProduct.add(new Product(rs.getInt("codProduto"),rs.getString("nomeProduto"),rs.getString("catProduto"),rs.getDouble("preco"),rs.getString("descProduto")));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(RepositoryOfProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }
    
     /**
     *
     * @return
     */
    public String deleteDB(){
       sql = "DELETE FROM Produto WHERE codProduto = ?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, Integer.toString(product.getCode()));
            int result = statement.executeUpdate();
            
            if(result == 1){
                return "DELETE";
            }
            else
                return "ERROR";
        } catch (SQLException ex) {
            return "ERROR: "+ex.toString();
        }
    }
    
    /**
     *
     * @return
     */
    public String countTotalProducts(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Produto");
        return result;
    }
    
        public String countTotalProducts(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Produto WHERE nomeProduto LIKE '"+search+"' OR catProduto LIKE '"+search+"' OR preco LIKE '"+search+"' OR descProduto LIKE '"+search+"'");
        return result;
    }

    @Override
    public String updateDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
