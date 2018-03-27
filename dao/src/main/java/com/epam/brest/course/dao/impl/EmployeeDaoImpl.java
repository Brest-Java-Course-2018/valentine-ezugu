package com.epam.brest.course.dao.impl;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.dao.api.EmployeeDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import java.util.Collection;


/**
 * impl of dao fro employee.
 *
 * @author user
 * @version $Id: $
 */
public class EmployeeDaoImpl implements EmployeeDao {


    /**
     * for static logger use logManager.
     */
    private static final Logger LOGGER = LogManager.getLogger();

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

    @Value("${employee.check}")
    private String employeeCheck;

    /**
     * variables for resultSet.
     */
    private static final String EMPLOYEE_ID = "employeeId";

    /**
     * Template class with a basic set of JDBC operations, allowing the use
     * of named parameters rather than traditional '?' placeholders.
     *
     * when we want to give a parameter a specific value
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * @param namedParameterJdbcTemplate1 adding namedParameterJdbcTemplate.
     *  which has datasource init so we can use this.
     * to access and manipulate our db.
     */
    public final void setNamedParameterJdbcTemplate(
         final NamedParameterJdbcTemplate namedParameterJdbcTemplate1) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate1;
    }

    @Override
    public  final Collection<Employee> getEmployees() {
        LOGGER.debug("getEmployees()");
        Collection<Employee> employees =
                namedParameterJdbcTemplate.getJdbcOperations().
                        query(employeeSelectAll, BeanPropertyRowMapper.
                                newInstance(Employee.class));
        return employees;
    }

    /**
     *
     * @param id to select a particular id.
     * @return employee.
     */
    @Override
    public final Employee getEmployeeById(final Integer id) {
        LOGGER.debug("getEmployeeById {}", id);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(EMPLOYEE_ID, id);
        Employee employee =
                namedParameterJdbcTemplate.queryForObject(employeeSelect,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    /**
     *
     * @param employee takes an object as param for adding new.
     * employee.
     * @return new employee.
     */
//    @Override
//    public final Employee addEmployee(final Employee employee) {
//        Assert.notNull(employee, "cannot be null");
//        SqlParameterSource namedParameterSource =
//                new MapSqlParameterSource("employeeName",
//                         employee.getEmployeeName())
//                        .addValue("salary", employee.getSalary())
//                        .addValue("departmentId", employee.getDepartmentId());
//        namedParameterJdbcTemplate.update(
//                addEmployee, namedParameterSource);
//        return employee;
//    }
    @Override
    public final Employee addEmployee(final Employee employee) {
        Assert.notNull(employee, "cannot be null");
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(employee);

        //this is used for auto generated key
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        Integer result = namedParameterJdbcTemplate.queryForObject(
                employeeCheck, namedParameters, Integer.class);
        LOGGER.debug("result({})", result);
        if (result == 0) {

            namedParameterJdbcTemplate.
                    update(addEmployee, namedParameters, generatedKeyHolder);
            employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException(
                    "such Employee is already belong "
                            + "to this department.");
        }
        LOGGER.debug("addEmployee({})", employee);
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
                        .addValue("email", employee.getEmail())
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

}
