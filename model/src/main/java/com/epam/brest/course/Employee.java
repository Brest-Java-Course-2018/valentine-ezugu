package com.epam.brest.course;

/**
 * POJO Employee for model.
 */
public class Employee {

    /**
     * attribute employeeId
     */
    private Integer employeeId;
    /**
     * attribute employeeName
     */
    private String employeeName;
    /**
     * attribute salary
     */
    private Integer salary;
    /**
     * attribute departmentId
     */
    private Integer departmentId;

    /**
     * Get Employee Id.
     * @return employeeId.
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     *
     * @param employeeId setter
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     *
     * @return employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     *
     * @param employeeName setter
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     *
     * @return salary
     */
    public Integer getSalary() {
        return salary;
    }

    /**
     *
     * @param salary setter
     */
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     *
     * @return departmentId
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     *
     * @param departmentId setter
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     *
     * @return String
     */
    @Override
    public java.lang.String toString() {
        return "Employee{"
              +  "employeeId=" + employeeId
              + ",   employeeName=" + employeeName
              + ", salary=" + salary
              + ", departmentId=" + departmentId
              +  '}';
    }
}