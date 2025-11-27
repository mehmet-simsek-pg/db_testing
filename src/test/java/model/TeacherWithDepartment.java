package model;

// Iki tabloyu birlestirdigimiz icin yeni bir modele ihtiyac duyduk
// Cünkü modelleri olusan tabloya göre olusturuyoruz.
// Bu yapiya DTO denir(Data Transfer Object)
public class TeacherWithDepartment {

    private int teacherId;
    private String firstname;
    private String lastname;
    private String email;
    private int departmentId;
    private String departmentName;

    public TeacherWithDepartment() {
    }

    public TeacherWithDepartment(int teacherId, String firstname, String lastname, String email, int departmentId, String departmentName) {
        this.teacherId = teacherId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "TeacherWithDepartment{" +
                "teacherId=" + teacherId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
