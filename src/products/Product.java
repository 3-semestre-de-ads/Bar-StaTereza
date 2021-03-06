package products;

/**
 * Classe responsável por estabelecer os atributos e metódos de produtos.
 * @author Isaías de França Leite
 */
public class Product {
    private int code;
    private String name;
    private String category;
    private double price;
    private String description;

    /**
     * Construtor da classe product.
     * @param code - o valor inicial de code.
     * @param name - o valor inicial de name.
     * @param category - o valor inicial de category.
     * @param price - o valor inicial de price.
     * @param description - o valor inicial de description.
     */
    public Product(int code, String name, String category, double price, String description) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public Product(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * retorna o valor de name.
     * @return - o valor de name.
     */
    public String getName() {
        return name;
    }

    /**
     * inserir o valor de name.
     * @param name - o valor de name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * retorna o valor de category.
     * @return - o valor de category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * inserir o valor de category.
     * @param category - o valor de category.
     */
    public void setCategory(String category) {
        this.category = category;
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
     * retorna o valor de description.
     * @return - o valor de description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * inserir o valor de description.
     * @param description - o valor de description.
     */
    public void setDescription(String description) {
        this.description = description;
    } 
}
