package modelos;

import java.util.ArrayList;

public interface IMarcaCRUD {
    void incluir(Marca marca) throws Exception;
    void alterar(Marca marca) throws Exception;
    public Marca obterPorId(int id) throws Exception;
    ArrayList<Marca> obterListaDeMarcas() throws Exception;
}