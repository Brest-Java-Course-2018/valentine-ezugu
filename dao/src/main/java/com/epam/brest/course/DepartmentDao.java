package com.epam.brest.course;

import java.util.List;

/**
 * interface for db manipulation
 */
public interface DepartmentDao {

    List<Department> getDepartments();

    Department getDepartmentById(Integer departmentId);

}