package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentTests extends BaseTest {

    @Test
    public void insertAndSelectStudent() {

        String insertSql = "INSERT INTO students(first_name, last_name, email, birth_date, gender, classroom_id)" +
                " VALUES(?,?,?,?,?,?) RETURNING student_id";

        int generatedId; // Database otomatik id atadigi icin böyle bir degisken tanimladik

        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);

            ps.setString(1, "Baris");
            ps.setString(2, "Cansiz");
            ps.setString(3, "bariss2@example.com");
            ps.setDate(4, Date.valueOf(LocalDate.of(1998, 12, 3)));
            ps.setString(5, "Male");
            ps.setInt(6, 1); // 1 numarali classroom oldugu icin ekleyebildim bu sekilde

            ResultSet rs = ps.executeQuery();
            Assert.assertTrue(rs.next(), "Insert yaptiktan sonra result set bos olamaz");
            generatedId = rs.getInt("student_id");

            ps.close();
            rs.close();

            String selectSql = "SELECT student_id, first_name, last_name, email, birth_date, gender, classroom_id " +
                    "FROM students WHERE student_id = ?";

            PreparedStatement ps2 = connection.prepareStatement(selectSql);

            ps2.setInt(1, generatedId);
            ResultSet rs2 = ps2.executeQuery();

            Assert.assertTrue(rs2.next(), "insert ten sonra ögrenci eklenmis olmali");
            Assert.assertEquals(rs2.getString("first_name"), "Baris", "Ögrenci ismi yazilmamis");
            Assert.assertEquals(rs2.getString("last_name"), "Cansiz", "Ögrenci soyismi yazilmamis");

            ps2.close();
            rs2.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void insertAndDeleteStudent() {
        String insertSql = "INSERT INTO students(first_name, last_name, email, birth_date, gender, classroom_id)" +
                " VALUES(?,?,?,?,?,?) RETURNING student_id";

        int generatedId;

        try {
            PreparedStatement ps1 = connection.prepareStatement(insertSql);

            ps1.setString(1, "Merve");
            ps1.setString(2, "Celebi");
            ps1.setString(3, "merve@example.com");
            ps1.setDate(4, Date.valueOf(LocalDate.of(2000, 1,23)));
            ps1.setString(5, "Female");
            ps1.setInt(6, 1);

            ResultSet rs1 = ps1.executeQuery();

            Assert.assertTrue(rs1.next(), "Yeni ögrenci eklenmis olmali");
            // Bu satirda id yi aliyoruz. Yeni ögrenci olustuktan sonra database ona hangi id yi atadi ise
            generatedId = rs1.getInt("student_id");

            String deleteSql = "DELETE FROM students WHERE student_id = ?";

            PreparedStatement ps2 = connection.prepareStatement(deleteSql);

            ps2.setInt(1, generatedId); // WHERE student_id = generatedId olsun diye bu satir var
            int row = ps2.executeUpdate(); // executeQuery yerine delete isleminde executeUpdate kullandik
            // Silme islemi sonunda pgAdmin den kontrol ettik sonuc kisminda 1 gördük
            // Assert ettigimiz kisimda sonuc icerisinde 1 var mi
            Assert.assertEquals(row, 1, "Tablodan 1 satir silinmis olmali");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
