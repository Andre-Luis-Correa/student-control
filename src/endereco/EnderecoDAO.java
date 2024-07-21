package endereco;

import bairro.BairroDAO;
import cidade.CidadeDAO;
import connections.DatabaseConnection;
import logradouro.LogradouroDAO;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import bairro.BairroModel;
import cidade.CidadeModel;
import logradouro.LogradouroModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO {

    private LogradouroDAO logradouroDAO;
    private CidadeDAO cidadeDAO;
    private BairroDAO bairroDAO;

    public EnderecoDAO() {
        this.logradouroDAO = new LogradouroDAO();
        this.cidadeDAO = new CidadeDAO();
        this.bairroDAO = new BairroDAO();
    }

    public int insert(EnderecoModel endereco) throws InsertSqlException, SelectSqlException {
        // Verificar se o endereço já existe
        int idEnderecoExistente = getIdByEndereco(endereco);
        if (idEnderecoExistente > 0) {
            return idEnderecoExistente;
        }

        String query = "INSERT INTO endereco (cepEndereco, idBairro, idCidade, idLogradouro) VALUES (?, ? ,? ,?)";
        int idEndereco = 0;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, endereco.getCepEndereco());
            stmt.setInt(2, endereco.getBairroModel().getIdBairro());
            stmt.setInt(3, endereco.getCidadeModel().getIdCidade());
            stmt.setInt(4, endereco.getLogradouroModel().getIdLogradouro());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idEndereco = rs.getInt(1);
                    }
                }
            } else {
                throw new InsertSqlException("Erro ao inserir endereço, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new InsertSqlException("Erro ao inserir endereço", e);
        }

        return idEndereco;
    }

    private int getIdByEndereco(EnderecoModel endereco) throws SelectSqlException {
        String query = "SELECT idEndereco FROM endereco WHERE cepEndereco = ? AND idBairro = ? AND idCidade = ? AND idLogradouro = ?";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, endereco.getCepEndereco());
            stmt.setInt(2, endereco.getBairroModel().getIdBairro());
            stmt.setInt(3, endereco.getCidadeModel().getIdCidade());
            stmt.setInt(4, endereco.getLogradouroModel().getIdLogradouro());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idEndereco");
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao verificar existência do endereço", e);
        }

        return 0; // Endereço não encontrado
    }

    public EnderecoModel selectById(int idEndereco) throws SelectSqlException {
        String query = "SELECT * FROM endereco WHERE idEndereco = ?";
        EnderecoModel endereco = null;

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idEndereco);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    endereco = new EnderecoModel();
                    endereco.setIdEndereco(rs.getInt("idEndereco"));
                    endereco.setCepEndereco(rs.getString("cepEndereco"));

                    LogradouroModel logradouro = logradouroDAO.selectById(rs.getInt("idLogradouro"));
                    endereco.setLogradouroModel(logradouro);

                    CidadeModel cidade = cidadeDAO.selectById(rs.getInt("idCidade"));
                    endereco.setCidadeModel(cidade);

                    BairroModel bairro = bairroDAO.selectById(rs.getInt("idBairro"));
                    endereco.setBairroModel(bairro);
                }
            }
        } catch (SQLException e) {
            throw new SelectSqlException("Erro ao realizar select na tabela endereco", e);
        }

        return endereco;
    }
}
