/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manipular;

import java.util.ArrayList;
import java.util.List;
import modelo.Aluno;

public interface IAluno {
    public void incluir (Aluno objetoAluno) throws Exception;
    public List<Aluno> listar(String tipoOrdenacao) throws Exception;
    public List<Aluno> buscar(String nomeMatricula, String tipoBusca) throws Exception;
}
