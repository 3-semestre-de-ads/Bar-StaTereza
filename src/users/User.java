package users;

/**
 * Classe responsável por estabelecer os atributos e os metódos dos usuários.
 * @author Isaías de França Leite.
 */
public class User {
    private int code;
    private String name;
    private String username;
    private String password;
    private String typeOfPermission;

    /**
     * Constructor da classe User.
     */
    public User(){}
    
    /**
     * Constructor da classe User.
     * @param code - o valor inicial de code.
     * @param name - o valor inicial de name.
     * @param username - o valor inicial de username.
     * @param password - o valor inicial de password.
     * @param typeOfPermission
     */
    public User(int code, String name, String username, String password, String typeOfPermission) {
        this.code = code;
        this.name = name;
        this.username = username;
        this.password = password;
        this.typeOfPermission = typeOfPermission;
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
     * Retorna o valor de username.
     * @return - o valor de username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Inserir o valor de username.
     * @param username - o valor de username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retorna o valor de password.
     * @return - o valor de password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Inserir o valor de password.
     * @param password - o valor de password.
     */
    public void setPassword(String password) {
        this.password = password;
    } 

    /**
     * Retorna o valor de typeOfPermission.
     * @return
     */
    public String getTypeOfPermission() {
        return typeOfPermission;
    }

    /**
     * Inserir o valor de typeOfPermission.
     * @param typeOfPermission
     */
    public void setTypeOfPermission(String typeOfPermission) {
        this.typeOfPermission = typeOfPermission;
    }
}
