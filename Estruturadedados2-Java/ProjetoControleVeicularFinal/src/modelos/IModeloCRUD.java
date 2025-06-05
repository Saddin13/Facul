package modelos;

import java.util.ArrayList;

public interface IModeloCRUD {
    void incluir(Modelo modelo) throws Exception;
    void alterar(Modelo modelo) throws Exception;
    ArrayList<Modelo> obterListaDeModelos() throws Exception;
    Modelo obterModelo(int id) throws Exception;
}
