package com.epam.brest.course.dao;

import com.epam.brest.course.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *dao class for db manipulation
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private static final int ROW1 = 1;

    private static final int ROW2 = 2;

    private static final int ROW3 = 3;

    /**
     * It simplifies the use of
     * JDBC and helps to avoid common errors.
     * It executes core JDBC workflow, leaving
     * application code to provide SQL and extract
     * results. This class executes SQL queries or
     * updates, initiating iteration over ResultSets
     * and catching JDBC exceptions and translating
     * them to the generic.
     *
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * Template class with a basic set of JDBC operations, allowing the use
     * of named parameters rather than traditional '?' placeholders.
     *
     * when we want to give a parameter a specific value
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     *
     * @param dataSource creates a db connection
     */
    public DepartmentDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Department> getDepartments() {
        List<Department> departments = jdbcTemplate.query(JdbcQuery.GET_DEPARTMENT_SQL,new DepartRowMapper());
        return departments;
    }

    /**
     *
     * @param departmentId
     * @return
     * during execution time namedParameter will
     * be converted to JDBC style '?' placeholders
     */
    @Override
    public Department getDepartmentById(Integer departmentId) {
        Department department;

        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("departmentId", departmentId);

          department =
                namedParameterJdbcTemplate.queryForObject(
                        JdbcQuery.GET_DEPARTMENT_BY_ID,namedParameterSource,
                        new DepartRowMapper());
        return department;
    }


    /**
     *
     * @param department
     * @return
     */
    @Override
    public Department addDepartment(Department department) {
        Assert.notNull(department,"department cannot be null");
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("departmentName", department.getDepartmentName())
                        .addValue("description",department.getDescription());
        namedParameterJdbcTemplate.update(
                        JdbcQuery.ADD_DEPARTMENT,namedParameterSource);
        return department;
    }

    /**
     *
     * @param department
     */
    @Override
    public void UpdateDepartment(Department department) {
        Assert.notNull(department,"department cannot be null");
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("departmentId", department.getDepartmentId())
                        .addValue("departmentName",department.getDepartmentName())
                        .addValue("description",department.getDescription());
        namedParameterJdbcTemplate.update(JdbcQuery.UPDATE_DEPARTMENT,namedParameterSource);
    }

    /**
     *
     * @param departmentId of
     * the column to be deleted
     */
    @Override
    public void deleteDepartmentById(Integer departmentId) {
         Assert.notNull(departmentId, "department id cannot be null ");
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("departmentId", departmentId);
        namedParameterJdbcTemplate.update(JdbcQuery.DELETE_DEPARTMENT,namedParameterSource );
    }

    /**
     * interface perform the actual work of mapping each row to a result object,
     * but don't need to worry about exception handling.
     *  java.sql.SQLException SQLExceptions} will be caught and handled
     * by the calling JdbcTemplate.
     */
    private class DepartRowMapper implements RowMapper<Department>{

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(ROW1));
            department.setDepartmentName(resultSet.getString(ROW2));
            department.setDescription(resultSet.getString(ROW3));
            return department;
        }
    }

    /**
     * class that stores all query to be used in dao
     *
     */
    private class JdbcQuery{

        private static final String GET_DEPARTMENT_BY_ID =
                "SELECT departmentId, departmentName, description "
                        + "FROM department WHERE departmentId = departmentId";


        private static final String GET_DEPARTMENT_SQL =
                "SELECT departmentId, departmentName, description FROM department";

        private static final String ADD_DEPARTMENT =
                "INSERT INTO department (departmentName, description) VALUES (:departmentName, :description)";

        private static final String UPDATE_DEPARTMENT =
                "UPDATE department SET departmentName = departmentName, description = description "
                        + "WHERE departmentId = departmentId";

        private static final String DELETE_DEPARTMENT =
                "DELETE FROM department WHERE departmentId = departmentId";


    }
}

