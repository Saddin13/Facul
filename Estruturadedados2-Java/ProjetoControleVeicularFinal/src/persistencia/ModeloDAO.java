package persistencia;

import ferramentas.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Modelo;
import modelos.IModeloCRUD;

public class ModeloDAO implements IModeloCRUD {
    private Connection conexao = null;

    public ModeloDAO() throws Exception {
        conexao = ConexaoBD.getConexao();
    }

    @Override
    public void incluir(Modelo modelo) throws Exception {
        String sql = "INSERT INTO modelo (nome, id_marca) VALUES (?, ?)";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, modelo.getNome());
        preparedStatement.setInt(2, modelo.getIdMarca());
        preparedStatement.executeUpdate();
    }

    @Override
    public void alterar(Modelo modelo) throws Exception {
        String sql = "UPDATE modelo SET nome=?, id_marca=? WHERE id_modelo=?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, modelo.getNome());
        preparedStatement.setInt(2, modelo.getIdMarca());
        preparedStatement.setInt(3, modelo.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<Modelo> obterListaDeModelos() throws Exception {
        ArrayList<Modelo> listaDeModelos = new ArrayList<>();
        String sql = "SELECT * FROM modelo ORDER BY id_modelo";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Modelo modelo = new Modelo(
                resultSet.getInt("id_modelo"),
                resultSet.getString("nome"),
                resultSet.getInt("id_marca")
            );
            listaDeModelos.add(modelo);
        }
        return listaDeModelos;
    }

    @Override
    public Modelo obterModelo(int id) throws Exception {
        String sql = "SELECT * FROM modelo WHERE id_modelo=?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            return new Modelo(
                resultSet.getInt("id_modelo"),
                resultSet.getString("nome"),
                resultSet.getInt("id_marca")
            );
        }
        throw new Exception("Modelo não encontrado");
    }
}
