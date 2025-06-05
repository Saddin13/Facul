
package estrutura;
import java.util.ArrayList;
import java.util.List;
import modelo.Aluno;

public final class ConjuntoAlunos implements IConjunto{
    private List<Aluno> array = null;
    private IArvore arvore = null;
    public ConjuntoAlunos() {
        this.array = new ArrayList();
    }
    
    @Override
    public void incluir(Aluno objetoAluno) throws Exception {
        try {
            String erro = "";
            erro = verificar(objetoAluno);
            if(!erro.isEmpty())throw new Exception(erro + "fecharemos a tela para confirmar os dados");  
    
            if (array != null) {
               if(array.contains(objetoAluno)) throw new Exception("Matricula ja existe \n");
            }
            array.addFirst(objetoAluno);

        } catch (Exception erro) {
        throw new Exception(erro.getMessage() + "erro no Incluir Conjunto\n");
        }   
    }
    private  List<Aluno> incluirArvore(String tipoOrdenacao) throws Exception{
        try {
            arvore = new Arvore_binaria();
            List<Aluno> saidaDados = new ArrayList<>();
                if(tipoOrdenacao.compareTo("crescente_nome") == 0){
                for(int i = 0;i < array.size(); i++){
                    arvore.incluir(array.get(i).getNome(), array.get(i));
                }
                saidaDados = arvore.pegarDadosCrescenteArvore();
                }
                else if(tipoOrdenacao.compareTo("decrescente_nome") == 0){
                for(int i = 0;i < array.size(); i++){
                    arvore.incluir(array.get(i).getNome(), array.get(i));
                }
                saidaDados = arvore.pegarDadosDecrescenteArvore();
                }
                else if(tipoOrdenacao.compareTo("crescente_matricula") == 0){
                for(int i = 0;i < array.size(); i++){
                    arvore.incluir(array.get(i).getMatricula(), array.get(i));
                }
                saidaDados = arvore.pegarDadosCrescenteArvore();
                }
                else if(tipoOrdenacao.compareTo("decrescente_matricula") == 0){
                for(int i = 0;i < array.size(); i++){
                    arvore.incluir(array.get(i).getMatricula(), array.get(i));
                }
                saidaDados = arvore.pegarDadosDecrescenteArvore();
                }
            return saidaDados;
            
        } catch (Exception erro) {
        throw new Exception(erro.getMessage() + "erro no listar Conjunto\n");
        }  
    }
    @Override
    public List<Aluno> listar(String tipoOrdenacao) throws Exception {
        try {
            return incluirArvore(tipoOrdenacao);
        } catch (Exception erro) {
        throw new Exception(erro.getMessage() + "erro no listar Conjunto\n");
        }      
    }
    private  List<Aluno> buscarArvores(String nomeMatricula, String tipoBusca) throws Exception{
        try {
            arvore = new Arvore_binaria();
            List<Aluno> dadosBusca = null;
                if(tipoBusca.compareTo("NOME") == 0){
                for(int i = 0;i < array.size(); i++){
                    arvore.incluir(array.get(i).getNome(), array.get(i));
                }
                }
                else if(tipoBusca.compareTo("MATRICULA") == 0){
                for(int i = 0;i < array.size(); i++){
                    arvore.incluir(array.get(i).getMatricula(), array.get(i));
                }
                }
                
                dadosBusca = arvore.buscaArvore(nomeMatricula);
                
                if(dadosBusca.isEmpty()) throw new Exception("(Nao Encontrado iremos fechar a tela\n)");
            
                return dadosBusca;
            
        } catch (Exception erro) {
        throw new Exception(erro.getMessage() + "erro no buscar aluno\n");
        }  
    }
    @Override
    public List<Aluno> buscar(String nomeMatricula, String tipoBusca) throws Exception {
        try {
            return buscarArvores(nomeMatricula, tipoBusca);
        } catch (Exception erro) {
        throw new Exception(erro.getMessage() + "erro no buscar aluno\n");
        } 
    }
    
    private String verificar(Aluno objeto){
        String erro = "";
        if(objeto.getMatricula().isEmpty()) erro += "Esse campo (matricula) e obrigatorio, nao pode estar vazio.\n";
        if(objeto.getNome().isEmpty()) erro += "Esse campo (nome) e obrigatorio, nao pode estar vazio.\n";      
        if(objeto.getEnfase().isEmpty()) erro += "Esse campo (enfase) e obrigatorio, nao pode estar vazio.\n";       
        if(objeto.getCurso().isEmpty()) erro += "Esse campo (Curso) e obrigatorio, nao pode estar vazio.\n";
        
        if(!Integer.toString(objeto.getPeriodo()).matches("\\d+")) erro += "Esse campo (periodo) somente aceita numeros\n";
        if(!objeto.getEnfase().matches("^[a-zA-ZáéíóúãõâêîôûÁÉÍÓÚÃÕÂÊÎÔÛÇç ]+$")) erro += "Esse campo (Enfase) somente aceita letras\n";
        if(!objeto.getCurso().matches("^[a-zA-ZáéíóúãõâêîôûÁÉÍÓÚÃÕÂÊÎÔÛÇç ]+$")) erro += "Esse campo (curso) somente aceita letras\n";
        if(!objeto.getNome().matches("^[a-zA-ZáéíóúãõâêîôûÁÉÍÓÚÃÕÂÊÎÔÛÇç ]+$")) erro += "Esse campo (nome) somente aceita letras\n";
                      
        return erro;
    }

}