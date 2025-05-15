package modelos;

public class Cliente {
    private int id;
    private String tipoCliente;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String cnpj;
    private String contato;
    private String inscricaoEstadual;
    private String logradouro;
    private int numero;
    private String complemento;
    
    public Cliente() {
        // Default constructor
    }
    
    public Cliente(int id, String tipoCliente, String nome, String telefone, 
                  String logradouro, int numero, String complemento, String email, 
                  String cpf, String cnpj, String contato, String inscricaoEstadual) {
        this.id = id;
        this.tipoCliente = tipoCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.email = email;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.contato = contato;
        this.inscricaoEstadual = inscricaoEstadual;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
    
    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }
    
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
}
