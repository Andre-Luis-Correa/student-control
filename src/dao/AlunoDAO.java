package dao;

import connections.DatabaseConnection;
import exceptions.DeleteSqlException;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import exceptions.UpdateSqlException;
import model.AlunoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void insert(AlunoModel alunoModel) throws InsertSqlException {
        String query = "INSERT INTO aluno (nomeAluno, cpfAluno, telefoneAluno, emailAluno, enderecoAluno) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, alunoModel.getNomeAluno());
            stmt.setString(2, alunoModel.getCpfAluno());
            stmt.setString(3, alunoModel.getTelefoneAluno());
            stmt.setString(4, alunoModel.getEmailAluno());
            stmt.setString(5, alunoModel.getEnderecoAluno());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InsertSqlException("Error inserting address", e);
        }
    }

    public AlunoModel select(int id) throws SelectSqlException {
        AlunoModel alunoModel = new AlunoModel();

        String query = "SELECT * FROM aluno WHERE id = ?";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id); // Define o parâmetro 'id'
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    alunoModel.setIdAluno(rs.getInt("idAluno"));
                    alunoModel.setNomeAluno(rs.getString("nomeAluno"));
                    alunoModel.setCpfAluno(rs.getString("cpfAluno"));
                    alunoModel.setTelefoneAluno(rs.getString("telefoneAluno"));
                    alunoModel.setEmailAluno(rs.getString("emailAluno"));
                    alunoModel.setEnderecoAluno(rs.getString("enderecoAluno"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela aluno", e);
        }
        return alunoModel;
    }

    public List<AlunoModel> selectAll() throws SelectSqlException {
        List<AlunoModel> alunoModels = new ArrayList<>();

        String query = "SELECT * FROM aluno";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AlunoModel alunoModel = new AlunoModel();
                alunoModel.setIdAluno(rs.getInt("alunoId"));
                alunoModel.setNomeAluno(rs.getString("nomeAluno"));
                alunoModel.setCpfAluno(rs.getString("cpfAluno"));
                alunoModel.setTelefoneAluno(rs.getString("telefoneAluno"));
                alunoModel.setEmailAluno(rs.getString("emailAluno"));
                alunoModel.setEnderecoAluno(rs.getString("enderecoAluno"));

                alunoModels.add(alunoModel);
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela aluno", e);
        }
        return alunoModels;
    }

    public void update(AlunoModel alunoModel) throws UpdateSqlException {
        String query = "UPDATE aluno SET nomeAluno = ?, cpfAluno = ?, telefoneAluno = ?, emailAluno = ?, enderecoAluno = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, alunoModel.getNomeAluno());
            stmt.setString(2, alunoModel.getCpfAluno());
            stmt.setString(3, alunoModel.getTelefoneAluno());
            stmt.setString(4, alunoModel.getEmailAluno());
            stmt.setString(5, alunoModel.getEnderecoAluno());
            stmt.setInt(6, alunoModel.getIdAluno());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateSqlException("Erro ao atualizar a tabela aluno", e);
        }
    }

    public void delete(int id) throws DeleteSqlException {
        String query = "DELETE FROM aluno WHERE id = ?";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteSqlException("Erro ao deletar da tabela aluno", e);
        }
    }

}
