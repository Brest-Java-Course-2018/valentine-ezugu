package com.epam.brest.course.dao.api;

import com.epam.brest.course.model.Department;
import java.util.List;

/**
 * Data access interface, abstracted methods.
 *
 * @author user
 * @version $Id: $
 */
public interface DepartmentDao {

    /**
     * <p>getDepartments</p>.
     *
     * @return a list of departments.
     */
    List<Department> getDepartments();

    /**
     * <p>getDepartmentById</p>.
     *
     * @param id to select a particular id.
     * @return the department selected.
     */
    Department getDepartmentById(Integer id);

    /**
     * <p>addDepartment</p>.
     *
     * @param dept takes an object as param for adding new.
     * department.
     * @return department.
     */
    Department addDepartment(Department dept);

    /**
     * <p>updateDepartment</p>.
     *
     * @param dept object used for update.
     */
    void updateDepartment(Department dept);

    /**
     * <p>deleteDepartmentById</p>.
     *
     * @param id for delete.
     */
    void deleteDepartmentById(Integer id);
}
