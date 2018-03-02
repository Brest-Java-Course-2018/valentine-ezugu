package com.epam.brest.course.dao;

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
     * @param department takes an object as param for adding new.
     * department.
     * @return department.
     */
    Department addDepartment(Department department);

    /**
     *
     * @param department object used for update.
     */
    void updateDepartment(Department department);

    /**
     *
     * @param id for delete.
     */
    void deleteDepartmentById(Integer id);
}
