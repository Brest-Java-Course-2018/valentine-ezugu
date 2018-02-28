package com.epam.brest.course.dao;

import com.epam.brest.course.Department;

import java.util.List;

/**
 * interface for db manipulation
 */
public interface DepartmentDao {

    List<Department> getDepartments();

    Department getDepartmentById(Integer departmentId);

    Department addDepartment(Department department);

    void UpdateDepartment(Department department);

    void deleteDepartmentById(Integer id);
}