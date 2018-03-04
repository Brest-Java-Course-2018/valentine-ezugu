package com.epam.brest.course.dao.impl;

import com.epam.brest.course.Department;
import com.epam.brest.course.dao.api.DepartmentDao;
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
 *dao class for db manipulation.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    /**
     * final variable for rows affected by execution.
     */
    private static final int ROW1 = 1;
    /**
     * final variable for rows affected by execution.
     */
    private static final int ROW2 = 2;

    /**
     * final variable for rows affected by execution.
     */
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
     * @param dataSource creates a db connection.
     */

    public DepartmentDaoImpl(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     *
     * @return a list of departments
     */
    @Override
    public final List<Department> getDepartments() {
        List<Department> departments =
        jdbcTemplate.query(JdbcQuery.GET_DEPARTMENT_SQL, new DepartRowMapper());
        return departments;
    }

    /**
     *
     * @param deptId used to get one department by id
     * @return
     * during execution time namedParameter will
     * be converted to JDBC style '?' placeholders.
     */
    @Override
    public final Department getDepartmentById(final Integer deptId) {
        Department department;

        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("departmentId", deptId);

          department =
                namedParameterJdbcTemplate.queryForObject(
                         JdbcQuery.GET_DEPARTMENT_BY_ID, namedParameterSource,
                        new DepartRowMapper());
        return department;
    }


    /**
     *
     * @param department to get and set info about department to add.
     * @return department.
     */
    @Override
    public final Department addDepartment(final Department department) {
        Assert.notNull(department, "department cannot be null");
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("departmentName",
                        department.getDepartmentName())
                        .addValue("description", department.getDescription());
        namedParameterJdbcTemplate.update(
                        JdbcQuery.ADD_DEPARTMENT, namedParameterSource);
        return department;
    }

    /**
     *
     * @param department used to accept info about department to update.
     */
    @Override
    public final void updateDepartment(final Department department) {
        Assert.notNull(department, "department cannot be null");
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("departmentId",
                        department.getDepartmentId())
                    .addValue("departmentName", department.getDepartmentName())
                    .addValue("description", department.getDescription());
        namedParameterJdbcTemplate
                .update(JdbcQuery.UPDATE_DEPARTMENT, namedParameterSource);
    }

    /**
     *
     * @param deptId of
     * the column to be deleted.
     */
    @Override
    public final void deleteDepartmentById(final Integer deptId) {
         Assert.notNull(deptId, "department id cannot be null ");
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("departmentId", deptId);
        namedParameterJdbcTemplate
                .update(JdbcQuery.DELETE_DEPARTMENT, namedParameterSource);
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
            department.setDepartmentId(resultSet.getInt(ROW1));
            department.setDepartmentName(resultSet.getString(ROW2));
            department.setDescription(resultSet.getString(ROW3));
            return department;
        }
    }

    /**
     * class that stores all query to be used in dao.
     *
     */
    private class JdbcQuery {
        /**
         * sql query.
         */
        private static final String GET_DEPARTMENT_BY_ID =
                "SELECT departmentId, departmentName, description"
                        + " FROM department WHERE departmentId = :departmentId";

        /**
         * sql query get department.
         */
        private static final String GET_DEPARTMENT_SQL =
           "SELECT departmentId, departmentName, description FROM department";

        /**
         * sql add entity.
         */
        private static final String ADD_DEPARTMENT =
          "INSERT INTO department (departmentName, description)"
                  + " VALUES (:departmentName, :description)";

        /**
         * update sql.
         */
        private static final String UPDATE_DEPARTMENT =
                "UPDATE department SET departmentName ="
                        + " :departmentName, description = :description "
                        + "WHERE departmentId = :departmentId";

        /**
         * delete department sql query.
         */
        private static final String DELETE_DEPARTMENT =
                "DELETE FROM department WHERE departmentId = :departmentId";

    }
}

