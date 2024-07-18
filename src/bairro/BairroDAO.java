package bairro;

import connections.DatabaseConnection;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BairroDAO {

    public int insert(BairroModel bairro) throws InsertSqlException {
        String query = "INSERT INTO bairro (nomeBairro) VALUES (?)";
        int idBairro = 0;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, bairro.getNomeBairro());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idBairro = rs.getInt(1);
                    }
                }
            } else {
                throw new InsertSqlException("Erro ao inserir bairro, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir bairro", e);
        }

        return idBairro;
    }

    public BairroModel selectById(int idBairro) throws SelectSqlException {
        String query = "SELECT * FROM bairro WHERE idBairro = ?";
        BairroModel bairro = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idBairro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    bairro = new BairroModel();
                    bairro.setIdBairro(rs.getInt("idBairro"));
                    bairro.setNomeBairro(rs.getString("nomeBairro"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela bairro", e);
        }

        return bairro;
    }
}
