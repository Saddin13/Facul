package persistencia;

import ferramentas.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import modelos.Cliente;
import modelos.IClienteCRUD;

public class ClienteDAO implements IClienteCRUD {
    
    @Override
    public void incluir(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente (tipo_cliente, nome, telefone, logradouro, " +
                    "numero, complemento, email, cpf, cnpj, contato, inscricao_estadual) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, cliente.getTipoCliente());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getLogradouro());
            ps.setInt(5, cliente.getNumero());
            ps.setString(6, cliente.getComplemento());
            ps.setString(7, cliente.getEmail());
            ps.setString(8, cliente.getCpf());
            ps.setString(9, cliente.getCnpj());
            ps.setString(10, cliente.getContato());
            ps.setString(11, cliente.getInscricaoEstadual());
            
            ps.executeUpdate();
        }
    }
    
    @Override
    public void alterar(Cliente cliente) throws Exception {
        String sql = "UPDATE cliente SET tipo_cliente=?, nome=?, telefone=?, " +
                    "logradouro=?, numero=?, complemento=?, email=?, cpf=?, " +
                    "cnpj=?, contato=?, inscricao_estadual=? WHERE id_cliente=?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, cliente.getTipoCliente());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getLogradouro());
            ps.setInt(5, cliente.getNumero());
            ps.setString(6, cliente.getComplemento());
            ps.setString(7, cliente.getEmail());
            ps.setString(8, cliente.getCpf());
            ps.setString(9, cliente.getCnpj());
            ps.setString(10, cliente.getContato());
            ps.setString(11, cliente.getInscricaoEstadual());
            ps.setInt(12, cliente.getId());
            
            ps.executeUpdate();
        }
    }
    
    @Override
    public ArrayList<Cliente> obterListaDeClientes() throws Exception {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("tipo_cliente"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("logradouro"),
                    rs.getInt("numero"),
                    rs.getString("complemento"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("cnpj"),
                    rs.getString("contato"),
                    rs.getString("inscricao_estadual")
                );
                lista.add(cliente);
            }
        }
        return lista;
    }
}
