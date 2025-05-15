package modelos;

public class Professor implements Comparable<Professor> {
    private int matricula = 0;
    private String nome = "";
    private String escola = "";
    private String titulacao = "";
    private String tipoContrato = "";

    public Professor() {
    }

    public Professor(int matricula, String nome, String escola, String titulacao, String tipoContrato) {
        this.matricula = matricula;
        this.nome = nome;
        this.escola = escola;
        this.titulacao = titulacao;
        this.tipoContrato = tipoContrato;
    }

    @Override
    public int compareTo(Professor outro) {
        // Compare by name or registration number based on your needs
        return this.nome.compareToIgnoreCase(outro.nome);
        // Or by matricula:
        // return Integer.compare(this.matricula, outro.matricula);
    }

    // Implement equals and hashCode for proper TreeSet functionality
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Professor) {
            Professor outro = (Professor) obj;
            return this.matricula == outro.matricula;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(matricula);
    }

    // Your existing getters and setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
}
