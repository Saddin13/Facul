package modelos;

import java.util.ArrayList;

public interface IVinculoCarroAcessorioCRUD {
    void incluir(VinculoCarroAcessorio vinculo) throws Exception;
    void alterar(VinculoCarroAcessorio vinculo) throws Exception;
    ArrayList<VinculoCarroAcessorio> obterListaDeVinculos() throws Exception;
}