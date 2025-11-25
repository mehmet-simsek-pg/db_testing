package tests;

import java.sql.*;

public class BasicConnectionTest {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/schooldb";
        // url -> jdbc + database + server + port + database name
        String user = "postgres";
        String password = "postgres";

        // Database baglandik
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "SELECT * FROM students"; // sorguyu yazdik
        // Java nin JDBC üzerinden database'e sorgu gönderilmesini saglayan classtir
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql); // sorguyu calistirdik

        while (resultSet.next()) { // tabloda kac veri oldugunu bilmedigim icin
            // while döngüsü kullaniyoruz.
            // Database deki tablodan gelen verileri ekrana yazdirdik
            System.out.println(
                    resultSet.getInt("student_id") + " - " +
                            resultSet.getString("first_name") + " - " +
                            resultSet.getString("last_name") + " - " +
                            resultSet.getString("email") + " - " +
                            resultSet.getDate("birth_date")
            );
        }

    }
}
