package dao;

import model.Department;
import util.SqlLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Dao -> Data Access Object(Veri erisim nesnesi)
// Java tasarim deseni. Temel amaci veritabani islemleri
public class DepartmentDao {

    private final Connection connection;

    private static final String INSERT_DEPARTMENT = "sql/departments/insert.sql";
    private static final String SELECT_DEPARTMENT = "sql/departments/select.sql";
    private static final String UPDATE_DEPARTMENT = "sql/departments/update.sql";
    private static final String DELETE_DEPARTMENT = "sql/departments/delete.sql";

    public DepartmentDao(Connection connection) {
        this.connection = connection;
    }

    public int insertDepartment(Department department) {

        String sql = SqlLoader.loadSql(INSERT_DEPARTMENT);

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, department.getName());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("department_id");
                return id;
            } else {
                throw new RuntimeException("Insert yaptiktan sonra id dönmedi");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Department findById(int departmentId) {

        String sql = SqlLoader.loadSql(SELECT_DEPARTMENT);
        Department department = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, departmentId);

            ResultSet rs = ps.executeQuery();

            // department_id yazmamizin sebebi tablodaki sütunda öyle yazdigi icin
            if (rs.next()) {
                department = new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return department;
    }

    public int updateDepartmentName(int departmentId, String newName) {

        String sql = SqlLoader.loadSql(UPDATE_DEPARTMENT);

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, newName);
            ps.setInt(2, departmentId);

            return ps.executeUpdate(); // return olarak executeUpdate int dönüyor
            // O yüzden ResultSet kullanmadik cünkü tablo olusmuyor bu islem sonucunda

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteDepartment(int departmentId) {

        String sql = SqlLoader.loadSql(DELETE_DEPARTMENT);

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, departmentId);

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
