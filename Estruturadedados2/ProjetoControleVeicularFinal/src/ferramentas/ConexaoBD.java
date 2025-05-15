
package ferramentas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static Connection conexao = null;  

    private ConexaoBD() {}

    public static Connection getConexao() throws Exception {
        try {
            if (conexao == null || conexao.isClosed()) {
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://localhost:5432/ctrl_veiculos";
                String user = "postgres";
                String password = "159753";

                Class.forName(driver);

                conexao = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão com o banco de dados estabelecida.");
            }
        } catch (ClassNotFoundException erro) {
            throw new Exception("Erro ao carregar o driver: " + erro.getMessage());
        } catch (SQLException erro) {
            throw new Exception("Erro na conexão com o banco: " + erro.getMessage());
        }
        return conexao;
    }

    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão com o banco de dados foi fechada.");
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao fechar a conexão: " + erro.getMessage());
        }
    }
}