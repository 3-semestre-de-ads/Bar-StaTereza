package reports;

import connection.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por calcular todas as regras de relatório do sistema.
 * @author Isaáias de França Leite.
 */
public class Report {
        
    // Classe de conexão.
    public ConnectionDB connectionDB = new ConnectionDB();
    
    /**
     * Função responsável de listar status do sistema.
     * @param value - tipo de retorno.
     * @return - quantiadade do tipo de retorno.
     */
    public String reportCountSQL(int value){
        String result =  null;
        switch (value) {
            case 1:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Produto;");
                break;
            case 2:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Jogos;");
                break;
            case 3:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Jogos j, JogoPedido jp WHERE j.codJogo = jp.codJogo");
                break;
            case 4:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Jogos j, JogoPedido jp WHERE j.codJogo != jp.codJogo");
                break;
            case 5:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Comanda;");
                break;
            case 6:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Comanda WHERE statusComanda = 1;");
                break;
            case 7:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Comanda WHERE statusComanda = 0;");
                break;
            case 8:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Mesa");
                break;
            case 9:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Mesa m, Comanda c, pedido p WHERE p.codMesa = m.codMesa AND p.codComanda = c.codComanda AND c.statusComanda = 1");
                break;
            case 10:
                result = connectionDB.executeQuery("DECLARE @TotalMesa INT; SET @TotalMesa = (SELECT COUNT(*) FROM Mesa); DECLARE @totalMesaOcupadas INT; SET @totalMesaOcupadas = (SELECT COUNT(*) FROM Mesa m, Comanda c, pedido p WHERE p.codMesa = m.codMesa AND p.codComanda = c.codComanda AND c.statusComanda = 1) SELECT @TotalMesa-@totalMesaOcupadas;");
                break;
            case 11:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Reserva;");
                break;
            case 12:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Reserva WHERE dataReserva = CONVERT(date,GETDATE());");
                break;
            case 13:
                result = connectionDB.executeQuery("SELECT COUNT(*) FROM Usuarios");
                break;
            default:
                break;
        }
        return result;
    }
    
    /**
     * Função responsável por retorna o valor total de vendas de acordo com tipo de data.
     * @param type - tipo de valor da data.
     * @param date - data.
     * @return - retorna uma tabela com todos resultados encontrado.
     */
    public double totalSales(String type, String date){
        double result = Double.parseDouble(connectionDB.executeQuery("SELECT ISNULL(SUM(itp.qtdProduto*itp.precoPedido),0) FROM pedido p, ItemPedido itp, Produto pr WHERE p.codPedido = itp.codPedido AND itp.codProduto = pr.codProduto AND datediff("+type+", itp.dataPedido,'"+date+"')=0"));
        if(result == 0){
            result = 0;
        }
        return result;
    }
    
    /**
     * Função responsável por calcular valor total de vendas de produtos de acordo com uma determinada data.
     * @param type - tipo de valor da data.
     * @param date - data.
     * @return - retorna uma tabela com todos resultados encontrado.
     * @throws SQLException
     */
    public ResultSet totalSalesOfProducts(String type, String date) throws SQLException{
        ResultSet rs = null;
        rs = connectionDB.getConnection().createStatement().executeQuery("SELECT pr.nomeProduto AS Produto, SUM(itp.precoPedido*itp.qtdProduto) AS TOTAL FROM ItemPedido itp, Produto pr WHERE pr.codProduto = itp.codProduto AND dateDiff("+type+",itp.dataPedido,'"+date+"') = 0 GROUP BY pr.nomeProduto ORDER BY TOTAL DESC");
        return rs;
    }
}
