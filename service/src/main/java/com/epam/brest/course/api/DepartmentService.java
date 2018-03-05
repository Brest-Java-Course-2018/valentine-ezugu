package com.epam.brest.course.api;

import com.epam.brest.course.Department;

import java.util.List;

public interface DepartmentService {

    /**
     *
     * @param department for adding new Department
     * @return new Department
     */
    Department addDepartment(Department department);

    /**
     *
     * @return a list of departments
     */
    List<Department> getAllDepartments();

    /**
     *
     * @param id for deleteBy Id
     */
    void deleteDepartmentById(Integer id);

    /**
     *
     * @param id for get by Id
     * @return found department
     */
    Department getDepartmentById(Integer id);

}
