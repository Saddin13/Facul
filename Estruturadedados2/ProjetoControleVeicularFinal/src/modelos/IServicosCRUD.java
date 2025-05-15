/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelos;
import java.util.ArrayList;
/**
 *
 * @author ejmcc
 */
public interface IServicosCRUD {
  public ArrayList<Servicos> obterListaDeServicos() throws Exception;
  public void incluir(Servicos objServico) throws Exception;
  public void alterar(Servicos objServico) throws Exception;
}
