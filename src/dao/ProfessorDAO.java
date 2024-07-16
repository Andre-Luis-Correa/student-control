package dao;

import connections.DatabaseConnection;
import exceptions.DeleteSqlException;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import exceptions.UpdateSqlException;
import model.ProfessorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void insert(ProfessorModel professorModel) throws InsertSqlException {
        String query = "INSERT INTO professor (nomeProfessor, emailProfessor, enderecoProfessor) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, professorModel.getNomeProfessor());
            stmt.setString(2, professorModel.getEmailProfessor());
            stmt.setString(3, professorModel.getEnderecoProfessor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir na tabela professor", e);
        }
    }

    public ProfessorModel select(int id) throws SelectSqlException {
        ProfessorModel professorModel = new ProfessorModel();

        String query = "SELECT * FROM professor WHERE idProfessor = ?";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    professorModel.setIdProfessor(rs.getString("idProfessor"));
                    professorModel.setNomeProfessor(rs.getString("nomeProfessor"));
                    professorModel.setEmailProfessor(rs.getString("emailProfessor"));
                    professorModel.setEnderecoProfessor(rs.getString("enderecoProfessor"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela professor", e);
        }
        return professorModel;
    }

    public List<ProfessorModel> selectAll() throws SelectSqlException {
        List<ProfessorModel> professorModels = new ArrayList<>();

        String query = "SELECT * FROM professor";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProfessorModel professorModel = new ProfessorModel();
                professorModel.setIdProfessor(rs.getString("idProfessor"));
                professorModel.setNomeProfessor(rs.getString("nomeProfessor"));
                professorModel.setEmailProfessor(rs.getString("emailProfessor"));
                professorModel.setEnderecoProfessor(rs.getString("enderecoProfessor"));

                professorModels.add(professorModel);
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela professor", e);
        }
        return professorModels;
    }

    public void update(ProfessorModel professorModel) throws UpdateSqlException {
        String query = "UPDATE professor SET nomeProfessor = ?, emailProfessor = ?, enderecoProfessor = ? WHERE idProfessor = ?";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, professorModel.getNomeProfessor());
            stmt.setString(2, professorModel.getEmailProfessor());
            stmt.setString(3, professorModel.getEnderecoProfessor());
            stmt.setString(4, professorModel.getIdProfessor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateSqlException("Erro ao atualizar a tabela professor", e);
        }
    }

    public void delete(int id) throws DeleteSqlException {
        String query = "DELETE FROM professor WHERE idProfessor = ?";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteSqlException("Erro ao deletar da tabela professor", e);
        }
    }
}
