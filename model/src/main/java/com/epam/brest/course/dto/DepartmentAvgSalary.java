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
     * default constructor.
     */
    public DepartmentAvgSalary() {
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
    public String toString() {
        return "DepartmentAvgSalary{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }
}
