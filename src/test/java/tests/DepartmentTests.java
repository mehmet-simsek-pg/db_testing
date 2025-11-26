package tests;

import dao.DepartmentDao;
import model.Department;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DepartmentTests extends BaseTest{

    private DepartmentDao departmentDao;

    @BeforeMethod
    public void setUpDao() {
        departmentDao = new DepartmentDao(connection);
    }

    @Test
    public void insertAndSelectDepartment(){
        Department department = new Department("Computer Science");

        // Database deki department tablosuna yeni bir department ekledi
        // ve olusan id yi döndü
        int id = departmentDao.insertDepartment(department);

        Assert.assertTrue(id > 0); // Eger yeni department eklendiyse id 0 dan büyük bir sayi olmustur.

        // Bu satirda select islemi sonucu gelen department i nesneye aktardik test icin
        Department fromDB = departmentDao.findById(id);

        Assert.assertEquals(fromDB.getName(), department.getName(), "Yanlis isim ile kayit yapildi");
    }

    @Test
    public void updateDepartmentName() {
        Department department = new Department("JDBC structure");

        int id = departmentDao.insertDepartment(department);
        Assert.assertTrue(id > 0);

        String newName = "Mobile Testing";

        int row = departmentDao.updateDepartmentName(id, newName);

        Assert.assertEquals(row, 1, "Department ismi degismedi");
    }

    @Test
    public void deleteDepartment() {

        Department department = new Department("CI/CD Pipeline");

        int id = departmentDao.insertDepartment(department);
        Assert.assertTrue(id > 0);

        int row = departmentDao.deleteDepartment(id);
        Assert.assertEquals(row, 1, "Department silinmedi");
    }
}
