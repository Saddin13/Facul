package modelos;

import java.sql.Date;
import java.util.ArrayList;

public interface IPosseCRUD {
    public ArrayList<Posse> obterListaDePosses() throws Exception;
    public void incluir(Posse objPosse) throws Exception;
    public void setDataVenda(int idPosse, Date dataVenda) throws Exception;
    public boolean hasActiveOwnership(String placa) throws Exception;
    public void closeCurrentOwnership(String placa, Date dataVenda) throws Exception;
    public int getClienteIdFromCPF(String cpf) throws Exception;
}

