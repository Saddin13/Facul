/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import estrutura.ConjuntoAlunos;
import modelo.Aluno;

public class DadosArqui{
    private File nomeArqui = new File("");
    
    public DadosArqui() {
    }
    public DadosArqui(File nomeArqui) {
        this.nomeArqui = nomeArqui;
    }

    public void setNomeArqui(File nomeArqui) {
        this.nomeArqui = nomeArqui;
    }
    
    public ConjuntoAlunos obterAlunos() throws Exception{
        try{
        ConjuntoAlunos alunoNew = new ConjuntoAlunos();
        FileReader lerArq = new FileReader(nomeArqui);
        BufferedReader bufLer = new BufferedReader(lerArq);
        String linha = "";
        while((linha = bufLer.readLine()) != null){
        String[] partesLinha = linha.split(";");
        Aluno conjtAluno = new Aluno(partesLinha[0], partesLinha[1], partesLinha[2], Integer.parseInt(partesLinha[3]), partesLinha[4],partesLinha[5]); 
        //Criar objeto 
        alunoNew.incluir(conjtAluno);
        }
        bufLer.close();
        return alunoNew;
        }
        catch (Exception erro){
        throw new Exception(erro.getMessage() + "erro no obter dados");
        }
    }
    
    
}