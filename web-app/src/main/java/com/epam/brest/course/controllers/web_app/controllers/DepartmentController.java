package com.epam.brest.course.controllers.web_app.controllers;

import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.api.DepartmentService;
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
 * Department controller.
 */
@Controller
public class DepartmentController {
    /**
     * log for debug controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * department service injected.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * @param model for ui.
     * @return list of all departments.
     */
    @GetMapping(value = "/departments")
    public final String departments(final Model model) {
        LOGGER.debug("getDepartments({})", model);
        Collection<DepartmentAvgSalary> departments
                = departmentService.getDepartmentsAvgSalary();

        model.addAttribute("departments", departments);
        return "departments";
    }

    /**
     * add department page.
     * @param model ui model .
     * @return new page for filling form department.
     */
    @GetMapping(value = "/department")
    public final String addDepartment(final Model model) {
        LOGGER.debug("addDepartment({})", model);
        Department department = new Department();
        model.addAttribute("isNew", true);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     *
     * @param department to be add new dept.
     * @param result binding.
     * @return list with new department.
     */
    @PostMapping(value = "/department")
    public final String addDepartment(@Valid final Department department,
                              final BindingResult result
    ) {

        LOGGER.debug("addDepartment({}, {})", department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
             departmentService.saveDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * edit department get page.
     * @param id to find department to edit.
     * @param model for ui model transfer.
     * @return department page.
     */
    @GetMapping(value = "/department/{id}")
    public final String editDepartment(@PathVariable final Integer id,
                                       final Model model) {
        LOGGER.debug("editDepartment({},{})", id, model);
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     * @param department to be updated.
     * @param result for binding.
     * @return updated list.
     */
    @PostMapping(value = "/department/{id}")
    public final String updateDepartment(@Valid final Department department,
                                 final BindingResult result
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
     *
     * @param id for finding dept.
     * @param model for ui.
     * @return department page.
     */
    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartmentById(@PathVariable final Integer id,
                                             final Model model) {
        LOGGER.debug("deleteDepartmentById({},{})", id, model);
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }

}
