package classes;

import java.util.Date;

/**
 * Classe responsável por estabelecer os atributos e metódos da alocação de games.
 * @author Isaías de França Leite
 */
public class Allocation {
    private int code;
    private Command command;
    private Game game;
    private Date date;

    /**
     * Construtor da classe Allocation.
     * @param code - o valor inicial de code.
     * @param command - o valor inicial da classe command.
     * @param game - o valor inicial da classe game.
     * @param date - o valor inicial de date.
     */
    public Allocation(int code, Command command, Game game, Date date) {
        this.code = code;
        this.command = command;
        this.game = game;
        this.date = date;
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
     * Retorna o valor de command.
     * @return - o valor de command.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Inserir o valor de command.
     * @param command - o valor de command.
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Retorna o valor de command.
     * @return - o valor de command.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Inserir o valor de game.
     * @param game - o valor de game.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Retorna o valor de date.
     * @return - o valor de date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Inserir o valor de date.
     * @param date - o valor de date.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
}
