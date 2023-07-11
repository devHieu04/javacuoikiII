package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static String nameDatabase = "quanlithuvien";
    public static String userDatabase = "nguyenduyhieu";
    public static String passDatabase = "";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/" + nameDatabase;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            String username = userDatabase;
            String password = passDatabase;
            conn = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Kết nối thành công");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Kết nối thất bại!");
            ex.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        ConnectDB connectDB = new ConnectDB();
        connectDB.getConnection();

    }
}

