package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.Department;
import com.epam.brest.course.api.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    Department department;

    @Autowired
    DepartmentService departmentService;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/departments")
    public String departments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/department/{id}")
    public String getDepartmentById(@PathVariable Integer id, Model model) {
        department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/updateDepartment/{id}")
    public String updateDepartment(@PathVariable Integer id, Model model) {

        department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     *
     * @param department
     * @return
     */
    @PostMapping(value = "/updateDepartment}")
    public String updateDepartmentPost(@ModelAttribute("department") Department department) {

        departmentService.saveDepartment(department);
        return "redirect:/departments" + department.getDepartmentId();
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping
    public String GetDepartmentList(Model model){
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments";
    }

    /**
     *
     * @param model
     * @return
     */
    @PostMapping
    public String saveDepartment(Model model){

        return "forward:departments";
    }

}
