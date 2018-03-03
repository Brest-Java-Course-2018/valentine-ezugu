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
    public   Integer getDepartmentId() {
        return departmentId;
    }

    /**
     *
     * @param departmentId setter.
     */
    public   void setDepartmentId(  Integer departmentId) {
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

    @Override
    public final boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        Department that = (Department) o;

        return Objects.equals(getDepartmentId(), that.getDepartmentId())
             && Objects.equals(getDepartmentName(), that.getDepartmentName())
             && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public final int hashCode() {

        return Objects.hash(getDepartmentId(),
                getDepartmentName(),
                getDescription());
    }
}