package repositories;

import classes.ConnectionDB;

/**
 *
 * @author ISaías de França Leite
 */
public class RepositoryOfReports {
    
    public ConnectionDB connectionDB = new ConnectionDB();
    
    /**
     * Função responsável por retorna o valor total de vendas de acordo com tipo de data.
     * @param type
     * @param data
     * @return
     */
    public double totalSalesValue(String type, String date){
        double result = Double.parseDouble(connectionDB.executeQuery("SELECT ISNULL(SUM(pr.preco*itp.qtdProduto),0) FROM pedido p, ItemPedido itp, Produto pr WHERE p.codPedido = itp.codPedido AND itp.codProduto = pr.codProduto AND datediff("+type+", itp.dataPedido,'"+date+"')=0"));
        if(result == 0){
            result = 0;
        }
        return result;
    }
}
