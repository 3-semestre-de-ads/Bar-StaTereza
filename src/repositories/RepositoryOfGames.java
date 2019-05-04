package repositories;

import classes.ConnectionDB;
import interfaces.InterfaceCRUD;

/**
 * Classe responsável por realizar o CRUD dos games.
 * @author Isaías de França Leite
 */
public class RepositoryOfGames implements InterfaceCRUD {
    
    private ConnectionDB connection = new ConnectionDB();
    
    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
