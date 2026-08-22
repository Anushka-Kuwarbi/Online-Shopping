package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static String url = "jdbc:mysql://localhost:3306/online_shopping";
    static String username = "root";
    static String password = "root@234";
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully!");
            return con;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
