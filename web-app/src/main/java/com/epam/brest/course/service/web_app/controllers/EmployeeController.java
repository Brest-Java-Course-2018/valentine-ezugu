package com.epam.brest.course.service.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.api.DepartmentService;
import com.epam.brest.course.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Employee controller.
 */
@Controller
@SessionAttributes(types = Employee.class)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * method employees mapping /employees reqest and get list  employees.
     *
     * @param model attributes map
     * @return template name
     */
    @GetMapping(value = "/employees")
    public String employees(final Model model) {

        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    /**
     * method editEmployee mapping /editEmployee reqest make setups to edit Employee record.
     *
     * @param model attributes map.
     * @return template name.
     */
    @GetMapping(value = "/addEmployee")
    public String addEmployee(final Model model) {
        Employee new_employee = new Employee();
        Collection<Department> departments = departmentService.getAllDepartments();

        model.addAttribute("employee", new_employee);
        model.addAttribute("Title", "Add Employee");
        model.addAttribute("isNew", true);
        model.addAttribute("departments", departments);
        return "editEmployee";
    }

    /**
     * method editEmployee mapping /editEmployee reqest make setups to edit Employee record.
     *
     * @param model attributes map
     * @param id id of Employee record
     * @return template name
     */

    /**
     * method removeEmployee mapping /removeEmployee reqest and remoce Employee record from db
     *
     * @param model attributes map
     * @param id id of Employee record
     * @return template name
     */
    @GetMapping(value = "/removeEmployee/{id}")
    public String removeEmployee(@PathVariable Integer id, final Model model) {
        employeeService.deleteEmployeeById(id);
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

}