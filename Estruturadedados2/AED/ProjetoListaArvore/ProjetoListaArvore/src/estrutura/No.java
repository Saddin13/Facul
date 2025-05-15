
package estrutura;

import modelo.Aluno;

public class No {
    private String chave = "";
    private No esquerdo = null;
    private No direito = null;
    private Aluno novoAluno = null;
    
    ///metodos

    public No(String chaves,Aluno alunoNovo) {
        chave = chaves;
        esquerdo = null;
        direito = null;
        novoAluno = alunoNovo;
    }

    public No() {
        chave = "";
        esquerdo = null;
        direito = null;
        novoAluno = null;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public No getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }

    public No getDireito() {
        return direito;
    }

    public void setDireito(No direito) {
        this.direito = direito;
    }

    public Aluno getNovoAluno() {
        return novoAluno;
    }

    public void setNovoAluno(Aluno novoAluno) {
        this.novoAluno = novoAluno;
    }
    
    
    
}
