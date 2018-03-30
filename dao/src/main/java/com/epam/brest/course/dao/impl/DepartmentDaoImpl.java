package com.epam.brest.course.dao.impl;

import com.epam.brest.course.dao.api.DepartmentDao;
import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
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
 * dao class for db manipulation.
 *
 * @author user.
 * @version $Id: $
 */
public class DepartmentDaoImpl implements DepartmentDao {

    /**
     * for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * query for getting avg salary of employee
     * grouped by department id.
     */
    @Value("${department.selectAvgSalary}")
    private String selectAvgSalarySql;
    /**
     * sql query for select by id.
     */
    @Value("${department.select}")
    private String departmentSelect;
    /**
     * sql query for select all.
     */
    @Value("${department.selectAll}")
    private String departmentSelectAll;
    /**
     * sql query for add new.
     */
    @Value("${department.addDepartment}")
    private String addDepartment;
    /**
     * sql query for update.
     */
    @Value("${department.update}")
    private String update;
    /**
     * sql query for delete by id.
     */
    @Value("${department.delete}")
    private String delete;
    /**
     *
     */
    @Value("${department.checkDepartment}")
    private String checkDepartment;

    /**
     * row description for row mapper.
     */
    private static final String DEPARTMENT_ID = "departmentId";
    /**
     * row description for row mapper.
     */
    private static final String DEPARTMENT_NAME = "departmentName";
    /**
     * row description for row mapper.
     */
    private static final String DESCRIPTION = "description";
    /**
     * head of the department attribute.
     */
    private static final String HEAD_OF_DEPARTMENT = "headOfDepartment";
    /**
     * average salary will increase or reduce when we add new salary.
     */
    private static final String AVG_SALARY = "avgSalary";

    /**
     * Template class with a basic set of JDBC operations, allowing the use.
     * of named parameters rather than traditional '?' placeholders.
     * <p>
     * when we want to give a parameter a specific value.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * <p>Setter for the field <code>namedParameterJdbcTemplate</code>.</p>
     *
     * @param namedParameterJdbcTemplate1 jdbc basic ops.
     */
    public final void setNamedParameterJdbcTemplate(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate1) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Department> getDepartments() {
        LOGGER.debug("getDepartment()");

        List<Department> departments =
                namedParameterJdbcTemplate.getJdbcOperations()
                        .query(departmentSelectAll, new DepartRowMapper());
        return departments;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public final Department getDepartmentById(final Integer departmentId) {
        Assert.notNull(departmentId, "departmentId cannot be null");

        LOGGER.debug("getDepartmentById({})", departmentId);

        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(departmentSelect,
                        namedParameters,

                        BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Department addDepartment(final Department department) {

        Assert.notNull(department, "department cannot be null");
        LOGGER.debug("addDepartment({})", department);

        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentName",
                        department.getDepartmentName());
        Integer result =
                namedParameterJdbcTemplate.queryForObject(
                        checkDepartment, namedParameters, Integer.class);

        LOGGER.debug("result({})", result);

        if (result == 0) {
            namedParameters = new MapSqlParameterSource();

            namedParameters.addValue("departmentName",
                    department.getDepartmentName());
            namedParameters.addValue("description",
                    department.getDescription());

            namedParameters.addValue("headOfDepartment",
                    department.getHeadOfDepartment());

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
     * {@inheritDoc}
     */
    @Override
    public final void updateDepartment(final Department department) {
        LOGGER.debug("updateDepartment({})", department);
        Assert.notNull(department, "department cannot be null");

        SqlParameterSource namedParameter =
                new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    /**
     *
     * @param id for finding department to delete.
     */
    @Override
    public final void deleteDepartmentById(final Integer id) {
        LOGGER.debug("deleteDepartmentById({})", id);
        Assert.notNull(id, "departmentId cannot be null");

        namedParameterJdbcTemplate.getJdbcOperations()
                .update(delete, id);
    }

    /**
     *
     * @return departments with avg salary.
     */
    @Override
    public final List<DepartmentAvgSalary> getDepartmentAvgSalary() {
        LOGGER.debug("getDepartmentDTOs()");
        List<DepartmentAvgSalary> departmentAvgSalaries =
                namedParameterJdbcTemplate
                        .getJdbcOperations()
                        .query(selectAvgSalarySql,
                                new DepartmentDTORowMapper());
        return departmentAvgSalaries;
    }

    /**
     * interface perform the actual work of mapping each row to a result object,
     * but don't need to worry about exception handling.
     * java.sql.SQLException SQLExceptions} will be caught and handled
     * by the calling JdbcTemplate.
     */
    private class DepartRowMapper implements RowMapper<Department> {

        @Override
        public final Department mapRow(final ResultSet resultSet, final int i)
                throws SQLException {

            Department department = new Department();
            department.setDepartmentId(
                    resultSet.getInt(DEPARTMENT_ID));

            department.setDepartmentName(
                    resultSet.getString(DEPARTMENT_NAME));

            department.setDescription(
                    resultSet.getString(DESCRIPTION));

            department.setHeadOfDepartment(
                    resultSet.getString(HEAD_OF_DEPARTMENT));

            return department;
        }
    }

    /**
     * This inner class is for mapping the rows of our table.
     */
    private class DepartmentDTORowMapper
            implements RowMapper<DepartmentAvgSalary> {

        @Override
        public final DepartmentAvgSalary mapRow(final ResultSet
                                                        resultSet, final int i)
                throws SQLException {

            DepartmentAvgSalary departmentAvgSalary = new DepartmentAvgSalary();

            departmentAvgSalary.setDepartmentId(
                    resultSet.getInt(DEPARTMENT_ID));

            departmentAvgSalary.setDepartmentName(
                    resultSet.getString(DEPARTMENT_NAME));

            departmentAvgSalary.setAvgSalary(
                    resultSet.getInt(AVG_SALARY));

            return departmentAvgSalary;
        }
    }


}

