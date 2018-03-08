package com.epam.brest.course.dao.impl;

import com.epam.brest.course.Department;
import com.epam.brest.course.dao.api.DepartmentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *dao class for db manipulation.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    /**
     * for static logger use logManager
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${department.select}")
    private String departmentSelect;

    @Value("${department.selectAll}")
    private String departmentSelectAll;

    @Value("${department.addDepartment}")
    private String addDepartment;

    @Value("${department.update}")
    private String update;

    @Value("${department.delete}")
    private String delete;

    @Value("${department.checkDepartment}")
    private String checkDepartment;


    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DESCRIPTION = "description";


    /**
     * Template class with a basic set of JDBC operations, allowing the use
     * of named parameters rather than traditional '?' placeholders.
     *
     * when we want to give a parameter a specific value
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     *
     * @param namedParameterJdbcTemplate jdbc basic ops.
     */
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     *
     * @return a list of departments
     */
    @Override
    public final List<Department> getDepartments() {
        LOGGER.debug("getDepartment()");

        List<Department> departments =
        namedParameterJdbcTemplate.getJdbcOperations().query(departmentSelectAll, new DepartRowMapper());
        return departments;
    }

    /**
     *
     * @param departmentId used to get one department by id.
     * @return
     * during execution time namedParameter will
     * be converted to JDBC style '?' placeholders.
     *
     * BeanPropertyRowMapper can be used instead of RowMapper by calling the
     * model class to check all attributes and map them to result.
     */
    @Override
    public Department getDepartmentById(Integer departmentId) {
        Assert.notNull(departmentId,"departmentId cannot be null");

        LOGGER.debug("getDepartmentById({})" , departmentId);

        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department =
            namedParameterJdbcTemplate.queryForObject(departmentSelect, namedParameters,

            BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    /**
     *
     * @param department to get and set info about department to add.
     * @return department.
     * the idea is to check that no repetition of already
     * existing department when adding new department
     */
    @Override
    public Department addDepartment(Department department) {

        Assert.notNull(department,"department cannot be null");
        LOGGER.debug("addDepartment({})" , department);

        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentName", department.getDepartmentName());
        Integer result =
                namedParameterJdbcTemplate.queryForObject(
                        checkDepartment, namedParameters, Integer.class);

        LOGGER.debug("result({})" , result);
        if (result == 0) {
            namedParameters = new MapSqlParameterSource();

            namedParameters.addValue("departmentName", department.getDepartmentName());
            namedParameters.addValue("description", department.getDescription());

            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(
                    addDepartment, namedParameters, generatedKeyHolder);

            department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException(
                    "Department with the same name already exists in DB.");
        }

        return department;
    }



    /**
     *
     * @param department used to accept info about department to update.
     */
    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("updateDepartment({})" , department);
        Assert.notNull(department,"department cannot be null");

        SqlParameterSource namedParameter =
                new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }


    /**
     *
     * @param departmentId of
     * the column to be deleted.
     */
    @Override
    public void deleteDepartmentById(Integer departmentId) {
        LOGGER.debug("deleteDepartmentById({})" , departmentId);
        Assert.notNull(departmentId,"departmentId cannot be null");

        namedParameterJdbcTemplate.getJdbcOperations().update(delete, departmentId);
    }

    /**
     * interface perform the actual work of mapping each row to a result object,
     * but don't need to worry about exception handling.
     *  java.sql.SQLException SQLExceptions} will be caught and handled
     * by the calling JdbcTemplate.
     */
    private class DepartRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(final ResultSet resultSet, final int i)
                throws SQLException {

            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDescription(resultSet.getString(DESCRIPTION));
            return department;
        }
    }


}

