package interfaces;

/**
 * Interface responsável por estabelecer todos os metódos necessário para realizar o CRUD .
 * @author Isaías de França Leite
 */
public interface InterfaceCRUD {

    /**
     * Função responsável por inserir os dados no banco de dados
     */
    public String create();

    /**
     * Função responsável por listar os dados no banco de dados
     */
    public void read();
    
    /**
     * Função responsável por alterar os dados no banco de dados
     */
    public void update();
    
    /**
     * Função responsável por deletar os dados no banco de dados
     */
    public void delete();
}
