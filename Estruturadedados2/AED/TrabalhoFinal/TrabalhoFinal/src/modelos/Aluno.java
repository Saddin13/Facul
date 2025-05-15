
package modelos;

public class Aluno implements Comparable <Aluno>{
    private String matricula = "";
    String nome = "";
    private String turno = "";
    private int periodo = 0;
    private int IDenfase = 0;
    private int IDcurso = 0;

    public Aluno(String matricula, String nome, String turno, int periodo, int IDenfase, int IDcurso) throws Exception {
        if (matricula == null || matricula.isEmpty()) 
            throw new Exception("Matrícula inválida. Não pode ser nula ou vazia.");
        if (nome == null || nome.isEmpty()) 
            throw new Exception("Nome inválido. Não pode ser nulo ou vazio.");
        if (turno == null || turno.isEmpty()) 
            throw new Exception("Turno inválido. Não pode ser nulo ou vazio.");
        if (periodo <= 0) 
            throw new Exception("Período inválido. Deve ser maior ou igual a 1.");
        if (IDcurso <= 0) 
            throw new Exception("Curso inválido. ID deve ser maior que 0.");
        if (IDenfase <= 0) 
            throw new Exception("Ênfase inválida. ID deve ser maior que 0.");
        
        this.matricula = matricula;
        this.nome = nome;
        this.turno = turno;
        this.periodo = periodo;
        this.IDenfase = IDenfase;
        this.IDcurso = IDcurso;
    }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Aluno) {
                Aluno outro = (Aluno) obj;
                return this.matricula.equals(outro.matricula);  // Use equals() for String comparison
            }
            return false;
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

    public int getIDenfase() {
        return IDenfase;
    }

    public void setIDenfase(int IDenfase) {
        this.IDenfase = IDenfase;
    }

    public int getIDcurso() {
        return IDcurso;
    }

    public void setIDcurso(int IDcurso) {
        this.IDcurso = IDcurso;
    }

    @Override
    public int compareTo(Aluno outro) {
     return this.nome.compareToIgnoreCase(outro.nome);
    }  
}