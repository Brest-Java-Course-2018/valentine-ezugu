package com.epam.brest.course.rest;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Collection;

/**
 * This is the controller for Employee.
 */
@RestController
public class EmployeeRestController {

    /**
     * Log class for debug.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Autowired employee service bean.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * @return json list of employees.
     */
    @GetMapping(value = "/employees")
    public final Collection<Employee> employees() {
        LOGGER.debug("employees()");
        return employeeService.getAllEmployees();
    }

    /**
     * @param id for find.
     * @return found employee object.
     */
    @GetMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Employee employeeId(@PathVariable(value = "id")
                                         final Integer id) {
        LOGGER.debug("employeeId({})", id);
        return employeeService.getEmployeeById(id);
    }

    /**
     * @param employee body for http create.
     * @return persisted object.
     */
    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public final Employee addEmployee(@RequestBody
                                          final Employee employee) {
        LOGGER.debug("addEmployee({})", employee);
        return employeeService.saveEmployee(employee);
    }

    /**
     * @param id for find.
     */
    @DeleteMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deleteEmployee(@PathVariable(value = "id")
                                       final Integer id) {
        LOGGER.debug("deleteEmployee({})", id);
        employeeService.deleteEmployeeById(id);
    }

    /**
     * @param employee .
     */
    @PutMapping(value = "/employees")
    @ResponseStatus(HttpStatus.OK)
    final void updateEmployee(@RequestBody final Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        employeeService.update(employee);
    }
}

