package classes;

/**
 * Classe responsável por estabelecer todos atributos e metódos da comanda.
 * @author Isaías de França Leite.
 */
public class Command {
    private int code;
    private Table table;
    private double amount;
    private String status;

    /**
     * Constructor da classe Command.
     * @param code - valor inicial do code.
     * @param amount - valor inicial do amount.
     * @param status - o valor inicial do status.
     */
    public Command(int code, double amount, String status) {
        this.code = code;
        this.amount = amount;
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
     * Retorna o valor da table.
     * @return - o valor da table.
     */
    public Table getTable() {
        return table;
    }

    /**
     * Inserir o valor da table.
     * @param table - o valor da table.
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Retorna o valor do amount.
     * @return - valor do amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Inseir o valor do amount.
     * @param amount - o valor do amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
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
