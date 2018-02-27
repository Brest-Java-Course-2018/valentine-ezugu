package com.epam.brest.course;

/**
 * POJO Department for model.
 */
public class Department {

    /**
     * id attribute
     */
    private Integer departmentId;

    /**
     * name attribute
     */
    private String departmentName;
    /**
     * description attribute
     */
    private String description;

    /**
     *
     * @return department id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     *
     * @param  departmentId setter
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     *
     * @return name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param departmentName setter
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description setter
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return all informations
     */
    @Override
    public String toString() {
        return "Department{"
             + "departmentId=" + departmentId
             + ", departmentName='" + departmentName + '\''
             + ", description='" + description + '\''
             + '}';
    }
}