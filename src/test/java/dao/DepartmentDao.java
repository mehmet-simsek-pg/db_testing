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


}
