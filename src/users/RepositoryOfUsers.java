package users;

import interfaces.InterfaceCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por realizar o CRUD dos usuários.
 * @author Isaías de França Leite
 */
public class RepositoryOfUsers implements InterfaceCRUD {
    
    // Classe de usuários
    public User user;
    private PreparedStatement statement;
    private String sql;
    private String resultOperationDB = null;
    
    /**
     * Função responsável por inserir usuários.
     * @return - resultado da operação.
     */
    public String createDB(){
        sql = "INSERT INTO Usuarios (nomeUsuario, usuario, senha, permissao) VALUES (?,?,?,?)";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getTypeOfPermission());
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
     * Função responsável por listar usuários.
     * @return - resultado da operação. 
     */
    @Override
    public ObservableList readDB(String search, int value){
        ObservableList<User> listUser = FXCollections.observableArrayList();
        try {
            ResultSet rs = null;
            if(value == 0){
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Usuarios");
            }
            else{
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Usuarios WHERE codUsuario LIKE '"+search+"' OR nomeUsuario LIKE '%"+search+"%' OR usuario LIKE '"+search+"'");
            }
            while(rs.next()){
                listUser.add(new User(rs.getInt("codUsuario"),rs.getString("nomeUsuario"),rs.getString("usuario"),null,rs.getString("permissao")));
            }
        } 
        catch (SQLException ex) {
            return null;
        }
        return listUser;
    }
    
    /**
     * Função responsável por alterar usuários.
     * @return - resultado da operação.
     */
    public String updateDB(){
        sql = "UPDATE Usuarios SET nomeUsuario=?, usuario=?, senha=?, permissao=? WHERE codUsuario=?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(5, Integer.toString(user.getCode()));
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getTypeOfPermission());
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
     * Função responsável por deletar usuário.
     * @return - resultado da operação.
     */
    public String deleteDB(){
       sql = "DELETE FROM Usuarios WHERE codUsuario = ?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, Integer.toString(user.getCode()));
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
     * Função responsável por contar total de usuários.
     * @return - total de usuários.
     */
    public String countTotalUsers(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Usuarios");
        return result;
    }
   
    /**
     * Função responsável por contar total de usuários pesquisados.
     * @param search - valor pesquisado.
     * @return - total de usuários pesquisados.
     */
    public String countTotalUsers(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Usuarios WHERE codUsuario LIKE '"+search+"' OR nomeUsuario LIKE '%"+search+"%' OR usuario LIKE '"+search+"'");
        return result;
    }
}
