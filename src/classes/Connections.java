package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Connections {
    
    public static void main(String[] args){
        final String DRIVER = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://localhost:3306/mysql";

        try{
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, "root", "");
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso");
        }
        catch (ClassNotFoundException erro){
            JOptionPane.showMessageDialog(null, "Drive não encontrado! \n " + erro.toString());
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados \n " + erro.toString());
        }
        
      
    }
}
