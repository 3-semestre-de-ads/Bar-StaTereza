package repositories;

import classes.ConnectionDB;
import interfaces.InterfaceCRUD;

/**
 * Classe responsável por realizar o CRUD das alocações dos games nas comandas.
 * @author Isaías de França Leite
 */
public class RepositoryOfAllocation implements InterfaceCRUD {
    
    private ConnectionDB connection = new ConnectionDB();
    
    @Override
    public String create() {
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
