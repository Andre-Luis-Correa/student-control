package dao;

import connections.DatabaseConnection;
import exceptions.DeleteSqlException;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import exceptions.UpdateSqlException;
import model.AlunoModel;
import model.EnderecoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private EnderecoDAO enderecoDAO;

    public AlunoDAO() {
        this.enderecoDAO = new EnderecoDAO();
    }

    public void insert(AlunoModel alunoModel) throws InsertSqlException {
        String query = "INSERT INTO aluno (nomeAluno, cpfAluno, telefoneAluno, emailAluno, idEndereco) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, alunoModel.getNomeAluno());
            stmt.setString(2, alunoModel.getCpfAluno());
            stmt.setString(3, alunoModel.getTelefoneAluno());
            stmt.setString(4, alunoModel.getEmailAluno());
            stmt.setInt(5, alunoModel.getEnderecoModel().getIdEndereco());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InsertSqlException("Error inserting address", e);
        }
    }

    public AlunoModel selectByCpf(String cpfAluno) throws SelectSqlException {
        String query = "SELECT * FROM aluno WHERE cpfAluno = ?";
        AlunoModel alunoModel = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cpfAluno);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    alunoModel = new AlunoModel();
                    alunoModel.setIdAluno(rs.getInt("idAluno"));
                    alunoModel.setNomeAluno(rs.getString("nomeAluno"));
                    alunoModel.setCpfAluno(rs.getString("cpfAluno"));
                    alunoModel.setTelefoneAluno(rs.getString("telefoneAluno"));
                    alunoModel.setEmailAluno(rs.getString("emailAluno"));

                    int idEndereco = rs.getInt("idEndereco");
                    EnderecoModel enderecoModel = enderecoDAO.selectById(idEndereco);
                    alunoModel.setEnderecoModel(enderecoModel);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela aluno", e);
        }
        return alunoModel;
    }
}
