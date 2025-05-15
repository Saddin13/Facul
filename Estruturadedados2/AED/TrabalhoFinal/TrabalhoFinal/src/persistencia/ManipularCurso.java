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
import modelos.Curso;

public class ManipularCurso {

    private String caminhoCurso = "";

    public ManipularCurso(String caminhoCurso) {
        this.caminhoCurso = caminhoCurso;
    }
    
    public TreeSet<Curso> obterListaCurso() throws Exception {
            System.out.println("Tentando ler arquivo: " + caminhoCurso);
    TreeSet<Curso> conjunto = new TreeSet<Curso>(); 
    try (BufferedReader br = new BufferedReader(new FileReader(caminhoCurso))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            System.out.println("Lendo linha: " + linha);
                try {
                    String[] dados = linha.split(";");
                    // Add validation for data format
                    if (dados.length != 2) {
                        throw new Exception("Formato inválido na linha: " + linha);
                    }

                    // Trim the data to remove any whitespace
                    int idCurso = Integer.parseInt(dados[0].trim());
                    String dCurso = dados[1].trim();

                    Curso curso = new Curso(idCurso, dCurso);
                    conjunto.add(curso);

                    // Add debug logging
                    System.out.println("Curso adicionado: ID=" + idCurso + ", Nome=" + dCurso);
                } catch (Exception e) {
                    System.err.println("Erro ao processar a linha: " + linha + " - " + e.getMessage());
                }
            }
        }
            // Add validation for empty set
            if (conjunto.isEmpty()) {
            throw new Exception("Nenhum curso foi carregado do arquivo");
        }
        System.out.println("Total de cursos carregados: " + conjunto.size());
        return conjunto;
    }

    public void atualizarArquivo(TreeSet<Curso> Cursos) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoCurso))) {
            for (Curso Curso : Cursos) {
                String linha = Curso.getIdCurso()+ ";" + Curso.getdCurso();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }
    }

