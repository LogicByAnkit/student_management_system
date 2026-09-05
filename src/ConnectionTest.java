import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_management";
        String username = "root";
        String password = "@23Ug57741"; // replace with your actual root password

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}