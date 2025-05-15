package modelos;

public class VinculoCarroAcessorio {
    private String placaVeiculo;
    private int idAcessorio;
    
    public VinculoCarroAcessorio() {
    }
    
    public VinculoCarroAcessorio(String placaVeiculo, int idAcessorio) {
        this.placaVeiculo = placaVeiculo;
        this.idAcessorio = idAcessorio;
    }
    
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }
    
    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }
    
    public int getIdAcessorio() {
        return idAcessorio;
    }
    
    public void setIdAcessorio(int idAcessorio) {
        this.idAcessorio = idAcessorio;
    }
}