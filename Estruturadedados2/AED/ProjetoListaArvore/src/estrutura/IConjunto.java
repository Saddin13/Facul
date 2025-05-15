
package estrutura;

import java.util.List;
import modelo.Aluno;

public interface IConjunto {
    public void incluir (Aluno objetoAluno) throws Exception;
    public List<Aluno> listar(String tipoOrdenacao) throws Exception;
    public List<Aluno> buscar(String nomeMatricula, String tipoBusca) throws Exception;
    public boolean excluirPorMatricula(String matricula) throws Exception;
    public boolean excluirPorNome(String nome) throws Exception;
}
