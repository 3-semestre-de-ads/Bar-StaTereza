package games;

import interfaces.InterfaceCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por realizar o CRUD dos games.
 * @author Isaías de França Leite.
 */
public class RepositoryOfGames implements InterfaceCRUD {
    
    // Classe Game.
    public Game game;
    private PreparedStatement statement;
    private String sql;
    
    /**
     * Função de inserir jogos.
     * @return - valor do resultado da operação.
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
     * Função de listar jogos.
     * @return - valor do resultado da operação.
     */
    public ObservableList readDB(String search, int value){
        ObservableList<Game> listGame = FXCollections.observableArrayList();
        try {
            ResultSet rs = null;
            if(value == 0){
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Jogos");
            }
            else{
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Jogos WHERE codJogo LIKE '%"+search+"%' OR nomeJogo LIKE '%"+search+"%' OR catJogo LIKE '%"+search+"%' OR descJogo LIKE '%"+search+"%'");
            }
            while(rs.next()){
                listGame.add(new Game(rs.getInt("codJogo"),rs.getString("nomeJogo"),rs.getString("catJogo"),rs.getString("descJogo")));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(FXMLSystemGamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGame;
    }
    
    /**
     * Função alterar jogos.
     * @return - valor do resultado da operação.
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
     * Função de deletar jogos.
     * @return - valor do resultado da operação.
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
     * Função responsável contar a quantidade de jogos.
     * @return - quantiadade total de jogos.
     */
    public String countTotalGames(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Jogos");
        return result;
    }
    
    /**
     * Função responsável contar a quantidade de jogos de pesquisa.
     * @param search - valor pesquisado.
     * @return - quantidade de jogos pesquisados.
     */
    public String countTotalGames(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Jogos WHERE codJogo LIKE '"+search+"' OR nomeJogo LIKE '"+search+"' OR catJogo LIKE '"+search+"' OR descJogo LIKE '"+search+"'");
        return result;
    }
    
    /**
     * Função responsável contar a quantidade de jogos alocados.
     * @return - quantidade de jogos alocados.
     */
    public String countTotalGamesAllocated(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM JogoPedido");
        return result;
    }
    
    /**
     * Função responsável contar a quantidade de jogos alocados.
     * @return - quantidade de jogos alocados.
     */
    public String checkGamesAllocated(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM JogoPedido WHERE codJogo = "+search);
        return result;
    }
}
