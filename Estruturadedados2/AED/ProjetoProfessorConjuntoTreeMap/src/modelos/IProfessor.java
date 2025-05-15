/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelos;

public interface IProfessor {
  public void incluir(Professor professor)throws Exception;
  public void excluir(int matricula) throws Exception;
  public void alterar(Professor professor) throws Exception;
  public Professor[] listagemDeProfessores() throws Exception;
}
