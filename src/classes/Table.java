package classes;

/**
 * Classe responsável por estabelecer os atributos e os metódos da mesa.
 * @author Isaías de França Leite.
 */
public class Table {
    private int code;
    private String status;

     public Table(){}
    /**
     * Constructor da classe Table.
     * @param code - o valor inicial de code.
     * @param status - o valor inicial de status.
     */
    public Table(int code, String status) {
        this.code = code;
        this.status = status;
    }

    /**
     * retorna o valor de code.
     * @return - o valor de code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Inserir o valor de code.
     * @param code - o valor de code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Retorna o valor de status.
     * @return - o valor de status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Inserir o valor de status.
     * @param status - o valor de status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
