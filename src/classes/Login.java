/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por estabelecer todos atributos e metódos para realizar login dos usuários.
 * @author Isaías de França Leite.
 */
public class Login{
    
   private ConnectionDB connectionDB = new ConnectionDB();
   public User user = new User(); 
           
    /**
     * Função responsável por verificar o usuário está cadastrado no banco de dados.
     * @return - retorna 1 caso tenha usuário cadastrado ou 0 caso não tenha.
     */
    public String verificationOfUser(){
       String result = connectionDB.executeQuery("SELECT COUNT(*) FROM usuarios u WHERE u.usuario = '"+user.getUsername()+"' AND u.senha = '"+user.getPassword()+"'");
       ResultSet rs;
       try {
           rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM usuarios u WHERE u.usuario = '"+user.getUsername()+"' AND u.senha = '"+user.getPassword()+"'");
          while(rs.next()){
               user = new User(rs.getInt("codUsuario"),rs.getString("nomeUsuario"),rs.getString("usuario"),rs.getString("senha"),rs.getString("permissao"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       }
       return result;
   }
}
