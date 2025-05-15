package persistencia;

import ferramentas.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import modelos.Acessorio;
import modelos.IAcessorioCRUD;

public class AcessorioDAO implements IAcessorioCRUD {
    
    @Override
    public void incluir(Acessorio acessorio) throws Exception {
        String sql = "INSERT INTO acessorio (nome, descricao) VALUES (?,?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, acessorio.getNome());
            ps.setString(2, acessorio.getDescricao());
            
            ps.executeUpdate();
        }
    }
    
    @Override
    public void alterar(Acessorio acessorio) throws Exception {
        String sql = "UPDATE acessorio SET nome=?, descricao=? WHERE id_acessorio=?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, acessorio.getNome());
            ps.setString(2, acessorio.getDescricao());
            ps.setInt(3, acessorio.getId());
            
            ps.executeUpdate();
        }
    }
    
    @Override
    public ArrayList<Acessorio> obterListaDeAcessorios() throws Exception {
        ArrayList<Acessorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM acessorio ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Acessorio acessorio = new Acessorio(
                    rs.getInt("id_acessorio"),
                    rs.getString("nome"),
                    rs.getString("descricao")
                );
                lista.add(acessorio);
            }
        }
        return lista;
    }
}