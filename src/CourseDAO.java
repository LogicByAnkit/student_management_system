import java.sql.*;
import java.util.ArrayList;

public class CourseDAO {

    // Reason: every method here needs a connection, so we centralize it
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student_management";
        String username = "root";
        String password = "type your password";
        return DriverManager.getConnection(url, username, password);
    }

    // Adds one course to the database
    public void addCourse(Course c) {
        String sql = "INSERT INTO course (course_code, course_name, course_fee) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.courseCode);
            ps.setString(2, c.courseName);
            ps.setDouble(3, c.courseFee);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetches all courses from the database
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Course c = new Course(
                    rs.getString("course_name"),
                    rs.getInt("course_code"),
                    (int) rs.getDouble("course_fee")
                );
                courses.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    public Course getCourseByCode(int code) {
    String sql = "SELECT * FROM course WHERE course_code = ?";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, code);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Course(rs.getString("course_name"), rs.getInt("course_code"), (int) rs.getDouble("course_fee"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}
