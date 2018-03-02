package com.epam.brest.course;

/**
 * POJO Employee for model.
 */
public class Employee {

    /**
     * attribute employeeId.
     */
    private Integer employeeId;
    /**
     * attribute employeeName.
     */
    private String employeeName;
    /**
     * attribute salary.
     */
    private Integer salary;
    /**
     * attribute departmentId.
     */
    private Integer deptId;

    /**
     * Get Employee Id.
     * @return employeeId.
     */
    public final Integer getEmployeeId() {
        return employeeId;
    }

    /**
     *
     * @param employeeId setter.
     */
    public final void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     *
     * @return employeeName.
     */
    public final String getEmployeeName() {
        return employeeName;
    }

    /**
     *
     * @param employeeName setter.
     */
    public final void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     *
     * @return salary.
     */
    public final Integer getSalary() {
        return salary;
    }

    /**
     *
     * @param salary setter.
     */
    public final void setSalary(final Integer salary) {
        this.salary = salary;
    }

    /**
     *
     * @return departmentId.
     */
    public final Integer getDepartmentId() {
        return deptId;
    }

    /**
     *
     * @param departmentId setter
     */
    public final void setDepartmentId(final Integer departmentId) {
        this.deptId = departmentId;
    }

    /**
     *
     * @return String
     */
    @Override
    public final java.lang.String toString() {
        return "Employee{"
              +  "employeeId=" + employeeId
              + ",   employeeName=" + employeeName
              + ", salary=" + salary
              + ", departmentId=" + deptId
              +  '}';
    }
}