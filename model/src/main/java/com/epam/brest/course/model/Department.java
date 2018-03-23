package com.epam.brest.course.model;

/**
 * department pojo.
 *
 * @author valentine
 * @version $Id: 1
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
     *head of department.
     */
    private String headOfDepartment;

    /**
     * <p>Constructor for Department.</p>
     *
     * @param name for constructor init.
     * @param descriptions for constructor init.
     */
    public Department(final String name, final String descriptions) {
        this.departmentName = name;
        this.description = descriptions;
    }

    /**
     * default constructor.
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
     * @param id setter.
     */
    public final void setDepartmentId(final Integer id) {
        this.departmentId = id;
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
     * @param name names.
     */
    public final void setDepartmentName(final String name) {
        this.departmentName = name;
    }

    /**
     * @return descriptions.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param descriptions setter.
     */
    public final void setDescription(final String descriptions) {
        this.description = descriptions;
    }

    /**
     * @return head of department
     */
    public final String getHeadOfDepartment() {
        return headOfDepartment;
    }

    /**
     *
     * @param headOfDepartments to set head of dept.
     */
    public final void setHeadOfDepartment(final String headOfDepartments) {
        this.headOfDepartment = headOfDepartments;
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
                + headOfDepartment
                + '}';
    }
}
