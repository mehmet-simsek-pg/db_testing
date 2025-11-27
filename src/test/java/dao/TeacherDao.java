package dao;

import model.Teacher;
import model.TeacherWithDepartment;
import util.SqlLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDao {

    private final Connection connection;

    private static final String INSERT_TEACHER = "sql/teachers/insert.sql";
    private static final String SELECT_TEACHER = "sql/teachers/select.sql";
    private static final String UPDATE_TEACHER = "sql/teachers/update.sql";
    private static final String DELETE_TEACHER = "sql/teachers/delete.sql";
    private static final String TEACHER_WITH_DEPARTMENT = "sql/teachers/teacher_with_department_name.sql";


    public TeacherDao(Connection connection) {
        this.connection = connection;
    }

    public int insertTeacher(Teacher teacher) throws SQLException{

        String sql = SqlLoader.loadSql(INSERT_TEACHER);
        /** try-with-resources
         try catch bloklari icerisinde bazen acilan classlarin islemi bittiginde
         kapatilmalari gerekir. Bu da performans sorunlarinlarina yol acabiliyor büyük projelerde.
         Java 8 den itibaren try-with-resource yaklasimi gelistirildi. Bu sayede try blogu icerisinde
         kullanilan bir kaynagin isi bittiginde kapatmak icin ekstra close() metodu kullanmaya gerek
         kalmiyor. Kendisi isi bitince otomatik kapaniyor. Bunun kullaniminin projeye en büyük katkisi performans.
        */
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.setString(3, teacher.getEmail());
            ps.setInt(4, teacher.getDepartmentId());

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("teacher_id");
                    teacher.setId(id);
                    return id;
                }
                else {
                    throw new SQLException("Ögretmen eklerken id dönmedi");
                }
            }
        }
    }

    public Teacher findById(int teacherId) throws SQLException {

        String sql = SqlLoader.loadSql(SELECT_TEACHER);
        Teacher teacher = null;

        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1,teacherId);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    teacher = new Teacher(
                            rs.getInt("teacher_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getInt("department_id")
                    );
                }
            }
        }
        return teacher;
    }

    public int updateTeacherEmail(int teacherId, String newEmail) throws SQLException{

        String sql = SqlLoader.loadSql(UPDATE_TEACHER);

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, newEmail);
            ps.setInt(2, teacherId);

           return ps.executeUpdate();
        }
    }

    public int deleteTeacher(int teacherId) throws SQLException {

        String sql = SqlLoader.loadSql(DELETE_TEACHER);

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, teacherId);

           return ps.executeUpdate();
        }
    }

    public TeacherWithDepartment findByIdWithDepartment(int teacherId) throws SQLException {

        String sql = SqlLoader.loadSql(TEACHER_WITH_DEPARTMENT);
        TeacherWithDepartment tc = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, teacherId);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    tc = new TeacherWithDepartment(
                            rs.getInt("teacher_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getInt("department_id"),
                            rs.getString("department_name")
                    );
                }
            }
        }
        return tc;
    }
}
