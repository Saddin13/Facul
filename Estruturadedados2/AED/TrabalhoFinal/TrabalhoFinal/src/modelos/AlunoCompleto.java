
package modelos;

public class AlunoCompleto implements Comparable<AlunoCompleto> {
    private String matricula = "";
    private String nome = "";
    private String turno = "";
    private int periodo = 0;
    private int IDenfase = 0;
    private String Enfase = "";
    private int IDcurso = 0;
    private String Curso = "";

    public AlunoCompleto(String matricula, String nome, String turno, int periodo, 
                        int IDenfase, String Enfase, int IDcurso, String Curso) throws Exception {
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
        this.Enfase = Enfase;
        this.IDcurso = IDcurso;
        this.Curso = Curso;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AlunoCompleto) {
            AlunoCompleto outro = (AlunoCompleto) obj;
            return this.matricula.equals(outro.matricula);
        }
        return false;
    }

    @Override
    public int compareTo(AlunoCompleto outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }
    
        public String getEnfase() {
        return Enfase;
    }

    public void setEnfase(String Enfase) {
        this.Enfase = Enfase;
    }

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String Curso) {
        this.Curso = Curso;
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
}