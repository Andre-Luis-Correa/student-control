package disciplina;

import connections.DatabaseConnection;
import exceptions.SelectSqlException;
import professor.ProfessorDAO;
import professor.ProfessorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    private ProfessorDAO professorDAO;

    public DisciplinaDAO() {
        this.professorDAO = new ProfessorDAO();
    }

    public List<DisciplinaModel> buscarPorCursoEAnoLetivo(int idCurso, int anoLetivo) throws SelectSqlException {
        String query = "SELECT DISTINCT d.* FROM Disciplina d " +
                "JOIN Curso c ON d.idCurso = c.idCurso " +
                "JOIN MatriculaAluno_Disciplina md ON d.idDisciplina = md.idDisciplina " +
                "JOIN MatriculaAluno_AnoLetivo ma ON md.nroMatricula = ma.nroMatricula " +
                "JOIN AnoLetivo a ON ma.idAnoLetivo = a.idAnoLetivo " +
                "WHERE d.idCurso = ? AND a.anoLetivo = ?";
        List<DisciplinaModel> disciplinas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCurso);
            stmt.setInt(2, anoLetivo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DisciplinaModel disciplina = new DisciplinaModel();
                    disciplina.setIdDisciplina(rs.getInt("idDisciplina"));
                    disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
                    disciplina.setIdCurso(rs.getInt("idCurso"));
                    disciplinas.add(disciplina);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao buscar disciplinas por curso e ano letivo: " + e.getMessage(), e);
        }
        return disciplinas;
    }

    public List<ProfessorModel> buscarProfessoresPorDisciplina(int idDisciplina) throws SelectSqlException {
        String query = "SELECT p.idProfessor FROM Professor p " +
                "JOIN Disciplina_Professor dp ON p.idProfessor = dp.idProfessor " +
                "WHERE dp.idDisciplina = ?";
        List<ProfessorModel> professores = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idDisciplina);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idProfessor = rs.getInt("idProfessor");
                    ProfessorModel professor = professorDAO.selectById(idProfessor);
                    professores.add(professor);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao buscar professores por disciplina: " + e.getMessage(), e);
        }
        return professores;
    }
}
