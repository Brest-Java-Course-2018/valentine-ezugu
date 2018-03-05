package com.epam.brest.course;

import java.util.Objects;

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
    private Integer departmentId;

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
        return departmentId;
    }

    /**
     *
     * @param departmentId setter.
     */
    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     *
     * @return String.
     */
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

    @Override
    public final int hashCode() {

        return Objects.hash(getEmployeeId(),
                getEmployeeName(),
                getSalary(),
                departmentId);
    }
}




