package com.epam.brest.course.model;

import java.util.Objects;

/**
 * POJO Employee for model.
 *
 * @author valentine
 * @version $Id: $
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
     * Get Employee Id.
     *
     * @return employeeId.
     */
    public final Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * <p>Setter for the field <code>employeeId</code>.</p>
     *
     * @param employeeId setter.
     */
    public final void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
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
     * <p>Setter for the field <code>employeeName</code>.</p>
     *
     * @param employeeName setter.
     */
    public final void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * <p>Getter for the field <code>salary</code>.</p>
     *
     * @return salary.
     */
    public final Integer getSalary() {
        return salary;
    }

    /**
     * <p>Setter for the field <code>salary</code>.</p>
     *
     * @param salary setter.
     */
    public final void setSalary(final Integer salary) {
        this.salary = salary;
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
     * <p>Setter for the field <code>departmentId</code>.</p>
     *
     * @param departmentId setter.
     */
    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    /** {@inheritDoc} */
    @Override
    public final java.lang.String toString() {
        return "Employee{"
              +  "employeeId="
              + employeeId
              + ",   employeeName="
              + employeeName
              + ", salary="
              + salary
              + ", departmentId="
              + departmentId
              +  '}';
    }

    /** {@inheritDoc} */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(getEmployeeId(),
                employee.getEmployeeId())
                && Objects.equals(getEmployeeName(),
                        employee.getEmployeeName())
                && Objects.equals(getSalary(),
                        employee.getSalary())
                && Objects.equals(departmentId, employee.departmentId);
    }

    /** {@inheritDoc} */
    @Override
    public final int hashCode() {

        return Objects.hash(getEmployeeId(),
                getEmployeeName(),
                getSalary(),
                departmentId);
    }
}




