import java.sql.*;

public class Database {
    private String url = "jdbc:mysql://localhost:3306/scrum_escape_building";
    private String username = "root";
    private String password = "AGao2005.";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Databaseverbinding mislukt: " + e.getMessage());
            return null;
        }
    }
}

class databaseInsert extends Database{

}