package dao;

import connections.DatabaseConnection;
import exceptions.InsertSqlException;
import model.AddressModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDao {


    public void insertAddress(AddressModel addressModel) throws InsertSqlException {
        String sql = "INSERT INTO address (street, number, cep, city, state) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

}
