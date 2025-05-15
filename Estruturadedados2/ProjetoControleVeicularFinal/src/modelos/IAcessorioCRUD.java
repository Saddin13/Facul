package modelos;

import java.util.ArrayList;

public interface IAcessorioCRUD {
    void incluir(Acessorio acessorio) throws Exception;
    void alterar(Acessorio acessorio) throws Exception;
    ArrayList<Acessorio> obterListaDeAcessorios() throws Exception;
}