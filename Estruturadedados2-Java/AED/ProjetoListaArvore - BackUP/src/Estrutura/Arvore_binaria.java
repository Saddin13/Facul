/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estrutura;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import modelo.Aluno;


public class Arvore_binaria implements IArvore{
    //atributo
    private No raiz = null;
    private List<Aluno> saida = null;
    
    //metodos

    public Arvore_binaria() {
        raiz = null;
    }
    
    public Arvore_binaria(String chave, Aluno objt) {
        raiz = new No(chave,objt);
    }
    
    private No inserirRecursivo (No raiz, String chave, Aluno objt) throws Exception{
        Collator collator = Collator.getInstance (new Locale ("pt", "BR"));
	collator.setStrength(Collator.PRIMARY); // Compara certo mesmo com acento
        if(raiz == null) raiz = new No(chave,objt);
        else if(collator.compare(chave, raiz.getChave()) < 0)
            raiz.setEsquerdo(inserirRecursivo(raiz.getEsquerdo(), chave,objt));
        else raiz.setDireito(inserirRecursivo(raiz.getDireito(), chave,objt));
        
        return raiz;
    }
    
    @Override
    public void incluir(String chave, Aluno objt) throws Exception {
        try {
            saida = new ArrayList();
            raiz = inserirRecursivo(raiz, chave, objt);
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
    }
    
    private List<Aluno> pegarDadosCrescenteRecursivo(No raiz)throws Exception{
        try {
            if(raiz != null){
                
                pegarDadosCrescenteRecursivo(raiz.getEsquerdo());
                saida.add(raiz.getNovoAluno());
                pegarDadosCrescenteRecursivo(raiz.getDireito());
            
        } 
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        return saida;  
    }
    
    @Override
    public List<Aluno> pegarDadosCrescenteArvore() throws Exception{
        return pegarDadosCrescenteRecursivo(raiz);
    }

    private List pegarDadosDecrescenteRecursivo(No raiz)throws Exception{
        try {
            if(raiz != null){
                
                pegarDadosDecrescenteRecursivo(raiz.getDireito());
                saida.add(raiz.getNovoAluno());
                pegarDadosDecrescenteRecursivo(raiz.getEsquerdo());
            
        } 
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        return saida;  
    }
    
    @Override
    public List<Aluno> pegarDadosDecrescenteArvore() throws Exception {
        return pegarDadosDecrescenteRecursivo(raiz);
    }
    
    private List<Aluno> localizarDadosRecursivo(No raiz, String chaveBusca)throws Exception{
        try {
            if(raiz != null){
                Collator collator = Collator.getInstance (new Locale ("pt", "BR"));
                collator.setStrength(Collator.PRIMARY); // Compara certo mesmo com acento
                localizarDadosRecursivo(raiz.getEsquerdo(),chaveBusca);
            if (collator.compare(chaveBusca, raiz.getChave()) == 0) {
                saida.add(raiz.getNovoAluno());
            }
                localizarDadosRecursivo(raiz.getDireito(),chaveBusca);
        } 
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        return saida;  
    }
    @Override
    public List<Aluno> buscaArvore(String chaveBusca) throws Exception {
        return localizarDadosRecursivo(raiz,chaveBusca);
    }
    
}
