package report;

import classes.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por calcular todas as regras de relatório do sistema.
 * @author Isaáias de França Leite.
 */
public class Report {
        
    public ConnectionDB connectionDB = new ConnectionDB();
    
    /**
     * Função responsável por retorna o valor total de vendas de acordo com tipo de data.
     * @param type - tipo de valor da data.
     * @param date - data.
     * @return - retorna uma tabela com todos resultados encontrado.
     */
    public double totalSales(String type, String date){
        double result = Double.parseDouble(connectionDB.executeQuery("SELECT ISNULL(SUM(pr.preco*itp.qtdProduto),0) FROM pedido p, ItemPedido itp, Produto pr WHERE p.codPedido = itp.codPedido AND itp.codProduto = pr.codProduto AND datediff("+type+", itp.dataPedido,'"+date+"')=0"));
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
