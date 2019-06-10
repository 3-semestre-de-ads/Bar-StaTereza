package cashier;

import static interfaces.InterfaceCRUD.connectionDB;

/**
 * Classe responsável por definir os atributos e metódos necessários do caixa.
 * @author Isaías de França Leite.
 */
public class Cashier{
    
    /**
     * Função de contar a quantidade de pedido na comanda.
     * @return - resultado da operação.
     */
    public String countTotalOrder(String search){
        String result = connectionDB.executeQuery("SELECT COUNT(*) FROM pedido p, itemPedido it, Produto pr where p.codPedido = it.codPedido and pr.codProduto = it.codProduto and p.codPedido = (select top 1 codPedido from Pedido where codComanda = "+search+" order by codPedido desc);");
        return result;
    }
    
    /**
     * Função de calcular valor total da comanda.
     * @param search - valor de busca.
     * @return - resultado da operação.
     */
    public double totalOrder(String search){
        double result = Double.parseDouble(connectionDB.executeQuery("SELECT ISNULL(SUM(it.qtdProduto*it.precoPedido),0) FROM pedido p, itemPedido it, Produto pr where p.codPedido = it.codPedido and pr.codProduto = it.codProduto and p.codPedido = (select top 1 codPedido from Pedido where codComanda = "+search+" order by codPedido desc);"));
        return result;
    }
}
