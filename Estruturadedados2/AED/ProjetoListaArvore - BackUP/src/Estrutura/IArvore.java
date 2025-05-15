/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Estrutura;

import java.util.List;
import modelo.Aluno;

public interface IArvore {
    public void incluir (String chave, Aluno novoAluno) throws Exception;
    public List<Aluno> pegarDadosCrescenteArvore() throws Exception;
    public List<Aluno> pegarDadosDecrescenteArvore() throws Exception;
    public List<Aluno> buscaArvore(String chaveBusca) throws Exception;
}
