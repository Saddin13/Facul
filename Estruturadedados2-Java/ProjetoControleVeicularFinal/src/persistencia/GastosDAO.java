/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelos.Gastos;
import modelos.IGastosCRUD;
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
public class GastosDAO implements IGastosCRUD{
    //Conexao com o banco
    private Connection conexao = null;
    public GastosDAO()throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
  @Override
  public void incluir(Gastos objServico) throws Exception {
try {
      String sql =  "insert into servrealiz(dataServ, valor, idServ, idVeicProp)"
              +     "values(?,?,?,?);";
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, objServico.getDataDoServico());
      preparedStatement.setFloat(2, objServico.getValor());
      preparedStatement.setInt(3, objServico.getIdServico());
      preparedStatement.setInt(4,objServico.getIdVeiculoProprietario());
      preparedStatement.executeUpdate();
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Incluir Persistencia: " + erro);
    }   
  }

  @Override
  public ArrayList<Gastos> obterListaDeGastos() throws Exception {
    ArrayList<Gastos> listaDeGastos = new ArrayList<>();
        String sql = "select * from servrealiz order by idservrealiz";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Gastos objGastos = new Gastos();
                objGastos.setIdentificador(rs.getInt("idservrealiz"));
                objGastos.setDataDoServico(rs.getString("dataserv"));
                objGastos.setValor(rs.getFloat("valor"));
                objGastos.setIdVeiculoProprietario(rs.getInt("idveicprop"));
                objGastos.setIdServico(rs.getInt("idserv"));
                listaDeGastos.add(objGastos);
            }
            return listaDeGastos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
  }
}
