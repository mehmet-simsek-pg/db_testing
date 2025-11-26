package model;

public class Department {

    // Department department = new Department();
    // department.getName(); department.detDepartmentName();
    private int id;
    private String name;

    public Department() {
    }

    // Ilk department eklerken id yi database otomatik atadigi icin bu constructor i kullandik
    public Department(String name) {
        this.name = name;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
