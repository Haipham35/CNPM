package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Lớp SqlConnection cung cấp phương thức để kết nối đến cơ sở dữ liệu MySQL.
 */
public class SqlConnection {
    private final static String URL = "jdbc:sqlserver://sqldatabasestudent.database.windows.net:1433;databaseName=QLThuPhiDB;";
    private final static String USERNAME = "sqladmin";
    private final static String PASSWORD = "Mk123456";
    
    /**
     * Phương thức này tạo và trả về một đối tượng Connection để kết nối đến cơ sở dữ liệu MySQL.
     *
     * @return Đối tượng Connection cho kết nối đến cơ sở dữ liệu.
     */
    public static Connection connect() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch (Exception e) {

        }
        // Tạo đối tượng Properties để cấu hình kết nối.
        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("encrypt", "true"); // Enable SSL
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(URL, properties);
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
        
        return connection;
    }
}
