package interfaces;

import connection.ConnectionDB;
import javafx.collections.ObservableList;

/**
 * Interface responsável por estabelecer todos os metódos necessário para realizar o CRUD .
 * @author Isaías de França Leite
 */
public interface InterfaceCRUD {
    
    public ConnectionDB connectionDB = new ConnectionDB();
    
    /**
     * Função responsável por inserir os dados no banco de dados
     * @return 
     */
    public String createDB();
    /**
     * Função responsável por listar os dados no banco de dados
     * @param search
     * @param value
     * @return 
     */
    public ObservableList readDB(String search, int value);
    /**
     * Função responsável por alterar os dados no banco de dados
     * @return 
     */
    public String updateDB();
   /**
     * Função responsável por deletar os dados no banco de dados
     * @return 
     */
    public String deleteDB();
}
