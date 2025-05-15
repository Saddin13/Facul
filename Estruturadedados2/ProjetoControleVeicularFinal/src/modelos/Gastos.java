/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author ejmcc
 */
public class Gastos {
  //Atributos
  private int identificador = 0;
  private String dataDoServico = "";
  private float valor = 0;
  private int idVeiculoProprietario = 0;
  private int idServico = 0;
  
  //Metodos
  public Gastos() {
  }
  public Gastos(int identificador, String dataDoServico, float valor,
                int idVeiculoProprietario, int idServico) {
    this.identificador = identificador;
    this.dataDoServico = dataDoServico;
    this.valor = valor;
    this.idVeiculoProprietario = idVeiculoProprietario;
    this.idServico = idServico;
  }
  public int getIdentificador() {
    return identificador;
  }
  public void setIdentificador(int identificador) {
    this.identificador = identificador;
  }
  public String getDataDoServico() {
    return dataDoServico;
  }
  public void setDataDoServico(String dataDoServico) {
    this.dataDoServico = dataDoServico;
  }
  public float getValor() {
    return valor;
  }
  public void setValor(float valor) {
    this.valor = valor;
  }
  public int getIdVeiculoProprietario() {
    return idVeiculoProprietario;
  }
  public void setIdVeiculoProprietario(int idVeiculoProprietario) {
    this.idVeiculoProprietario = idVeiculoProprietario;
  }
  public int getIdServico() {
    return idServico;
  }
  public void setIdServico(int idServico) {
    this.idServico = idServico;
  }
}
