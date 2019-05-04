package classes;

import java.util.Date;

/**
 * Classe responsável por estabelecer os atributos e os metódos do pedido relacionado com os produtos.
 * @author Isaías de França Leite
 */
public class Order {
    private int code;
    private Command command;
    private Product product;
    private int amount;
    private String observation;
    private Date date;

    /**
     * Construtor de classe order.
     * @param code - o valor inicial de code.
     * @param command - o valor inicial de command.
     * @param product - o valor inicial de product.
     * @param amount - o valor inicial de amount.
     * @param observation - o valor inicial de observation.
     * @param date - o valor inicial de date
     */
    public Order(int code, Command command, Product product, int amount, String observation, Date date) {
        this.code = code;
        this.command = command;
        this.product = product;
        this.amount = amount;
        this.observation = observation;
        this.date = date;
    }

    /**
     * retorna o valor de code.
     * @return - o valor de code.
     */
    public int getCode() {
        return code;
    }

    /**
     * inserir o valor de code.
     * @param code - o valor de code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * retorna o valor de command.
     * @return - o valor de command.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * inserir o valor de command.
     * @param command - o valor de command.
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * retorna o valor de product.
     * @return - o valor de product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * inserir o valor de product.
     * @param product - o valor de product.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * retorna o valor de amount.
     * @return - o valor de amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * inserir o valor de amount.
     * @param amount - o valor de amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * inserir o valor de observation.
     * @return - o valor de observation.
     */
    public String getObservation() {
        return observation;
    }

    /**
     * inserir o valor de observation.
     * @param observation - o valor de observation.
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     * retorna o valor de date.
     * @return - o valor de date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * inserir o valor de date.
     * @param date - o valor de date.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
