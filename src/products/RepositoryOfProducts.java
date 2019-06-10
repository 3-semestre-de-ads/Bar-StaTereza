package products;

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
    
    // Classe de produto.
    public Product product;
    private PreparedStatement statement;
    private String sql;
    
    /**
     * Função responsável por inserir produtos.
     * @return - resultado da operação.
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
     * Função responsável por listar produtos.
     * @param search - valor de pesquisa.
     * @param value - tipo de pesquisa.
     * @return - resultado da operação.
     */
    public ObservableList readDB(String search, int value){
        ObservableList<Product> listProduct = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            if(value == 0){
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Produto");
            }
            else{
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Produto WHERE codProduto LIKE '"+search+"' OR nomeProduto LIKE '"+search+"' OR catProduto LIKE '"+search+"' OR preco LIKE '"+search+"' OR descProduto LIKE '"+search+"'");
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
     * Função responsável por alterar produtos.
     * @return - resultado da operação.
     */
    @Override
    public String updateDB() {
        sql = "UPDATE Produto SET nomeProduto=?, catProduto=?, preco=?, descProduto=? WHERE codProduto = ?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(5, Integer.toString(product.getCode()));
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setString(3, Double.toString(product.getPrice()));
            statement.setString(4, product.getDescription());
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
     * Função responsável por deletar produtos.
     * @return - resultado da operação.
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
     * Função responsável por contar quantidade total de produtos cadastrados.
     * @return - total de produtos.
     */
    public String countTotalProducts(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Produto");
        return result;
    }
    
    /**
     * Função responsável por contar quantidade de produtos pesquisados. 
     * @param search - valor de pesquisado.
     * @return - total de produtos pesquisados.
     */
    public String countTotalProducts(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Produto WHERE codProduto LIKE '"+search+"' OR nomeProduto LIKE '"+search+"' OR catProduto LIKE '"+search+"' OR preco LIKE '"+search+"' OR descProduto LIKE '"+search+"'");
        return result;
    }
}
