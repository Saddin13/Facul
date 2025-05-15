package modelos;

public class Enfase implements Comparable<Enfase> {
    private int idEnfase = 0;
    private String dEnfase = "";

    public Enfase() {
    }

    public Enfase(int idEnfase, String dEnfase) {
        this.idEnfase = idEnfase;
        this.dEnfase = dEnfase;
    }

    @Override
    public int compareTo(Enfase outro) {
        return Integer.compare(this.idEnfase, outro.idEnfase);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idEnfase);
    }

    @Override
    public String toString() {
        return "Enfase{id=" + idEnfase + ", nome='" + dEnfase + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Enfase) {
            Enfase outro = (Enfase) obj;
            return this.idEnfase == outro.idEnfase;
        }
        return false;
    }

    public int getIdEnfase() {
        return idEnfase;
    }

    public void setIdEnfase(int idEnfase) {
        this.idEnfase = idEnfase;
    }

    public String getdEnfase() {
        return dEnfase;
    }

    public void setdEnfase(String dEnfase) {
        this.dEnfase = dEnfase;
    }

}
