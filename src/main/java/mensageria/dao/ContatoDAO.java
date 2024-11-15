package mensageria.dao;


import mensageria.factory.ConnectionFactory;
import model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    public void salvarContato(Contato contato) {
        String sql = "INSERT INTO contatos (nome, email, telefone) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contato> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM contatos";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setTelefone(rs.getString("telefone"));
                contatos.add(contato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contatos;
    }

    public void excluirContato(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

