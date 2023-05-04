package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {

    public Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // String url = "jdbc:mysql://localhost:3306/crud-jsp?user=root&password=123";
            // conn = DriverManager.getConnection(url);
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return conn;
    }
}