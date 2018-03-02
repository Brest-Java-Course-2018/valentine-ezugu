package com.epam.brest.course.dao;

import com.epam.brest.course.Department;
import java.util.List;

public interface DepartmentDao {

    List<Department> getDepartments();

    Department getDepartmentById(Integer id);

    Department addDepartment(Department department);

    void UpdateDepartment(Department department);

    void deleteDepartmentById(Integer id);
}