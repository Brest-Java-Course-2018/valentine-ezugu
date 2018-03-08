package com.epam.brest.course.impl;

import com.epam.brest.course.Department;
import com.epam.brest.course.api.DepartmentService;
import com.epam.brest.course.dao.api.DepartmentDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * service bean for department.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * dao for db transactions.
     */
    @Autowired
    private DepartmentDao departmentDao;

    /**
     *
     * @param department for adding new Department.
     * @return new saved department.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public Department saveDepartment(Department department) throws DataAccessException {
        LOGGER.debug("saveDepartment({})", department);
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
        LOGGER.debug("getAllDepartments()");
        return departmentDao.getDepartments();
    }

    /**
     *
     * @param id for deleteBy Id.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public void deleteDepartmentById(Integer id) throws DataAccessException {
        LOGGER.debug("deleteDepartmentById({})" ,id);
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
        LOGGER.debug("getDepartmentById({})" ,id);
        Assert.notNull(id, "id cannot be null");
        return departmentDao.getDepartmentById(id);
    }

}
