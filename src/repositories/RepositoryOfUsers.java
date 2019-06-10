package repositories;

import classes.ConnectionDB;
import classes.Game;
import classes.Reservation;
import classes.User;
import fxml.FXMLSystemGamesController;
import interfaces.InterfaceCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por realizar o CRUD dos usuários.
 * @author Isaías de França Leite
 */
public class RepositoryOfUsers implements InterfaceCRUD {
    
    
    public Reservation reservation;
    private PreparedStatement statement;
    private String sql;
    private String resultOperationDB = null;
    
    /**
     *
     * @return
     */
    public String createDB(){
        sql = "";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
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
     * @return
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
     *
     * @return
     */
    public String updateDB(){
        sql = "UPDATE Jogos SET nomeJogo=?, catJogo=?, descJogo=? WHERE codJogo=?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
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
     *
     * @return
     */
    public String deleteDB(){
       sql = "DELETE FROM Usuarios WHERE codUsuario = ?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, Integer.toString(reservation.getCode()));
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
    public String countTotalUsers(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Usuarios");
        return result;
    }
   
    public String countTotalUsers(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Usuarios WHERE codUsuario LIKE '"+search+"' OR nomeUsuario LIKE '%"+search+"%' OR usuario LIKE '"+search+"'");
        return result;
    }
}
