package persistencia;

import ferramentas.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Veiculo;
import modelos.IVeiculoCRUD;

public class VeiculoDAO implements IVeiculoCRUD {
    private Connection conexao = null;

    public VeiculoDAO() throws Exception {
        conexao = ConexaoBD.getConexao();
    }

    @Override
    public void incluir(Veiculo veiculo) throws Exception {
        String sql = "INSERT INTO veiculo (placa, id_modelo, ano_modelo, ano_fabricacao, data_cadastro, km, patrimonio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, veiculo.getPlaca());
        preparedStatement.setInt(2, Integer.parseInt(veiculo.getModelo())); // Convert modelo to ID
        preparedStatement.setInt(3, veiculo.getAnoModelo());
        preparedStatement.setInt(4, veiculo.getAnoFabricacao());
        preparedStatement.setDate(5, veiculo.getDataCadastro());
        preparedStatement.setInt(6, veiculo.getKm());
        preparedStatement.setString(7, veiculo.getCpfDono());

        preparedStatement.executeUpdate();
    }

    @Override
    public void alterar(Veiculo veiculo) throws Exception {
        String sql = "UPDATE veiculo modelo=?, ano_modelo=?, ano_fabricacao=?, data_cadastro=?, km=?, patrimonio=? WHERE placa=?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, veiculo.getModelo());
        preparedStatement.setInt(2, veiculo.getAnoModelo());
        preparedStatement.setInt(3, veiculo.getAnoFabricacao());
        preparedStatement.setDate(4, veiculo.getDataCadastro());
        preparedStatement.setInt(5, veiculo.getKm());
        preparedStatement.setString(6, veiculo.getCpfDono());
        preparedStatement.setString(7, veiculo.getPlaca());
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<Veiculo> obterListaDeVeiculos() throws Exception {
        ArrayList<Veiculo> listaDeVeiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Veiculo veiculo = new Veiculo(
                resultSet.getString("placa"),
                resultSet.getString("modelo"),
                resultSet.getInt("ano_modelo"),
                resultSet.getInt("ano_fabricacao"),
                resultSet.getDate("data_cadastro"),
                resultSet.getInt("km"),
                resultSet.getString("cpf_dono")
            );
            listaDeVeiculos.add(veiculo);
        }
        return listaDeVeiculos;
    }

    public Veiculo obterVeiculo(String placa) throws Exception {
        String sql = "SELECT * FROM veiculo WHERE placa=?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, placa);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            return new Veiculo(
                resultSet.getString("placa"),
                resultSet.getString("modelo"),
                resultSet.getInt("ano_modelo"),
                resultSet.getInt("ano_fabricacao"),
                resultSet.getDate("data_cadastro"),
                resultSet.getInt("km"),
                resultSet.getString("cpf_dono")
            );
        }
        throw new Exception("Veículo não encontrado");
    }
}
