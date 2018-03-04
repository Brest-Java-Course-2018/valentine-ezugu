package com.epam.brest.course.dao.api;

import com.epam.brest.course.Department;
import java.util.List;

/**
 * Data access interface, abstracted methods.
 */
public interface DepartmentDao {

    /**
     *
     * @return a list of departments.
     */
    List<Department> getDepartments();

    /**
     *
     * @param id to select a particular id.
     * @return the department selected.
     */
    Department getDepartmentById(Integer id);

    /**
     *
     * @param dept takes an object as param for adding new.
     * department.
     * @return department.
     */
    Department addDepartment(Department dept);

    /**
     *
     * @param dept object used for update.
     */
    void updateDepartment(Department dept);

    /**
     *
     * @param id for delete.
     */
    void deleteDepartmentById(Integer id);
}
