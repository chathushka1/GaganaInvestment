package lk.ijse.microfinance.db;

import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class DBConnection {
        private static DBConnection dbConnection;
        private Connection connection;

        private DBConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/GaganaInvestment", "root", "1234");
        }

        public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
            return (null == dbConnection) ? new DBConnection() : dbConnection;
        }
        public Connection getConnection() {
            return connection;
        }
    
    }

