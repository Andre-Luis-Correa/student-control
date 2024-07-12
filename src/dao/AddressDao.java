package dao;

import connections.DatabaseConnection;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import model.AddressModel;
import model.PersonModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDao {


    public void insertAddress(AddressModel addressModel) throws InsertSqlException {
        String query = "INSERT INTO address (street, number, cep, city, state) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, addressModel.getStreet());
            stmt.setString(2, addressModel.getNumber());
            stmt.setString(3, addressModel.getCep());
            stmt.setString(4, addressModel.getCity());
            stmt.setString(5, addressModel.getState());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InsertSqlException("Error inserting address", e);
        }
    }

    // Método para recuperar todas as pessoas do banco.
    public List<PersonModel> readAll() throws SelectSqlException {
        // Lista para armazenar as pessoas recuperadas.
        List<PersonModel> pessoas = new ArrayList<>();

        // Query SQL para seleção com JOIN na tabela de endereços.
        String query = "SELECT p.id AS personId, p.name, p.cpf, p.phoneNumber, p.email, "
                + "a.id AS addressId, a.street, a.number, a.cep, a.city, a.state "
                + "FROM person p "
                + "LEFT JOIN address a ON p.idEndereco = a.id";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre o resultado.
            while (rs.next()) {
                // Cria e preenche o objeto AddressModel.
                AddressModel address = new AddressModel();
                address.setId(rs.getInt("addressId"));
                address.setStreet(rs.getString("street"));
                address.setNumber(rs.getString("number"));
                address.setCep(rs.getString("cep"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));

                // Cria e preenche o objeto PersonModel.
                PersonModel person = new PersonModel();
                person.setId(rs.getInt("personId"));
                person.setName(rs.getString("name"));
                person.setCpf(rs.getString("cpf"));
                person.setPhoneNumber(rs.getString("phoneNumber"));
                person.setEmail(rs.getString("email"));
                person.setAddressModel(address);

                // Adiciona a pessoa à lista.
                pessoas.add(person);
            }
        } catch (SQLException e) {
            // Lança uma exceção em caso de erro.
            throw new SelectSqlException("Error selecting people", e);
        }
        // Retorna a lista de pessoas.
        return pessoas;
    }


}
