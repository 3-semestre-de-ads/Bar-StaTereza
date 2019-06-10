package classes;

import java.util.Date;

/**
 * Classe responsável por estabelecer os atributos e metódos das reservas.
 * @author Isaías de França Leite
 */
public class Reservation {
    private int code;
    private int codeTable;
    private Table table = new Table();
    private String customer;
    private Date date;
    private String observation;
    private String status;

    /**
     * Construtor da classe reservation.
     * @param code - o valor inicial de code.
     * @param codeTable - o valor da table.
     * @param customer - o valor inicial de customer.
     * @param date - o valor inicial de date.
     * @param observation - o valor inicial de observation.
     */
    public Reservation(int code, int codeTable, String customer, Date date, String observation) {
        this.code = code;
        this.table.setCode(codeTable);
        this.codeTable = table.getCode();
        this.customer = customer;
        this.date = date;
        this.observation = observation;
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
     *
     * @return
     */
    public int getCodeTable() {
        return codeTable;
    }

    /**
     *
     * @param codeTable
     */
    public void setCodeTable(int codeTable) {
        this.codeTable = codeTable;
    }
    
    /**
     * retorna o valor de customer.
     * @return - valor de customer.
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * inserir o valor de customer.
     * @param customer - o valor de customer.
     */
    public void setCustomer(String customer) {
        this.customer = customer;
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

    /**
     * retorna o valor de observation.
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
     * retorna o valor de status.
     * @return - o valor de status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * inserir o valor de status.
     * @param status - o valor de status.
     */
    public void setStatus(String status) {
        this.status = status;
    }   
}
