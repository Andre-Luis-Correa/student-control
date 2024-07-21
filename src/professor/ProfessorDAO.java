package professor;

import connections.DatabaseConnection;
import endereco.EnderecoDAO;
import endereco.EnderecoModel;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    private EnderecoDAO enderecoDAO;

    public ProfessorDAO() {
        this.enderecoDAO = new EnderecoDAO();
    }

    public void insert(ProfessorModel professor) throws InsertSqlException {
        String query = "INSERT INTO professor (nomeProfessor, emailProfessor, idEndereco) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, professor.getNomeProfessor());
            stmt.setString(2, professor.getEmailProfessor());
            stmt.setInt(3, professor.getIdEndereco());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir professor", e);
        }
    }

    public ProfessorModel selectById(int idProfessor) throws SelectSqlException {
        String query = "SELECT * FROM professor WHERE idProfessor = ?";
        ProfessorModel professor = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idProfessor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    professor = new ProfessorModel();
                    professor.setIdProfessor(rs.getString("idProfessor"));
                    professor.setNomeProfessor(rs.getString("nomeProfessor"));
                    professor.setEmailProfessor(rs.getString("emailProfessor"));
                    professor.setIdEndereco(rs.getInt("idEndereco"));

                    EnderecoModel enderecoModel = enderecoDAO.selectById(professor.getIdEndereco());
                    professor.setEnderecoProfessor(enderecoModel);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao buscar professor por ID", e);
        }
        return professor;
    }

    public List<ProfessorModel> selectAll() throws SelectSqlException {
        String query = "SELECT * FROM professor";
        List<ProfessorModel> professores = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProfessorModel professor = new ProfessorModel();
                professor.setIdProfessor(rs.getString("idProfessor"));
                professor.setNomeProfessor(rs.getString("nomeProfessor"));
                professor.setEmailProfessor(rs.getString("emailProfessor"));
                professor.setIdEndereco(rs.getInt("idEndereco"));

                EnderecoModel enderecoModel = enderecoDAO.selectById(professor.getIdEndereco());
                professor.setEnderecoProfessor(enderecoModel);

                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao buscar todos os professores", e);
        }
        return professores;
    }
}
