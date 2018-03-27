package com.epam.brest.course.dto;

/**
 * This class is mainly for finding
 * average salary in list of department.
 */
public class DepartmentAvgSalary {
    /**
     * id.
     */
    private Integer departmentId;
    /**
     * department name.
     */
    private String departmentName;
    /**
     * avg salary.
     */
    private Integer avgSalary;

    /**
     * default.
     */
    public DepartmentAvgSalary() {
    }

    /**
     *
     * @param departmentId1 .
     * @param departmentName1 .
     * @param avgSalary1 .
     */
    public DepartmentAvgSalary(final Integer departmentId1,
                               final String departmentName1,
                               final Integer avgSalary1) {
        this.departmentId = departmentId1;
        this.departmentName = departmentName1;
        this.avgSalary = avgSalary1;
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
     * @param id setter method for id.
     */
    public final void setDepartmentId(final Integer id) {
        this.departmentId = id;
    }

    /**
     *
     * @return department name.
     */
    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param name name.
     */
    public final void setDepartmentName(final String name) {
        this.departmentName = name;
    }

    /**
     *
     * @return avgSalary.
     */
    public final Integer getAvgSalary() {
        return avgSalary;
    }

    /**
     *
     * @param salary setter for avg salary.
     */
    public final void setAvgSalary(final Integer salary) {
        this.avgSalary = salary;
    }

    @Override
    public final String toString() {
        return "DepartmentAvgSalary{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", avgSalary=" + avgSalary
                + '}';
    }
}
