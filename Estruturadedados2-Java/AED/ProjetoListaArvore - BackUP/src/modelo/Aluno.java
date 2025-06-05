/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Objects;

public class Aluno {
    //atributos
    private String matricula = "";
    private String nome = "";
    private String turno = "";
    private int periodo = 0;
    private String enfase = "";
    private String curso = "";

    public Aluno(String matricula,String nome,String turno,int periodo,String enfase,String curso) throws Exception{
        if(periodo <= 0) throw new Exception(" Periodo invalido deve ser igual a 1 ou maior\n");
        this.matricula = matricula;
        this.nome = nome;
        this.turno = turno;
        this.periodo = periodo;
        this.enfase = enfase;
        this.curso = curso;
    }

    public Aluno() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getEnfase() {
        return enfase;
    }

    public void setEnfase(String enfase) {
        this.enfase = enfase;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String toStringBusca() {
        return "matricula=" + matricula + "\n " + "nome=" + nome + "\n " + "turno=" + turno + "\n " + "periodo=" + periodo + "\n " +"enfase=" + enfase + "\n " + "curso=" + curso + "\n\n";
    }

    @Override
    public String toString() {
        return  matricula + ";" + nome + ";" + turno + ";" + periodo + ";" + enfase + ";" + curso;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        return Objects.equals(this.matricula, other.matricula);
    }
}
