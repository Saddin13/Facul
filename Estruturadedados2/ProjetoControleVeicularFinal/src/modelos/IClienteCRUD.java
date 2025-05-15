package modelos;

import java.util.ArrayList;

public interface IClienteCRUD {
    void incluir(Cliente cliente) throws Exception;
    void alterar(Cliente cliente) throws Exception;
    ArrayList<Cliente> obterListaDeClientes() throws Exception;
}