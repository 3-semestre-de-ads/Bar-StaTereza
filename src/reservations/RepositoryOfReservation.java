package reservations;

import connection.ConnectionDB;
import interfaces.InterfaceCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por realizar o CRUD das reservas.
 * @author Isaías de França Leite
 */
public class RepositoryOfReservation implements InterfaceCRUD{
    
    public Reservation reservation;
    private PreparedStatement statement;
    private String sql;
    private List<String> listTables = new ArrayList<>();
    
    /**
     *
     * @return
     */
    public String createDB(){
        sql = "INSERT INTO Reserva (codMesa, nomeCliente, dataReserva, observacao) VALUES (?,?,?,?)";
        try {
            System.out.println(reservation.getDate());
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, Integer.toString(reservation.getCodeTable()));
            statement.setString(2, reservation.getCustomer());
            java.sql.Date date = (java.sql.Date) reservation.getDate();
            statement.setDate(3, date);
            statement.setString(4, reservation.getObservation());
            int result = statement.executeUpdate();
            
            if(result == 1){
                return "CREATE";
            }
            else
                return "ERROR";
        } catch (SQLException ex) {
            return "ERROR: "+ex.toString();
        }
    }
    
    /**
     *
     * @return
     */
    public ObservableList readDB(String search, int value){
        ObservableList<Reservation> listReservation = FXCollections.observableArrayList();
        try {
            ResultSet rs = null;
            if(value == 0){
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Reserva");
            }
            else{
                rs = connectionDB.getConnection().createStatement().executeQuery("SELECT * FROM Reserva WHERE codMesa LIKE '%"+search+"%' OR nomeCliente LIKE '%"+search+"%' OR dataReserva LIKE '%"+search+"%' OR observacao LIKE '%"+search+"%'");
            }
            while(rs.next()){
               listReservation.add(new Reservation(rs.getInt("codReserva"),rs.getInt("codMesa"),rs.getString("nomeCliente"),rs.getDate("dataReserva"),rs.getString("observacao")));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(RepositoryOfReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservation;
    }
    
    /**
     *
     * @return
     */
    public String updateDB(){
        sql = "UPDATE Reserva SET codMesa = ?, nomeCliente = ?, dataReserva = ?, observacao = ? WHERE codReserva = ?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, Integer.toString(reservation.getCodeTable()));
            statement.setString(2, reservation.getCustomer());
            java.sql.Date date = (java.sql.Date) reservation.getDate();
            statement.setDate(3, date);
            statement.setString(4, reservation.getObservation());
            statement.setString(5, Integer.toString(reservation.getCode()));
            int result = statement.executeUpdate();
            
            if(result == 1){
                return "UPDATE";
            }
            else
                return "ERROR";
        } catch (SQLException ex) {
            return "ERROR: "+ex.toString();
        }
    }
        
    /**
     *
     * @return
     */
    public String deleteDB(){
       sql = "DELETE FROM Reserva WHERE codReserva = ?";
        try {
            statement = connectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, Integer.toString(reservation.getCode()));
            int result = statement.executeUpdate();
            
            if(result == 1){
                return "DELETE";
            }
            else
                return "ERROR";
        } catch (SQLException ex) {
            return "ERROR: "+ex.toString();
        }
    }
    
    /**
     *
     * @return
     */
    public ObservableList<String> tables(){
        ObservableList<String> observableListTables = FXCollections.observableArrayList();
        try {
            ResultSet rs = connectionDB.getConnection().createStatement().executeQuery("SELECT codMesa FROM Mesa");
           
            while(rs.next()){
               listTables.add(rs.getString("codMesa"));
            }
            observableListTables = FXCollections.observableArrayList(listTables);
        } 
        catch (SQLException ex) {
            return null;
        }
        return observableListTables;
    }
    
    /**
     *
     * @return
     */
    public String countTotalReservation(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Reserva");
        return result;
    }
    
    public String countTotalReservation(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Reserva WHERE codMesa LIKE '%"+search+"%' OR nomeCliente LIKE '%"+search+"%' OR dataReserva LIKE '%"+search+"%' OR observacao LIKE '%"+search+"%'");
        return result;
    }
    
    public String countTotalReservationToday(){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM Reserva WHERE dataReserva = CONVERT(date,GETDATE())");
        return result;
    }
    
}
