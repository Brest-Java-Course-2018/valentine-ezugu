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

    public final Integer getDepartmentId() {
        return departmentId;
    }

    public final void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param departmentName names
     */
    public final void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     *
     * @return descriptions
     */
    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o){
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