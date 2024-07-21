package matriculaaluno;

import aluno.AlunoDAO;
import aluno.AlunoModel;
import anoletivo.AnoLetivoDAO;
import anoletivo.AnoLetivoModel;
import connections.DatabaseConnection;
import curso.CursoDAO;
import curso.CursoModel;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaAlunoDAO {

    public void insert(MatriculaAlunoModel matriculaAlunoModel) throws InsertSqlException {
        String queryMatricula = "INSERT INTO MatriculaAluno (dtMatricula, idAluno, idCurso, statusMatricula) VALUES (?, ?, ?, ?)";
        String queryMatriculaAnoLetivo = "INSERT INTO MatriculaAluno_AnoLetivo (nroMatricula, idAnoLetivo) VALUES (?, ?)";
        String queryMatriculaDisciplina = "INSERT INTO MatriculaAluno_Disciplina (nroMatricula, idDisciplina) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.GetConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtMatricula = conn.prepareStatement(queryMatricula, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement stmtMatriculaAnoLetivo = conn.prepareStatement(queryMatriculaAnoLetivo);
                 PreparedStatement stmtMatriculaDisciplina = conn.prepareStatement(queryMatriculaDisciplina)) {

                // Inserir matrícula
                stmtMatricula.setDate(1, java.sql.Date.valueOf(matriculaAlunoModel.getDtMatricula()));
                stmtMatricula.setInt(2, matriculaAlunoModel.getAlunoModel().getIdAluno());
                stmtMatricula.setInt(3, matriculaAlunoModel.getCursoModel().getIdCurso());
                stmtMatricula.setBoolean(4, matriculaAlunoModel.isStatusMatricula());
                stmtMatricula.executeUpdate();

                // Obter número da matrícula gerado
                ResultSet generatedKeys = stmtMatricula.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int nroMatricula = generatedKeys.getInt(1);
                    matriculaAlunoModel.setNroMatricula(nroMatricula);
                }

                // Inserir matrícula ano letivo
                stmtMatriculaAnoLetivo.setInt(1, matriculaAlunoModel.getNroMatricula());
                stmtMatriculaAnoLetivo.setInt(2, matriculaAlunoModel.getAnoLetivoModel().getIdAnoLetivo());
                stmtMatriculaAnoLetivo.executeUpdate();

                // Inserir disciplinas
                for (int idDisciplina : matriculaAlunoModel.getDisciplinas()) {
                    stmtMatriculaDisciplina.setInt(1, matriculaAlunoModel.getNroMatricula());
                    stmtMatriculaDisciplina.setInt(2, idDisciplina);
                    stmtMatriculaDisciplina.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new InsertSqlException("Error inserting matricula: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new InsertSqlException("Error inserting matricula: " + e.getMessage(), e);
        }
    }

    public void desativarMatriculasAnteriores(int idAluno) throws SelectSqlException {
        String query = "UPDATE MatriculaAluno SET statusMatricula = FALSE WHERE idAluno = ? AND statusMatricula = TRUE";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAluno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao desativar matrículas anteriores: " + e.getMessage(), e);
        }
    }

    public MatriculaAlunoModel selectByNroMatricula(int nroMatricula) throws SelectSqlException {
        String query = "SELECT * FROM MatriculaAluno WHERE nroMatricula = ?";
        MatriculaAlunoModel matriculaAlunoModel = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nroMatricula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    matriculaAlunoModel = new MatriculaAlunoModel();
                    matriculaAlunoModel.setNroMatricula(rs.getInt("nroMatricula"));
                    matriculaAlunoModel.setDtMatricula(rs.getDate("dtMatricula").toLocalDate());
                    matriculaAlunoModel.setStatusMatricula(rs.getBoolean("statusMatricula"));

                    int idAluno = rs.getInt("idAluno");
                    AlunoDAO alunoDAO = new AlunoDAO();
                    AlunoModel alunoModel = alunoDAO.selectById(idAluno);
                    matriculaAlunoModel.setAlunoModel(alunoModel);

                    int idCurso = rs.getInt("idCurso");
                    CursoDAO cursoDAO = new CursoDAO();
                    CursoModel cursoModel = cursoDAO.selectById(idCurso);
                    matriculaAlunoModel.setCursoModel(cursoModel);

                    int idAnoLetivo = this.selectAnoLetivoByNroMatricula(nroMatricula);
                    AnoLetivoDAO anoLetivoDAO = new AnoLetivoDAO();
                    AnoLetivoModel anoLetivoModel = anoLetivoDAO.selectById(idAnoLetivo);
                    matriculaAlunoModel.setAnoLetivoModel(anoLetivoModel);

                    matriculaAlunoModel.setDisciplinas(this.selectDisciplinasByNroMatricula(nroMatricula));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela MatriculaAluno: " + e.getMessage(), e);
        }
        return matriculaAlunoModel;
    }

    private int selectAnoLetivoByNroMatricula(int nroMatricula) throws SelectSqlException {
        String query = "SELECT idAnoLetivo FROM MatriculaAluno_AnoLetivo WHERE nroMatricula = ?";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nroMatricula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idAnoLetivo");
                } else {
                    throw new SelectSqlException("Nenhum ano letivo encontrado para a matrícula " + nroMatricula);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela MatriculaAluno_AnoLetivo: " + e.getMessage(), e);
        }
    }

    public List<Integer> selectDisciplinasByNroMatricula(int nroMatricula) throws SelectSqlException {
        String query = "SELECT idDisciplina FROM MatriculaAluno_Disciplina WHERE nroMatricula = ?";
        List<Integer> disciplinas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nroMatricula);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    disciplinas.add(rs.getInt("idDisciplina"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela MatriculaAluno_Disciplina: " + e.getMessage(), e);
        }
        return disciplinas;
    }

    public List<MatriculaAlunoModel> selectByAluno(int idAluno) throws SelectSqlException {
        String query = "SELECT * FROM MatriculaAluno WHERE idAluno = ?";
        List<MatriculaAlunoModel> matriculas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAluno);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MatriculaAlunoModel matriculaAlunoModel = new MatriculaAlunoModel();
                    matriculaAlunoModel.setNroMatricula(rs.getInt("nroMatricula"));
                    matriculaAlunoModel.setDtMatricula(rs.getDate("dtMatricula").toLocalDate());
                    matriculaAlunoModel.setStatusMatricula(rs.getBoolean("statusMatricula"));

                    AlunoDAO alunoDAO = new AlunoDAO();
                    AlunoModel alunoModel = alunoDAO.selectById(idAluno);
                    matriculaAlunoModel.setAlunoModel(alunoModel);

                    int idCurso = rs.getInt("idCurso");
                    CursoDAO cursoDAO = new CursoDAO();
                    CursoModel cursoModel = cursoDAO.selectById(idCurso);
                    matriculaAlunoModel.setCursoModel(cursoModel);

                    int idAnoLetivo = this.selectAnoLetivoByNroMatricula(matriculaAlunoModel.getNroMatricula());
                    AnoLetivoDAO anoLetivoDAO = new AnoLetivoDAO();
                    AnoLetivoModel anoLetivoModel = anoLetivoDAO.selectById(idAnoLetivo);
                    matriculaAlunoModel.setAnoLetivoModel(anoLetivoModel);

                    matriculaAlunoModel.setDisciplinas(this.selectDisciplinasByNroMatricula(matriculaAlunoModel.getNroMatricula()));

                    matriculas.add(matriculaAlunoModel);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela MatriculaAluno: " + e.getMessage(), e);
        }
        return matriculas;
    }

    public List<Integer> selectDisciplinasCursadasPorAluno(int idAluno) throws SelectSqlException {
        String query = "SELECT idDisciplina FROM MatriculaAluno_Disciplina d " +
                "JOIN MatriculaAluno m ON d.nroMatricula = m.nroMatricula " +
                "WHERE m.idAluno = ?";
        List<Integer> disciplinas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAluno);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    disciplinas.add(rs.getInt("idDisciplina"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela MatriculaAluno_Disciplina: " + e.getMessage(), e);
        }
        return disciplinas;
    }

    public boolean isAlunoMatriculadoNoAno(int idAluno, int idAnoLetivo) throws SelectSqlException {
        String query = "SELECT COUNT(*) AS count FROM MatriculaAluno_AnoLetivo a " +
                "JOIN MatriculaAluno m ON a.nroMatricula = m.nroMatricula " +
                "WHERE m.idAluno = ? AND a.idAnoLetivo = ?";
        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAluno);
            stmt.setInt(2, idAnoLetivo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao verificar matrícula do aluno no ano letivo: " + e.getMessage(), e);
        }
    }

    public int generateNroMatricula() throws SelectSqlException {
        String query = "SELECT MAX(nroMatricula) AS maxMatricula FROM MatriculaAluno";
        int maxMatricula = 0;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                maxMatricula = rs.getInt("maxMatricula");
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao gerar número de matrícula: " + e.getMessage(), e);
        }
        return maxMatricula + 1;
    }

}
