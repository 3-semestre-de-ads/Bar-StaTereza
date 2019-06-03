/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author isaia
 */
public class NewClass {
        // Informações da conexão com banco de dados.
    public Connection connection = null;
    private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String URL = "jdbc:sqlserver://COORD01:1433;databaseName=brasil;user=sa;password=123456";
    /**
     * Função resposável por estabelecer a conexão com banco de dados. 
     * @return - retornar a conexão ou um erro caso tenha um falha na comunicação com banco de dados.
     */
    public String getConnection() {
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            return "Conectou";
        }
        catch(ClassNotFoundException | SQLException erro){
            return null;
        }
    }
    
    public static void main(String[] args){
        NewClass n = new NewClass();
                n.getConnection();
    }
}
