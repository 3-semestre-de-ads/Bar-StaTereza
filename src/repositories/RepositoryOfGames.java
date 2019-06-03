package repositories;

import classes.ConnectionDB;
import classes.Game;
import fxml.FXMLSystemGamesController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por realizar o CRUD dos games.
 * @author Isaías de França Leite
 */
public class RepositoryOfGames {
    
    public Game game;
    private ConnectionDB connectionDB = new ConnectionDB();
    private PreparedStatement statement;
    private String sql;
    private String resultOperationDB = null;
    
    /**
     *
     * @return
     */
    public String createDB(){
        sql = "INSERT INTO Jogos (nomeJogo, catJogo, descJogo) VALUES (?,?,?)";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, game.getName());
            statement.setString(2, game.getCategory());
            statement.setString(3, game.getDescription());
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
    public ObservableList readDB(){
        ObservableList<Game> listGame = FXCollections.observableArrayList();
        try {
            ResultSet rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Jogos");
            
            while(rs.next()){
                listGame.add(new Game(rs.getInt("codJogo"),rs.getString("nomeJogo"),rs.getString("catJogo"),rs.getString("descJogo")));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(FXMLSystemGamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGame;
    }
    
    /**
     *
     * @return
     */
    public String updateDB(){
        sql = "UPDATE Jogos SET nomeJogo=?, catJogo=?, descJogo=? WHERE codJogo=?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(4, Integer.toString(game.getCode()));
            statement.setString(1, game.getName());
            statement.setString(2, game.getCategory());
            statement.setString(3, game.getDescription());
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
       sql = "DELETE FROM Jogos WHERE codJogo = ?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, Integer.toString(game.getCode()));
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
    public String countTotalGames(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Jogos");
        return result;
    }
    
    public String countTotalGamesAllocated(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM JogoPedido");
        return result;
    }
}
