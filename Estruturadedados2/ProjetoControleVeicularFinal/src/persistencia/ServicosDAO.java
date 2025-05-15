/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import modelos.IServicosCRUD;
import modelos.Servicos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ferramentas.ConexaoBD;
import java.util.ArrayList;
/**
 *
 * @author ejmcc
 */
public class ServicosDAO implements IServicosCRUD{
    //Conexao com o banco
    private Connection conexao = null;

    public ServicosDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
  @Override
  public ArrayList<Servicos> obterListaDeServicos() throws Exception {
    ArrayList<Servicos> listaDeServicos = new ArrayList<>();
        String sql = "select * from servico order by idserv";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Servicos objServicos = new Servicos();
                objServicos.setIdentificador(rs.getInt("idserv"));
                objServicos.setDescricao(rs.getString("descricao"));
                listaDeServicos.add(objServicos);
            }
            return listaDeServicos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
  }

  @Override
  public void incluir(Servicos objServico) throws Exception {
    try {
      String sql = "insert into servico(descricao)values(?);";
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, objServico.getDescricao());
      preparedStatement.executeUpdate();
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Incluir Persistencia: " + erro);
    } 
  }

  @Override
  public void alterar(Servicos objServico) throws Exception {
    try {
      String sql = "update servico set descricao = ? "
              + "where idserv = ?";
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, objServico.getDescricao());
      preparedStatement.setInt(2, objServico.getIdentificador());
      preparedStatement.executeUpdate();
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Alterar Servicos Persistencia: " + erro);
    }   
  }

  
  
}
