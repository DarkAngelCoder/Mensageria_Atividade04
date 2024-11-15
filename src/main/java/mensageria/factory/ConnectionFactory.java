package mensageria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/contatos";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    // Bloco estático para carregar o driver
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar o driver JDBC", e);
        }
    }

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    // Método para fechar a conexão
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
