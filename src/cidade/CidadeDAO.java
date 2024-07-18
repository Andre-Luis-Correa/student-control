package cidade;

import connections.DatabaseConnection;
import unidadefederacao.UnidadeFederacaoDAO;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import unidadefederacao.UnidadeFederacaoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CidadeDAO {

    private UnidadeFederacaoDAO unidadeFederacaoDAO;

    public CidadeDAO() {
        this.unidadeFederacaoDAO = new UnidadeFederacaoDAO();
    }

    public int insert(CidadeModel cidade) throws InsertSqlException {
        String query = "INSERT INTO cidade (nomeCidade, siglaUF) VALUES (?, ?)";
        int idCidade = 0;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cidade.getNomeCidade());
            stmt.setString(2, cidade.getUnidadeFederacaoModel().getSiglaUF());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idCidade = rs.getInt(1);
                    }
                }
            } else {
                throw new InsertSqlException("Erro ao inserir cidade, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir cidade", e);
        }

        return idCidade;
    }

    public CidadeModel selectById(int idCidade) throws SelectSqlException {
        String query = "SELECT * FROM cidade WHERE idCidade = ?";
        CidadeModel cidade = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCidade);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cidade = new CidadeModel();
                    cidade.setIdCidade(rs.getInt("idCidade"));
                    cidade.setNomeCidade(rs.getString("nomeCidade"));

                    UnidadeFederacaoModel unidadeFederacao = unidadeFederacaoDAO.selectBySigla(rs.getString("siglaUF"));
                    cidade.setUnidadeFederacaoModel(unidadeFederacao);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela cidade", e);
        }

        return cidade;
    }
}
