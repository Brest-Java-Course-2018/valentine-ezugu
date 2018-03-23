package com.epam.brest.course.model;

/**
 * POJO Employee for model.
 *
 * @author valentine
 * @version $Id: $1
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
    private Integer departmentId;

    /**
     * attribute email.
     */
     private String email;

    /**
     * Constructor.
     * @param id id.
     * @param name employeeName.
     * @param salaryToStart salary.
     * @param deptId department.
     */
    public Employee(final Integer id, final String name,
                    final Integer salaryToStart, final Integer deptId) {
        this.employeeId = id;
        this.employeeName = name;
        this.salary = salaryToStart;
        this.departmentId = deptId;
    }

    /**
     * default constructor.
     */
    public Employee() {
    }

    /**
     * Get Employee Id.
     * @return employeeId.
     */
    public final Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * @param id setter.
     */
    public final void setEmployeeId(final Integer id) {
        this.employeeId = id;
    }

    /**
     * <p>Getter for the field <code>employeeName</code>.</p>
     *
     * @return employeeName.
     */
    public final String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param name setter.
     */
    public final void setEmployeeName(final String name) {
        this.employeeName = name;
    }

    /**
     * @return salary.
     */
    public final Integer getSalary() {
        return salary;
    }

    /**
     * @param salaryToSet setter.
     */
    public final void setSalary(final Integer salaryToSet) {
        this.salary = salaryToSet;
    }

    /**
     * <p>Getter for the field <code>departmentId</code>.</p>
     *
     * @return departmentId.
     */
    public final Integer getDepartmentId() {
        return departmentId;
    }

    /**
     *
     * @return email
     */
    public final String getEmail() {
        return email;
    }

    /**
     *
     * @param emails for email set
     */
    public final void setEmail(final String emails) {
        this.email = emails;
    }


    /**
     * <p>Setter for the field <code>departmentId</code>.</p>
     *
     * @param id setter.
     */
    public final void setDepartmentId(final Integer id) {
        this.departmentId = id;
    }

    @Override
    public final String toString() {
        return "Employee{"
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName
                + ", salary=" + salary
                + ", departmentId=" + departmentId
                + ", email='" + email
                + '}';
    }
}




