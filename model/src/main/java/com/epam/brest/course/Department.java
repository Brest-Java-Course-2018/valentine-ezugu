package com.epam.brest.course;

import java.util.Objects;

/**
 * department pojo.
 */
public class Department {

    /**
     * id attribute.
     */
    private Integer departmentId;
    /**
     * name attribute.
     */
    private String departmentName;
    /**
     * description attribute.
     */
    private String description;

    /**
     *
     * @return id.
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
     * @return deptName.
     */
    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param departmentName names.
     */
    public final void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     *
     * @return descriptions.
     */
    public final String getDescription() {
        return description;
    }

    /**
     *
     * @param description setter.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     *
     * @param o - object param
     * @return an object that matches  department.
     * main purposr is to check that object created is
     * a match to department object.
     */
    @Override
    public final boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        //if object not an instance of dept
        if (!(o instanceof Department)) {
            return false;
        }
        Department that = (Department) o;

        return Objects.equals(getDepartmentId(), that.getDepartmentId())
             && Objects.equals(getDepartmentName(), that.getDepartmentName())
             && Objects.equals(getDescription(), that.getDescription());
    }

    /**
     *
     * @return and store hash value of obj
     */
    @Override
    public final int hashCode() {

        return Objects.hash(getDepartmentId(),
                getDepartmentName(),
                getDescription());
    }

    @Override
    public final String toString() {
        return "Department{"
                + "departmentId="
                + departmentId
                + ", departmentName='"
                + departmentName
                + ", description='"
                + description
                + '}';
    }

}
