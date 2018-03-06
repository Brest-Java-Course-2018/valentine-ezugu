package com.epam.brest.course.impl;

import com.epam.brest.course.Department;
import com.epam.brest.course.api.DepartmentService;
import com.epam.brest.course.dao.api.DepartmentDao;

import org.springframework.dao.DataAccessException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * service bean for department.
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

    /**
     * dao for db transactions.
     */
    private final DepartmentDao departmentDao;

    /**
     *
     * @param departmentDao constructor based autowiring
     *                      of dependency.
     */
    public DepartmentServiceImpl(DepartmentDao departmentDao){
         this.departmentDao = departmentDao;
      }

    /**
     *
     * @param department for adding new Department.
     * @return new saved department.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public Department saveDepartment(Department department) throws DataAccessException {
        Assert.notNull(department,"department cannot be null");
        return departmentDao.addDepartment(department);
    }

    /**
     *
     * @return list of all departments.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public List<Department> getAllDepartments() throws DataAccessException {
        return departmentDao.getDepartments();
    }

    /**
     *
     * @param id for deleteBy Id.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public void deleteDepartmentById(Integer id) throws DataAccessException {
        Assert.notNull(id,"id cannot be null");
        departmentDao.deleteDepartmentById(id);
    }

    /**
     *
     * @param id for get by Id
     * @return department that matches id.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public Department getDepartmentById(Integer id) throws DataAccessException {
        Assert.notNull(id, "id cannot be null");
        return departmentDao.getDepartmentById(id);
    }


}
