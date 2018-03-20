package com.epam.brest.course.service.web_app.controllers;

import com.epam.brest.course.dto.DepartmentAvgSalary;
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

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    Department department;
    /**
     * Goto departments list page.
     *
     * @return view name
     */
    @GetMapping(value = "/departments")
    public final String departments(Model model) {
        LOGGER.debug("getDepartments({})", model);
        Collection<DepartmentAvgSalary> departments = departmentService.getDepartments_avgSalary();
        model.addAttribute("departments", departments);
        return "departments";
    }

    /**
     * Goto new department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department")
    public final String gotoAddDepartmentPage(Model model) {
        LOGGER.debug("addDepartment({})", model);
        Department department = new Department();
        model.addAttribute("isNew", true);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     * @param department new department.
     * @param result data binding result.
     * @return view name.
     */
    @PostMapping(value = "/department")
    public String addDepartment(@Valid Department department,
                                BindingResult result
    ) {
        LOGGER.debug("addDepartment({}, {})", department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
            this.departmentService.saveDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Goto edit department page.
     * @return view name.
     */
    @GetMapping(value = "/department/{id}")
    public final String gotoEditDepartmentPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("gotoEditDepartmentPage({},{})", id, model);
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     * Update department into persistence storage.
     * @return view name.
     */
    @PostMapping(value = "/department/{id}")
    public String updateDepartment(@Valid Department department,
                                   BindingResult result
    ) {
        LOGGER.debug("updateDepartment({}, {})", department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
            this.departmentService.updateDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Delete department.
     * @return view name
     */
    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartmentById(@PathVariable Integer id, Model model) {
        LOGGER.debug("deleteDepartmentById({},{})", id, model);

        for (Employee employee : employeeService.getAllEmployees()) {

            Department department = departmentService.getDepartmentById(id);
            if (department.getDepartmentId().equals(employee.getDepartmentId())){
                model.addAttribute("cantdelete", true);
                return "departments";
            }
            model.addAttribute("cantdelete", false);
        }
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }

}