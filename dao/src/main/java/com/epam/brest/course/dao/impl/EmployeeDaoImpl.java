package com.epam.brest.course.dao.impl;

import com.epam.brest.course.Employee;
import com.epam.brest.course.dao.api.EmployeeDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * impl of dao fro employee.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    /**
     * sql query for get by id.
     */
    @Value("${employee.select}")
    private String employeeSelect;

    /**
     * sql query for get All.
     */
    @Value("${employee.selectAll}")
    private String employeeSelectAll;

    /**
     * sql query for add new employee.
     */
    @Value("${employee.addEmployee}")
    private String addEmployee;

    /**
     * sql query for update employee.
     */
    @Value("${employee.update}")
    private String update;

    /**
     * sql query for delete by id.
     */
    @Value("${employee.delete}")
    private String delete;


    /**
     * variables for resultSet.
     */
    private static final String EMPLOYEE_ID = "employeeId";
    /**
     * row name for row mapper.
     */
    private static final String EMPLOYEE_NAME = "employeeName";
    /**
     * row name for row mapper.
     */
    private static final String SALARY = "salary";
    /**
     * row name for row mapper.
     */
    private static final String DEPARTMENT_ID = "departmentId";


    /**
     * Template class with a basic set of JDBC operations, allowing the use
     * of named parameters rather than traditional '?' placeholders.
     *
     * when we want to give a parameter a specific value
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     *
     * @param namedParameterJdbcTemplate adding namedParameterJdbcTemplate.
     *  which has datasource init so we can use this.
     * to access and manipulate our db.
     *
     */
    public final void setNamedParameterJdbcTemplate(
         final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     *
     * @return a list of employees.
     */
    @Override
    public final List<Employee> getEmployees() {
        List<Employee> employees =
                namedParameterJdbcTemplate.getJdbcOperations()
                        .query(employeeSelectAll, new DepartRowMapper());
         return employees;
    }

    /**
     *
     * @param id to select a particular id.
     * @return an employee.
     */
    @Override
    public final Employee getEmployeeById(final Integer id) {
        Assert.notNull(id, "cannot be null");

    Employee employee;

        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("employeeId", id);

        employee =
                namedParameterJdbcTemplate.queryForObject(
                        employeeSelect, namedParameterSource,
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
        Assert.notNull(employee, "cannot be null");
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("employeeName",
                         employee.getEmployeeName())
                        .addValue("salary", employee.getSalary())
                        .addValue("departmentId", employee.getDepartmentId());
        namedParameterJdbcTemplate.update(
                addEmployee, namedParameterSource);
        return employee;
    }

    /**
     *
     * @param employee object used for update.
     */
    @Override
    public final void updateEmployee(final Employee employee) {

        Assert.notNull(employee, "cannot be null");

        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("employeeId",
                         employee.getEmployeeId())
                        .addValue("employeeName", employee.getEmployeeName())
                        .addValue("salary", employee.getSalary())
                        .addValue("departmentId", employee.getDepartmentId());
        namedParameterJdbcTemplate
                .update(update, namedParameterSource);
    }

    /**
     *
     * @param id for delete.
     */
    @Override
    public final void deleteEmployeeById(final Integer id) {
        Assert.notNull(id, "cannot be null");
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("employeeId", id);
        namedParameterJdbcTemplate
                .update(delete, namedParameterSource);
    }

    /**
     * row mapper.
     */
    private class DepartRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(final ResultSet resultSet, final int i)
                throws SQLException {

            Employee employee = new Employee();
            employee.setDepartmentId(resultSet.getInt(EMPLOYEE_ID));
            employee.setEmployeeName(resultSet.getString(EMPLOYEE_NAME));
            employee.setSalary(resultSet.getInt(SALARY));
            employee.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));

            return employee;
        }
    }

}
