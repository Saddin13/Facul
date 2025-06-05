package modelos;

import java.sql.Date;

public class Veiculo {
    private String placa;
    private String modelo;
    private int anoModelo;
    private int anoFabricacao;
    private Date dataCadastro;
    private int km;
    private String cpfDono;

    public Veiculo(String placa, String modelo, int anoModelo, 
                  int anoFabricacao, Date dataCadastro, int km, String cpfDono) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.anoFabricacao = anoFabricacao;
        this.dataCadastro = dataCadastro;
        this.km = km;
        this.cpfDono = cpfDono;
    }

    // Getters
    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public int getAnoModelo() { return anoModelo; }
    public int getAnoFabricacao() { return anoFabricacao; }
    public Date getDataCadastro() { return dataCadastro; }
    public int getKm() { return km; }
    public String getCpfDono() { return cpfDono; }

    // Setters
    public void setPlaca(String placa) { this.placa = placa; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAnoModelo(int anoModelo) { this.anoModelo = anoModelo; }
    public void setAnoFabricacao(int anoFabricacao) { this.anoFabricacao = anoFabricacao; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public void setKm(int km) { this.km = km; }
    public void setCpfDono(String cpfDono) { this.cpfDono = cpfDono; }
}
