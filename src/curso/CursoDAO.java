package curso;

import connections.DatabaseConnection;
import exceptions.SelectSqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public CursoModel selectById(int idCurso) throws SelectSqlException {
        String query = "SELECT * FROM Curso WHERE idCurso = ?";
        CursoModel cursoModel = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCurso);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cursoModel = new CursoModel();
                    cursoModel.setIdCurso(rs.getInt("idCurso"));
                    cursoModel.setNomeCurso(rs.getString("nomeCurso"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela Curso: " + e.getMessage(), e);
        }
        return cursoModel;
    }

    public List<CursoModel> selectAll() throws SelectSqlException {
        String query = "SELECT * FROM Curso";
        List<CursoModel> cursos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CursoModel cursoModel = new CursoModel();
                cursoModel.setIdCurso(rs.getInt("idCurso"));
                cursoModel.setNomeCurso(rs.getString("nomeCurso"));
                cursos.add(cursoModel);
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao buscar todos os cursos: " + e.getMessage(), e);
        }
        return cursos;
    }
}