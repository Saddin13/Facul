
package modelos;

import java.util.ArrayList;

public interface IVeiculoCRUD {
    void incluir(Veiculo veiculo) throws Exception;
    void alterar(Veiculo veiculo) throws Exception;
    ArrayList<Veiculo> obterListaDeVeiculos() throws Exception;
}