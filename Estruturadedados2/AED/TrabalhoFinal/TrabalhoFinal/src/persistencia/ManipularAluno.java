/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;
import modelos.Aluno;
import modelos.AlunoCompleto;

public class ManipularAluno {
    private String caminhoAluno = "";

    public ManipularAluno(String caminhoAluno) {
        this.caminhoAluno = caminhoAluno;
    }
    
    public TreeSet<Aluno> obterListaAluno() throws Exception {
        TreeSet<Aluno> conjunto = new TreeSet<Aluno>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoAluno))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                try {
                    System.out.println("Lendo linha: " + linha);
                    String[] dados = linha.split(";");
                    if (dados.length != 6) {
                        continue;
                    }
                    
                    String matricula = dados[0];
                    String nome = dados[1];
                    String turno = dados[2];
                    int periodo = Integer.parseInt(dados[3]);
                    int IDenfase = Integer.parseInt(dados[4]);
                    int IDcurso = Integer.parseInt(dados[5]);
                    
                    Aluno aluno = new Aluno(matricula, nome, turno, periodo, IDenfase, IDcurso);
                    conjunto.add(aluno);
                } catch (Exception e) {
                    System.err.println("Erro ao processar a linha: " + linha + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new Exception("Erro ao ler o arquivo: " + e.getMessage());
        }
        return conjunto;
    }

    public void atualizarArquivo(TreeSet<Aluno> alunos) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoAluno))) {
            for (Aluno aluno : alunos) {
                String linha = String.format("%s;%s;%s;%d;%d;%d", 
                    aluno.getMatricula(),
                    aluno.getNome(),
                    aluno.getTurno(),
                    aluno.getPeriodo(),
                    aluno.getIDenfase(),
                    aluno.getIDcurso());
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }
    public void gravarLista(TreeSet<AlunoCompleto> alunosCompletos) throws Exception {
    TreeSet<Aluno> alunos = new TreeSet<>();
    
    for(AlunoCompleto ac : alunosCompletos) {
        Aluno aluno = new Aluno(
            ac.getMatricula(),
            ac.getNome(),
            ac.getTurno(),
            ac.getPeriodo(),
            ac.getIDenfase(),
            ac.getIDcurso()
        );
        alunos.add(aluno);
    }
    
    atualizarArquivo(alunos);
}
}
