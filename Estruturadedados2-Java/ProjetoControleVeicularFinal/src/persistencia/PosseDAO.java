package persistencia;

import java.util.ArrayList;
import java.sql.*;
import ferramentas.ConexaoBD;
import modelos.Posse;
import modelos.IPosseCRUD;

public class PosseDAO implements IPosseCRUD {
    private Connection conexao = null;

    public PosseDAO() throws Exception {
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO PROPRIETARIOS");
    }

    @Override
    public ArrayList<Posse> obterListaDePosses() throws Exception {
        ArrayList<Posse> listaDePosses = new ArrayList<>();
        String sql = "SELECT p.*, c.nome as nome_cliente FROM posse p " +
                    "INNER JOIN cliente c ON p.id_cliente = c.id_cliente " +
                    "ORDER BY p.id_posse";
        
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while(rs.next()) {
                Posse objPosse = new Posse();
                objPosse.setIdentificador(rs.getInt("id_posse"));
                objPosse.setPlacaVeiculo(rs.getString("placa_veiculo"));
                objPosse.setCPF(rs.getInt("id_cliente"));
                objPosse.setDataAquisicao(rs.getDate("data_aquisicao"));
                objPosse.setDataVenda(rs.getDate("data_venda"));
                objPosse.setNomeCliente(rs.getString("nome_cliente"));
                listaDePosses.add(objPosse);
            }
            return listaDePosses;
        }
    }

    @Override
    public void incluir(Posse objPosse) throws Exception {
        String sql = "INSERT INTO posse(placa_veiculo, id_cliente, data_aquisicao) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, objPosse.getPlacaVeiculo());
            stmt.setInt(2, objPosse.getCPF());
            stmt.setDate(3, new java.sql.Date(objPosse.getDataAquisicao().getTime()));
            stmt.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: " + erro.getMessage());
        }
    }

    public boolean hasActiveOwnership(String placa) throws Exception {
        String sql = "SELECT COUNT(*) FROM posse WHERE placa_veiculo = ? AND data_venda IS NULL";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public void closeCurrentOwnership(String placa, Date dataVenda) throws Exception {
        String sql = "UPDATE posse SET data_venda = ? WHERE placa_veiculo = ? AND data_venda IS NULL";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, dataVenda);
            stmt.setString(2, placa);
            stmt.executeUpdate();
        }
    }

    public void setDataVenda(int idPosse, Date dataVenda) throws Exception {
        String sql = "UPDATE posse SET data_venda = ? WHERE id_posse = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, dataVenda);
            stmt.setInt(2, idPosse);
            stmt.executeUpdate();
        }
    }

    public int getClienteIdFromCPF(String cpf) throws Exception {
        String sql = "SELECT id_cliente FROM cliente WHERE cpf = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_cliente");
            }
            throw new Exception("Cliente não encontrado com o CPF fornecido");
        }
    }
}
