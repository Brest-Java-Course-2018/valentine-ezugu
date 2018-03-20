package com.epam.brest.course.model;

import java.util.Objects;

/**
 * department pojo.
 *
 * @author user
 * @version $Id: $
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
     */
    private String headOfDepartment;

    /**
     * <p>Constructor for Department.</p>
     *
     * @param departmentName for constructor init.
     * @param description for constructor init.
     */
    public Department(final String departmentName, final String description) {
        this.departmentName = departmentName;
        this.description = description;
    }

    /**
     * empty constructor.
     */
    public Department() {
    }

    /**
     * <p>Getter for the field <code>departmentId</code>.</p>
     *
     * @return id.
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

    /**
     * <p>Getter for the field <code>departmentName</code>.</p>
     *
     * @return deptName.
     */
    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     * <p>Setter for the field <code>departmentName</code>.</p>
     *
     * @param departmentName names.
     */
    public final void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * <p>Getter for the field <code>description</code>.</p>
     *
     * @return descriptions.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * <p>Setter for the field <code>description</code>.</p>
     *
     * @param description setter.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(getDepartmentId(),
                that.getDepartmentId())
                && Objects.equals(getDepartmentName(),
                that.getDepartmentName())
                && Objects.equals(getDescription(),
                that.getDescription())
                && Objects.equals(getHeadOfDepartment(),
                that.getHeadOfDepartment());
    }

    /** {@inheritDoc} */
    @Override
    public final int hashCode() {

        return Objects.hash(getDepartmentId(),
                getDepartmentName(),
                getDescription());
    }

    /** {@inheritDoc} */
    @Override
    public final String toString() {
        return "Department{"
                + "departmentId="
                + departmentId
                + ", departmentName='"
                + departmentName
                + ", description='"
                + description
                + ", headOfDepartment='"
                + headOfDepartment +
                '}';
    }}

