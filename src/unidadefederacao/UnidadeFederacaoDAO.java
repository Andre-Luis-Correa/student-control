package unidadefederacao;

import connections.DatabaseConnection;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeFederacaoDAO {

    public String insert(UnidadeFederacaoModel unidadeFederacao) throws InsertSqlException {
        String query = "INSERT INTO UnidadeFederacao (siglaUF, nomeUF) VALUES (?, ?)";
        String siglaUF = unidadeFederacao.getSiglaUF();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, siglaUF);
            stmt.setString(2, unidadeFederacao.getNomeUF());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new InsertSqlException("Erro ao inserir unidade federativa, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir unidade federativa", e);
        }

        return siglaUF;
    }

    public UnidadeFederacaoModel selectBySigla(String siglaUF) throws SelectSqlException {
        String query = "SELECT * FROM unidadefederacao WHERE siglaUF = ?";
        UnidadeFederacaoModel unidadeFederacao = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, siglaUF);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    unidadeFederacao = new UnidadeFederacaoModel();
                    unidadeFederacao.setSiglaUF(rs.getString("siglaUF"));
                    unidadeFederacao.setNomeUF(rs.getString("nomeUF"));
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela unidadefederacao", e);
        }

        return unidadeFederacao;
    }

    public List<UnidadeFederacaoModel> selectAll() throws SelectSqlException {
        String query = "SELECT * FROM unidadefederacao";
        List<UnidadeFederacaoModel> unidadesFederativas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UnidadeFederacaoModel unidadeFederacao = new UnidadeFederacaoModel();
                unidadeFederacao.setSiglaUF(rs.getString("siglauf"));
                unidadeFederacao.setNomeUF(rs.getString("nomeuf"));
                unidadesFederativas.add(unidadeFederacao);
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela unidadefederacao", e);
        }

        return unidadesFederativas;
    }
}
