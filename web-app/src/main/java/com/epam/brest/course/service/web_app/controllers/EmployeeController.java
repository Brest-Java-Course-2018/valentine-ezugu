package com.epam.brest.course.service.web_app.controllers;


import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.api.DepartmentService;
import com.epam.brest.course.service.api.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    /**
     * LOG for debug.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * injected employeeService.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * injected departmentService.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     *
     * @param model for ui.
     * @return list of employees on page employees.
     */
    @GetMapping(value = "/employees")
    public final String getEmployees(final Model model) {
        LOGGER.debug("getEmployees({})", model);
        Collection<Employee> employees =
                employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }


    /**
     *
     * @param id to find one employee.
     * @param model for ui.
     * @return employee page.
     */
    @GetMapping(value = "/employee/{id}")
    public final String editEmployee(@PathVariable final Integer id,
                                        final Model model) {
        LOGGER.debug("editEmployee({},{})", id, model);
        Collection<Department> departments =
                departmentService.getAllDepartments();
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("isNew", false);
        return "employee";
    }

    /**
     * Update employee.
     * @param employee updated employee.
     * @param result binding.
     * @param model for ui.
     * @return employees page.
     */
    @PostMapping(value = "/employee/{id}")
    public final String editEmployee(@Valid final Employee employee,
                                     final BindingResult result,
                                     final Model model) {
        LOGGER.debug("editEmployee({}, {})", employee, result);
        if (result.hasErrors()) {
            Collection<Department> departments =
                    departmentService.getAllDepartments();
            model.addAttribute("departments", departments);
            return "employee";
        } else {
            this.employeeService.update(employee);
            return "redirect:/employees";
        }
    }

    /**
     *
     * @param model for ui.
     * @return employee page for new employee.
     */
    @GetMapping(value = "/employee")
    public final String addEmployee(final Model model) {
        LOGGER.debug("addEmployee({})", model);
        Collection<Department> departments =
               departmentService.getAllDepartments();
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("isNew", true);
        return "employee";
    }

    /**
     * @param employee to be added.
     * @param result binding.
     * @param model for ui.
     * @return list of employees page.
     */
    @PostMapping(value = "/employee")
    public final String addEmployee(@Valid final Employee employee,
                                    final BindingResult result,
                                    final Model model) {
        LOGGER.debug("addEmployee({},{})", employee, result);
        if (result.hasErrors()) {
            Collection<Department> departments =
                    departmentService.getAllDepartments();
            model.addAttribute("departments", departments);
            return "employee";
        } else {
            employeeService.saveEmployee(employee);
            return "redirect:/employees";
        }
    }

    /**
     *
     * @param id to find employee to delete.
     * @param model for ui.
     * @return redirectto employees list.
     */
    @GetMapping(value = "/employee/{id}/delete")
    public final String deleteEmployee(@PathVariable final Integer id,
                                         final Model model) {
        LOGGER.debug("deleteEmployee({},{})", id, model);
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

}
