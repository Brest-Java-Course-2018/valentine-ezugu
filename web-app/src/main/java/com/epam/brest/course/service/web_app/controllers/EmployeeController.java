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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/employees")
    public String employees(final Model model) {

        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    /**
     * @param model for adding model to ui.
     * @return
     */
    @GetMapping(value = "/addEmployee")
    public String addEmployee(final Model model) {
        Employee employee = new Employee();
        Collection<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("employee", employee);
        model.addAttribute("isNew", true);
        model.addAttribute("departments", departments);
        return "employee";
    }

    @PostMapping(value = "/employee")
    public String addEmployee(@Valid Employee employee,
                                BindingResult result
    ) {
        LOGGER.debug("addEmployee({}, {})", employee, result);
        if (result.hasErrors()) {
            return "employee";
        } else {
            this.employeeService.saveEmployee(employee);
            return "redirect:/employees";
        }
    }

    /**
     *
     * @param employee employee to be updated.
     * @param result binded result
     * @return
     */
    @PostMapping(value = "/employee/{id}")
    public String updateDepartment(@Valid Employee employee,
                                   BindingResult result
    ) {
        LOGGER.debug("updateDepartment({}, {})", employee, result);
        if (result.hasErrors()) {
            return "employee";
        } else {
            this.employeeService.update(employee);
            return "redirect:/employees";
        }
    }

    /**
     * @param id
     */
    @GetMapping(value = "/removeEmployee/{id}")
    public String removeEmployee(@PathVariable Integer id, final Model model) {
        employeeService.deleteEmployeeById(id);
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

}