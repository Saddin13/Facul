package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import modelos.Professor;

public class ManipularArquivo {
    private String caminhoArquivo = "";

    public ManipularArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
    
    public TreeMap<Integer, Professor> obterListaProfessores() throws Exception {
        TreeMap<Integer, Professor> mapa = new TreeMap<>(); 
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                try {
                    System.out.println("Lendo linha: " + linha);
                    String[] dados = linha.split(";");
                    if (dados.length != 5) {
                        continue;
                    }
                    
                    int matricula = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String escola = dados[2];
                    String titulacao = dados[3];
                    String contrato = dados[4];
                    
                    Professor professor = new Professor(matricula, nome, escola, titulacao, contrato);
                    mapa.put(matricula, professor);
                    
                } catch (Exception e) {
                    System.err.println("Erro ao processar a linha: " + linha + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new Exception("Erro ao ler o arquivo: " + e.getMessage());
        }
        
        return mapa;
    }

    public void atualizarArquivo(TreeMap<Integer, Professor> professores) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Professor professor : professores.values()) {
                String linha = professor.getMatricula() + ";" +
                        professor.getNome() + ";" +
                        professor.getEscola() + ";" +
                        professor.getTitulacao() + ";" +
                        professor.getTipoContrato();
                        
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }
}
