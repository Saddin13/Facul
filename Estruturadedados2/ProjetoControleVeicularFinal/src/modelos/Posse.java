package modelos;

import java.sql.Date;

public class Posse {
    private int identificador;
    private String placaVeiculo;
    private int CPF;
    private Date dataAquisicao;
    private Date dataVenda;
    private String nomeCliente; // For display purposes

    public Posse() {
    }

    public Posse(int identificador, String placaVeiculo, int CPF, 
                 Date dataAquisicao, Date dataVenda) {
        this.identificador = identificador;
        this.placaVeiculo = placaVeiculo;
        this.CPF = CPF;
        this.dataAquisicao = dataAquisicao;
        this.dataVenda = dataVenda;
    }

    // Getters and Setters
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return identificador + " - " + placaVeiculo + " => " + nomeCliente + 
               " (Aquisição: " + dataAquisicao + ")";
    }
}
