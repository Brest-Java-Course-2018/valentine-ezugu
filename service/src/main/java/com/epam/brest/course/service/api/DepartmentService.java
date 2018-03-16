package com.epam.brest.course.service.api;

import com.epam.brest.course.model.Department;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * department api service.
 */
public interface DepartmentService {

    /**
     *
     * @param department for adding new Department.
     * @return new Department.
     * @throws DataAccessException if cannot access db.
     */
    Department saveDepartment(Department department)throws DataAccessException;

    /**
     *
     * @return a list of departments.
     * @throws DataAccessException if cannot access db.
     */
    List<Department> getAllDepartments() throws DataAccessException;

    /**
     *@throws DataAccessException if cannot access db.
     * @param id for deleteBy Id.
     */
    void deleteDepartmentById(Integer id) throws DataAccessException;

    /**
     *
     * @param id for get by Id.
     * @throws DataAccessException if cannot access db.
     * @return found department.
     */
    Department getDepartmentById(Integer id) throws DataAccessException;

}
