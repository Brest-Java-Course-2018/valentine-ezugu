package com.epam.brest.course.impl;

import com.epam.brest.course.Department;
import com.epam.brest.course.api.DepartmentService;
import com.epam.brest.course.dao.api.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Department addDepartment(Department department) throws DataAccessException {
        Assert.notNull(department, "department cannot be null");
        Assert.hasText(department.getDepartmentName(),"name cannot be null");
        //description can be null
        return departmentDao.addDepartment(department);
    }

    @Override
    public List<Department> getAllDepartments() throws DataAccessException {
        return departmentDao.getDepartments();
    }

    @Override
    public void deleteDepartmentById(Integer id) throws DataAccessException {
        departmentDao.deleteDepartmentById(id);
    }

    @Override
    public Department getDepartmentById(Integer id) throws DataAccessException {
        return departmentDao.getDepartmentById(id);
    }
}
