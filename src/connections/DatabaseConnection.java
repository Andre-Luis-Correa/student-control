package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //Colocar o nome do banco de dados que você criou
    private static final String URL = "jdbc:mysql://localhost:3306/controle_estudantes";
    //Colocar o usuario que loga no MySQL Workbench
    private static final String USER = "root";
    //Colocar a senha que loga no MySQL Workbench
    private static final String PASSWORD = "trovaoA123?";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL driver", e);
        }
    }

    public static Connection GetConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
