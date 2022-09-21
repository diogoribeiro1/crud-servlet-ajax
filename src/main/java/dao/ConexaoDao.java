package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {

    public Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/crud-jsp?user=root&password=123";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return conn;
    }


}

