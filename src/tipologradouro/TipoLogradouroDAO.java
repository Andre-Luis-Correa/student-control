package tipologradouro;

import connections.DatabaseConnection;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoLogradouroDAO {

    public String insert(TipoLogradouroModel tipoLogradouro) throws InsertSqlException {
        String query = "INSERT INTO tipologradouro (siglaTipoLogradouro, nomeTipoLogradouro) VALUES (?, ?)";
        String siglaTipoLogradouro = tipoLogradouro.getSiglaTipoLogradouro();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tipoLogradouro.getSiglaTipoLogradouro());
            stmt.setString(2, tipoLogradouro.getNomeTipoLogradouro());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        siglaTipoLogradouro = rs.getString(1);
                    }
                }
            } else {
                throw new InsertSqlException("Erro ao inserir tipo de logradouro, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir tipo de logradouro", e);
        }

        return siglaTipoLogradouro;
    }

    public TipoLogradouroModel selectById(String siglaTipoLogradouro) throws SelectSqlException {
        String query = "SELECT * FROM tipologradouro WHERE siglaTipoLogradouro = ?";
        TipoLogradouroModel tipoLogradouro = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, siglaTipoLogradouro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tipoLogradouro = new TipoLogradouroModel();
                    tipoLogradouro.setSiglaTipoLogradouro(rs.getString("siglaTipoLogradouro"));
                    tipoLogradouro.setNomeTipoLogradouro(rs.getString("nomeTipoLogradouro"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela tipologradouro", e);
        }

        return tipoLogradouro;
    }

    public List<TipoLogradouroModel> selectAll() throws SelectSqlException {
        String query = "SELECT * FROM tipologradouro";
        List<TipoLogradouroModel> tiposLogradouro = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoLogradouroModel tipoLogradouro = new TipoLogradouroModel();
                tipoLogradouro.setSiglaTipoLogradouro(rs.getString("siglatipologradouro"));
                tipoLogradouro.setNomeTipoLogradouro(rs.getString("nometipologradouro"));
                tiposLogradouro.add(tipoLogradouro);
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela tipologradouro", e);
        }

        return tiposLogradouro;
    }

    public TipoLogradouroModel selectBySigla(String sigla) throws SelectSqlException {
        String query = "SELECT * FROM tipologradouro WHERE siglatipologradouro = ?";
        TipoLogradouroModel tipoLogradouro = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, sigla);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tipoLogradouro = new TipoLogradouroModel();
                    tipoLogradouro.setSiglaTipoLogradouro(rs.getString("siglatipologradouro"));
                    tipoLogradouro.setNomeTipoLogradouro(rs.getString("nometipologradouro"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela tipologradouro", e);
        }

        return tipoLogradouro;
    }
}
