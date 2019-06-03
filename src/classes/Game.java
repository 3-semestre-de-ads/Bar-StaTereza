package classes;

/**
 * Classe responsável por estabelecer os atributos e os metódos do jogo.
 * @author Isaías de França Leite.
 */
public class Game {
    private int code;
    private String name;
    private String category;
    private String description;

    /**
     * Constructor da classe Game.
     * @param code - o valor inicial de code.
     * @param name - o valor inicial de name.
     * @param category - o valor inicial de category.
     * @param description - o valor inicial de description.
     */
    public Game(int code, String name, String category, String description) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    /**
     * Retorna o valor de code.
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
     * Retorna o valor de name.
     * @return - o valor de name.
     */
    public String getName() {
        return name;
    }

    /**
     * Inserir o valor de name.
     * @param name - o valor de name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o valor de category.
     * @return - o valor de category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Inserir o valor de category.
     * @param category - o valor de category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Retorna o valor de description.
     * @return - o valor de description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Inserir o valor de description.
     * @param description - o valor de description.
     */
    public void setDescription(String description) {
        this.description = description;
    }      
}
