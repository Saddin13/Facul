/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author ejmcc
 */
public class Servicos {
  //Atributos
  private int identificador = 0;
  private String descricao = "";
  //Metodos

  public Servicos() {
  }
  public Servicos(int identificador, String descricao) {
    this.identificador = identificador;
    this.descricao = descricao;
  }

  public int getIdentificador() {
    return identificador;
  }

  public void setIdentificador(int identificador) {
    this.identificador = identificador;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return identificador + "-" + descricao;
  }
  
  
}
