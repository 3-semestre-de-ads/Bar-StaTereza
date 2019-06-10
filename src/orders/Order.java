package orders;

import products.Product;

/**
 * Classe responsável por estabelecer os atributos e os metódos do pedido relacionado com os produtos.
 * @author Isaías de França Leite
 */
public class Order {
    private int code;
    private Product product;
    private String productName;
    private int amount;
    private double price;
    private double total;

    /**
     * Construtor de classe order.
     * @param code - o valor inicial de code.
     * @param productName - o nome do produto.
     * @param amount - o valor inicial de amount.
     * @param price - o preço do produto.
     * @param total - o valor total
     */
    public Order(int code, String productName, int amount, double price, double total) {    
        this.code = code;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.total = total;
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
     * retorna o valor de produto
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
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
     * retorna o valor de price.
     * @return - o valor de price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * inserir o valor de price.
     * @param price - o valor de price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * retorna o valor total.
     * @return - o valor total.
     */
    public double getTotal() {
        return total;
    }

    /**
     * inserir o valor total.
     * @param total - o valor total.
     */
    public void setTotal(double total) {
        this.total = total;
    } 
}
