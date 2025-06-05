package modelos;

public class Modelo {
    private int id;
    private String nome;
    private int idMarca;

    public Modelo(int id, String nome, int idMarca) {
        this.id = id;
        this.nome = nome;
        this.idMarca = idMarca;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdMarca() {
        return idMarca;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public String toString() {
        return nome;
    }
}
