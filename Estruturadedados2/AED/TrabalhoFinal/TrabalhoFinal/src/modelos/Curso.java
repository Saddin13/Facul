package modelos;

public class Curso implements Comparable<Curso> {
    private int idCurso = 0;
    private String dCurso = "";

    public Curso() {
    }

    public Curso(int idCurso, String dCurso) {
        this.idCurso = idCurso;
        this.dCurso = dCurso;
    }

    @Override
    public int compareTo(Curso outro) {
        return Integer.compare(this.idCurso, outro.idCurso);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idCurso);
    }

    @Override
    public String toString() {
        return "Curso{id=" + idCurso + ", nome='" + dCurso + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Curso) {
            Curso outro = (Curso) obj;
            return this.idCurso == outro.idCurso;
        }
        return false;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getdCurso() {
        return dCurso;
    }

    public void setdCurso(String dCurso) {
        this.dCurso = dCurso;
    }

}
