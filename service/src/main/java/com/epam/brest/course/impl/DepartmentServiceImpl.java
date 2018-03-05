package com.epam.brest.course.impl;

import com.epam.brest.course.Department;
import com.epam.brest.course.api.DepartmentService;
import com.epam.brest.course.dao.api.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Department addDepartment(Department department) {
        Assert.notNull(department, "department cannot be null");
        Assert.hasText(department.getDepartmentName(),"name cannot be null");
        //description can be null
        return departmentDao.addDepartment(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getDepartments();
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        departmentDao.deleteDepartmentById(id);
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentDao.getDepartmentById(id);
    }
}
