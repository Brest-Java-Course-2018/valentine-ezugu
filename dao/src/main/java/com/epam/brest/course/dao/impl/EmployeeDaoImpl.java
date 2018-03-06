package com.epam.brest.course.dao.impl;

import com.epam.brest.course.Employee;
import com.epam.brest.course.dao.api.EmployeeDao;
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
 * impl of dao fro employee
 */
public class EmployeeDaoImpl implements EmployeeDao {

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
     * final variable for rows affected by execution.
     */
    private static final int ROW4 = 4;


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

    public EmployeeDaoImpl(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     *
     * @return a list of employees.
     */
    @Override
    public final List<Employee> getEmployees() {
        List<Employee> employees =
             jdbcTemplate.query(Query.GET_EMPLOYEE_SQL, new DepartRowMapper());
         return employees;
    }

    /**
     *
     * @param id to select a particular id.
     * @return an employee.
     */
    @Override
    public final Employee getEmployeeById(final Integer id) {
    Employee employee;

        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("employeeId", id);

        employee =
                namedParameterJdbcTemplate.queryForObject(
                        Query.GET_EMPLOYEE_BY_ID, namedParameterSource,
                        new DepartRowMapper());
        return employee;
    }

    /**
     *
     * @param employee takes an object as param for adding new.
     * employee.
     * @return a new employee.
     */
    @Override
    public final Employee addEmployee(final Employee employee) {
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("employeeName",
                         employee.getEmployeeName())
                        .addValue("salary", employee.getSalary())
                        .addValue("departmentId", employee.getDepartmentId());
        namedParameterJdbcTemplate.update(
                Query.ADD_EMPLOYEE_SQL, namedParameterSource);
        return employee;
    }

    /**
     *
     * @param employee object used for update.
     */
    @Override
    public final void updateEmployee(final Employee employee) {
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("employeeId",
                         employee.getEmployeeId())
                        .addValue("employeeName", employee.getEmployeeName())
                        .addValue("salary", employee.getSalary())
                        .addValue("departmentId", employee.getDepartmentId());
        namedParameterJdbcTemplate
                .update(Query.UPDATE_EMPLOYEE_SQL, namedParameterSource);
    }

    /**
     *
     * @param id for delete.
     */
    @Override
    public final void deleteEmployeeById(final Integer id) {
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("employeeId", id);
        namedParameterJdbcTemplate
                .update(Query.DELETE_EMPLOYEE_SQL, namedParameterSource);
    }

    private class DepartRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(final ResultSet resultSet, final int i)
                throws SQLException {

            Employee employee = new Employee();
            employee.setDepartmentId(resultSet.getInt(ROW1));
            employee.setEmployeeName(resultSet.getString(ROW2));
            employee.setSalary(resultSet.getInt(ROW3));
            employee.setDepartmentId(resultSet.getInt(ROW4));

            return employee;
        }
    }

    private class Query {
        private static final String GET_EMPLOYEE_BY_ID =
                "SELECT employeeId, employeeName, salary, departmentId"
                        + " FROM employee WHERE employeeId = :employeeId";

        /**
         * sql query get employee.
         */
        private static final String GET_EMPLOYEE_SQL =
                "SELECT employeeId, employeeName, salary, departmentId "
                       + "FROM employee";

        /**
         * sql add entity.
         */
        private static final String ADD_EMPLOYEE_SQL =
                "INSERT INTO employee (employeeName, salary, departmentId)"
                        + " VALUES (:employeeName, :salary, :departmentId)";

        /**
         * update sql.
         */
        private static final String UPDATE_EMPLOYEE_SQL =
                "UPDATE employee SET employeeName ="
                        + " :employeeName, salary = :salary "
                        + "WHERE employeeId = :employeeId";

        /**
         * delete employee sql query.
         */
        private static final String DELETE_EMPLOYEE_SQL =
                "DELETE FROM employee WHERE employeeId = :employeeId";

    }
}
