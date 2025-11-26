package model;

public class Teacher {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int departmentId;

    public Teacher() {
    }

    public Teacher(String firstname, String lastname, String email, int departmentId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.departmentId = departmentId;
    }

    public Teacher(int id, String firstname, String lastname, String email, int departmentId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
