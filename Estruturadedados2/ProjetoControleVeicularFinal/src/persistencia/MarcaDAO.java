package persistencia;

import ferramentas.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Marca;
import modelos.IMarcaCRUD;

public class MarcaDAO implements IMarcaCRUD {
    private Connection conexao = null;

    public MarcaDAO() throws Exception {
        conexao = ConexaoBD.getConexao();
    }

    @Override
    public void incluir(Marca marca) throws Exception {
        String sql = "INSERT INTO marca (nome) VALUES (?)";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, marca.getNome());
        preparedStatement.executeUpdate();
    }

    @Override
    public void alterar(Marca marca) throws Exception {
        String sql = "UPDATE marca SET nome=? WHERE id_marca=?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, marca.getNome());
        preparedStatement.setInt(2, marca.getId());
        preparedStatement.executeUpdate();
    }
    
    @Override
    public ArrayList<Marca> obterListaDeMarcas() throws Exception {
        ArrayList<Marca> listaDeMarcas = new ArrayList<>();
        String sql = "SELECT * FROM marca ORDER BY id_marca";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Marca marca = new Marca(
                resultSet.getInt("id_marca"),
                resultSet.getString("nome")
            );
            listaDeMarcas.add(marca);
        }
        return listaDeMarcas;
    }

    public Marca obterPorId(int id) throws Exception {
        String sql = "SELECT * FROM marca WHERE id_marca = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                return new Marca(id, nome);
            }
        return null;
        }
    }
    
    public Marca obterMarca(int id) throws Exception {
        String sql = "SELECT * FROM marca WHERE id_marca=?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            return new Marca(
                resultSet.getInt("id_marca"),
                resultSet.getString("nome")
            );
        }
        throw new Exception("Marca não encontrada");
    }
}
