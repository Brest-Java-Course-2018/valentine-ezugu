package com.epam.brest.course;

/**
 *
 *POJO Department model
 */
public class Department {

    private Integer department;

    private String departmentName;

    private String description;

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Department{" +
                "department=" + department +
                ", departmentName='" + departmentName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
