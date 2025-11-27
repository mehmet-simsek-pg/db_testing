package tests;

import dao.DepartmentDao;
import dao.TeacherDao;
import model.Department;
import model.Teacher;
import model.TeacherWithDepartment;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.UUID;

public class TeacherTests extends BaseTest {

    private TeacherDao teacherDao;
    private DepartmentDao departmentDao;

    @BeforeMethod
    public void setUpDao() {
        departmentDao = new DepartmentDao(connection);
        teacherDao = new TeacherDao(connection);
    }

    @Test
    public void insertAndSelectTeacher() throws SQLException {

        Department department = new Department("JDBC Dersleri");

        int departmentId = departmentDao.insertDepartment(department);

        Teacher teacher = new Teacher(
                "Mehmet",
                "Simsek",
                "mehmet." + UUID.randomUUID() + "@example.com",
                departmentId);

        int teacherId = teacherDao.insertTeacher(teacher);
        Assert.assertTrue(teacherId > 0);

        Teacher fromDb = teacherDao.findById(teacherId);

        Assert.assertEquals(fromDb.getFirstname(), teacher.getFirstname(), "Firstname yanlis girilmis");
        Assert.assertEquals(fromDb.getLastname(), teacher.getLastname(), "Lastname yanlis girilmis");
    }

    @Test
    public void updateTeacherEmail() throws SQLException {
        Department department = new Department("SQL abfragen");

        int departmentId = departmentDao.insertDepartment(department);

        String oldEmail = "m." + UUID.randomUUID() + "@example.com";

        Teacher teacher = new Teacher(
                "Mehmet",
                "Simsek",
                oldEmail,
                departmentId
        );

        int teacherId = teacherDao.insertTeacher(teacher);

        String newEmail = "mehmet@example.com";

        int row = teacherDao.updateTeacherEmail(teacherId, newEmail);
        Assert.assertEquals(row, 1, "Email güncellenemedi");

        Teacher fromDb = teacherDao.findById(teacherId);

        Assert.assertEquals(fromDb.getEmail(), newEmail, "Email yanlis güncellendi");
    }

    @Test
    public void deleteTeacher() throws SQLException {

        Department department = new Department("JDBC tables");

        int departmentId = departmentDao.insertDepartment(department);

        Teacher teacher = new Teacher(
                "mehmet",
                "simsek",
                "mehmetsimsek@example.com",
                departmentId
        );

        int teacherId = teacherDao.insertTeacher(teacher);
        Assert.assertTrue( teacherId > 0);

        int row = teacherDao.deleteTeacher(teacherId);
        Assert.assertEquals(row, 1, "Teacher silinemedi");
    }

    @Test
    public void findByIdWithDepartment() throws SQLException {

        Department department = new Department("Batch 8 ucuyor");

        int departmentId = departmentDao.insertDepartment(department);

        Teacher teacher = new Teacher(
                "Mehmet",
                "Simsek",
                "m.s" + UUID.randomUUID() +"@example.com",
                departmentId
        );

        int teacherId = teacherDao.insertTeacher(teacher);

        TeacherWithDepartment tc = teacherDao.findByIdWithDepartment(teacherId);

        Assert.assertEquals(tc.getFirstname(), teacher.getFirstname());
        Assert.assertEquals(tc.getLastname(), teacher.getLastname());
        Assert.assertEquals(tc.getDepartmentName(), department.getName());
    }

}
