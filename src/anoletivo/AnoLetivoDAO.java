package anoletivo;

import connections.DatabaseConnection;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnoLetivoDAO {

    public AnoLetivoModel selectByAnoLetivo(int anoLetivo) throws SelectSqlException {
        String query = "SELECT * FROM AnoLetivo WHERE anoLetivo = ?";
        AnoLetivoModel anoLetivoModel = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, anoLetivo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    anoLetivoModel = new AnoLetivoModel();
                    anoLetivoModel.setIdAnoLetivo(rs.getInt("idAnoLetivo"));
                    anoLetivoModel.setAnoLetivo(rs.getInt("anoLetivo"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela AnoLetivo: " + e.getMessage(), e);
        }
        return anoLetivoModel;
    }

    public AnoLetivoModel selectById(int idAnoLetivo) throws SelectSqlException {
        String query = "SELECT * FROM AnoLetivo WHERE idAnoLetivo = ?";
        AnoLetivoModel anoLetivoModel = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAnoLetivo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    anoLetivoModel = new AnoLetivoModel();
                    anoLetivoModel.setIdAnoLetivo(rs.getInt("idAnoLetivo"));
                    anoLetivoModel.setAnoLetivo(rs.getInt("anoLetivo"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela AnoLetivo: " + e.getMessage(), e);
        }
        return anoLetivoModel;
    }

    public List<AnoLetivoModel> selectAll() throws SelectSqlException {
        String query = "SELECT * FROM AnoLetivo";
        List<AnoLetivoModel> anosLetivos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AnoLetivoModel anoLetivoModel = new AnoLetivoModel();
                anoLetivoModel.setIdAnoLetivo(rs.getInt("idAnoLetivo"));
                anoLetivoModel.setAnoLetivo(rs.getInt("anoLetivo"));
                anosLetivos.add(anoLetivoModel);
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao buscar todos os anos letivos: " + e.getMessage(), e);
        }
        return anosLetivos;
    }

    public void insert(AnoLetivoModel anoLetivoModel) throws InsertSqlException {
        String query = "INSERT INTO AnoLetivo (anoLetivo) VALUES (?)";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, anoLetivoModel.getAnoLetivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir ano letivo: " + e.getMessage(), e);
        }
    }
}
