package classes;

/**
 * Classe responsável por estabelecer todos atributos e metódos da comanda.
 * @author Isaías de França Leite.
 */
public class OrderPad {
    private int code;
    private String status;

    /**
     * Constructor da classe Command.
     * @param code - valor inicial do code.
     * @param status - o valor inicial do status.
     */
    public OrderPad(int code, String status) {
        this.code = code;
        this.status = status;
    }

    /**
     * Retorna o valor do code.
     * @return - valor do code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Inserir o valor do code.
     * @param code - o valor do code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Retorna o valor do status.
     * @return - o valor do status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Inseir o valor do status.
     * @param status - valor do status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
