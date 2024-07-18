package logradouro;

import connections.DatabaseConnection;
import tipologradouro.TipoLogradouroDAO;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import tipologradouro.TipoLogradouroModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogradouroDAO {

    private TipoLogradouroDAO tipoLogradouroDAO;

    public LogradouroDAO() {
        this.tipoLogradouroDAO = new TipoLogradouroDAO();
    }

    public int insert(LogradouroModel logradouro) throws InsertSqlException {
        String query = "INSERT INTO logradouro (nomeLogradouro, siglaTipoLogradouro) VALUES (?, ?)";
        int idLogradouro = 0;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, logradouro.getNomeLogradouro());
            stmt.setString(2, logradouro.getTipoLogradouroModel().getSiglaTipoLogradouro());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idLogradouro = rs.getInt(1);
                    }
                }
            } else {
                throw new InsertSqlException("Erro ao inserir logradouro, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir logradouro", e);
        }

        return idLogradouro;
    }

    public LogradouroModel selectById(int idLogradouro) throws SelectSqlException {
        String query = "SELECT * FROM logradouro WHERE idLogradouro = ?";
        LogradouroModel logradouro = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idLogradouro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    logradouro = new LogradouroModel();
                    logradouro.setIdLogradouro(rs.getInt("idLogradouro"));
                    logradouro.setNomeLogradouro(rs.getString("nomeLogradouro"));

                    TipoLogradouroModel tipoLogradouro = tipoLogradouroDAO.selectById(rs.getString("siglaTipoLogradouro"));
                    logradouro.setTipoLogradouroModel(tipoLogradouro);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela logradouro", e);
        }

        return logradouro;
    }
}
