package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por criar a conexão com banco de dados, 
 * excultar e verificar os comandos SQL forem realmente efetuados com sucesso.
 * 
 * @author Isaías de França Leite
 */
public class ConnectionDB {
    
    // Informações da conexão com banco de dados.
    public Connection connection = null;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/mysql";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    
    /**
     * Função resposável por estabelecer a conexão com banco de dados. 
     * @return - retornar a conexão ou um erro caso tenha um falha na comunicação com banco de dados.
     */
    public String getConnection() {
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return "Connection Established";
        }
        catch(ClassNotFoundException | SQLException erro){
            return "Connection error: "+erro.toString();
        }
    }

    /**
     * Função responsável por fechar a conexão com banco de dados.
     * @return - retorna a conexão fechada ou um erro que impossibilitar fechar a conexão.
     */
    public String closeConnection()
    {
        try{
            connection.close();
            return "Connection closed";
        }catch(SQLException erro)
        {
            return "Connection error: "+erro.toString();
        }
    }
    
    /**
     * Função responsável por execultar os comandos SQL.
     * @param SQL - O comando SQL.
     * @return - retorna o resultado do comando SQL.
     */
    public ResultSet executeSQLCommand(String SQL){
        
        try{
            this.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        }
        catch(SQLException erro){
        }
        return null;
    }
}
