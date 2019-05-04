package repositories;

import classes.LanguageSystem;

/**
 * Classe responsável por definir os todos os textos de cada lingua definida no sistema.
 * @author Isaías de França Leite
 */
public class RepositoryOfLanguage {
    private int defaultLanguage;
    public LanguageSystem english = new LanguageSystem(01,"English","USA");
    public LanguageSystem portuguese = new LanguageSystem(02,"Português","Brasil");

    /**
     * retorna o valor de defaultLanguage.
     * @return - o valor de defaultLanguage.
     */
    public int getDefaultLanguage() {
        return defaultLanguage;
    }

    /**
     * Inserir o valor de defaultLanguage.
     * @param defaultLanguage - o valor de defaultLanguage.
     */
    public void setDefaultLanguage(int defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }
}
