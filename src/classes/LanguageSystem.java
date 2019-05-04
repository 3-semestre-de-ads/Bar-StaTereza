package classes;

/**
 * Classe responsável por estabelecer todos atributos e metódos da língua do sistema.
 * @author Isaías de França Leite
 */
public class LanguageSystem {
    private int code;
    private String name;
    private String nation;

    /**
     * Constructor da classe LanguagemSystem.
     * @param code - o valor inicial de code.
     * @param name - o valor inicial de name.
     * @param nation - o valor inicial de nation.
     */
    public LanguageSystem(int code, String name, String nation) {
        this.code = code;
        this.name = name;
        this.nation = nation;
    }

    /**
     * Retorna o valor de code.
     * @return - valor de code.
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
     * @return - valor de name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o valor de name.
     * @param name - o valor de name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o valor de nation.
     * @return - o valor de nation.
     */
    public String getNation() {
        return nation;
    }

    /**
     * Inserir o valor de nation.
     * @param nation - o valor de nation.
     */
    public void setNation(String nation) {
        this.nation = nation;
    }
}
