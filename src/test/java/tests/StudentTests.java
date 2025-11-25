package tests;

import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentTests extends BaseTest{

    @Test
    public void insertAndSelectStudent() {

        String sql = "";

        int generatedId; // Database otomatik id atadigi icin böyle bir degisken tanimladik

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "Baris");
            ps.setString(2, "Cansiz");
            ps.setString(3, "baris@example.com");
            ps.setDate(4, Date.valueOf(LocalDate.of(1998, 12, 3)));
            ps.setString(5, "Male");
            ps.setInt(6, 1); // 1 numarali classroom oldugu icin ekleyebildim bu sekilde

            ResultSet rs = ps.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
