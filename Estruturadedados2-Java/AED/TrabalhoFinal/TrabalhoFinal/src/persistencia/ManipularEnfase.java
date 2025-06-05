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
import modelos.Enfase;

public class ManipularEnfase {

    private String caminhoEnfase = "";

    public ManipularEnfase(String caminhoEnfase) {
        this.caminhoEnfase = caminhoEnfase;
    }
    
    public TreeSet<Enfase> obterListaEnfase() throws Exception {
            System.out.println("Tentando ler arquivo: " + caminhoEnfase);
    TreeSet<Enfase> conjunto = new TreeSet<Enfase>(); 
    try (BufferedReader br = new BufferedReader(new FileReader(caminhoEnfase))) {
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
                    int idEnfase = Integer.parseInt(dados[0].trim());
                    String dEnfase = dados[1].trim();

                    Enfase Enfase = new Enfase(idEnfase, dEnfase);
                    conjunto.add(Enfase);

                    // Add debug logging
                    System.out.println("Enfase adicionado: ID=" + idEnfase + ", Nome=" + dEnfase);
                } catch (Exception e) {
                    System.err.println("Erro ao processar a linha: " + linha + " - " + e.getMessage());
                }
            }
        }
            // Add validation for empty set
            if (conjunto.isEmpty()) {
            throw new Exception("Nenhum Enfase foi carregado do arquivo");
        }
        System.out.println("Total de Enfases carregados: " + conjunto.size());
        return conjunto;
    }

    public void atualizarArquivo(TreeSet<Enfase> Enfases) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoEnfase))) {
            for (Enfase Enfase : Enfases) {
                String linha = Enfase.getIdEnfase()+ ";" + Enfase.getdEnfase();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }
    }

