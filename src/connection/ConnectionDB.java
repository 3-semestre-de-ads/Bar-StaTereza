package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Classe responsável por criar a conexão com banco de dados, 
 * excultar e verificar os comandos SQL forem realmente efetuados com sucesso.
 * 
 * @author Isaías de França Leite
 */
public class ConnectionDB {
    
    // Informações da conexão com banco de dados.
    public Connection connection = null;
    private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String URL = "jdbc:sqlserver://DESKTOP-E7UICTR\\ISAIAS:1433;databaseName=SystemDB;user=userSQL;password=2501";

    /**
     * Função resposável por estabelecer a conexão com banco de dados. 
     * @return - retornar a conexão ou um erro caso tenha um falha na comunicação com banco de dados.
     */
    public Connection getConnection() {
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            return connection;
        }
        catch(ClassNotFoundException | SQLException erro){
            return null;
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
     * Função resposável por consulta o banco de dados é retorna uma valor de acordo com sql.
     * @param sql - o valor do sql.
     * @return - o valor do resultado do sql.
     */
    public String executeQuery(String sql){
        String result = null;
        try{
            Statement statement = this.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                result = rs.getString(1);
            }
        }catch(SQLException erro){
            return "Erro: "+erro;
        }
        return result;
    }
}
