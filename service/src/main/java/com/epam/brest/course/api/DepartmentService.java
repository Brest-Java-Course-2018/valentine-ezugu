package com.epam.brest.course.api;

import com.epam.brest.course.Department;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentService {

    /**
     *
     * @param department for adding new Department
     * @return new Department
     */
    Department addDepartment(Department department)throws DataAccessException;

    /**
     *
     * @return a list of departments
     */
    List<Department> getAllDepartments() throws DataAccessException;

    /**
     *
     * @param id for deleteBy Id
     */
    void deleteDepartmentById(Integer id) throws DataAccessException;

    /**
     *
     * @param id for get by Id
     * @return found department
     */
    Department getDepartmentById(Integer id) throws DataAccessException;

}
