package persistencia;

import ferramentas.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import modelos.VinculoCarroAcessorio;
import modelos.IVinculoCarroAcessorioCRUD;

public class VinculoCarroAcessorioDAO implements IVinculoCarroAcessorioCRUD {
    
    @Override
    public void incluir(VinculoCarroAcessorio vinculo) throws Exception {
        String sql = "INSERT INTO vinculo_carro_acessorio (placa_veiculo, id_acessorio) VALUES (?,?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, vinculo.getPlacaVeiculo());
            ps.setInt(2, vinculo.getIdAcessorio());
            
            ps.executeUpdate();
        }
        
    }
    
    @Override
    public void alterar(VinculoCarroAcessorio vinculo) throws Exception {
        String sql = "UPDATE vinculo_carro_acessorio SET id_acessorio=? WHERE placa_veiculo=?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, vinculo.getIdAcessorio());
            ps.setString(2, vinculo.getPlacaVeiculo());
            
            ps.executeUpdate();
        }
    }
    
    @Override
    public ArrayList<VinculoCarroAcessorio> obterListaDeVinculos() throws Exception {
        ArrayList<VinculoCarroAcessorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM vinculo_carro_acessorio";
        
        try (Connection conn = ConexaoBD.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                VinculoCarroAcessorio vinculo = new VinculoCarroAcessorio(
                    rs.getString("placa_veiculo"),
                    rs.getInt("id_acessorio")
                );
                lista.add(vinculo);
            }
        }
        return lista;
    }
}