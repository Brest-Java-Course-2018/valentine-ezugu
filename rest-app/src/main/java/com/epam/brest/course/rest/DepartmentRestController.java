package com.epam.brest.course.rest;

import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.api.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * This is the rest controller.
 * for Rest services.
 */
@RestController
public class DepartmentRestController {
    /**
     * Log class for debug.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Autowired department service bean.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * @return json list of departments.
     */
    @GetMapping(value = "/departments")
    public final Collection<DepartmentAvgSalary> departments() {
        LOGGER.debug("departments()");
        return departmentService.getDepartmentsAvgSalary();
    }

    /**
     *
     * @param id for find.
     * @return found department object.
     */
    @GetMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Department departmentId(@PathVariable(value = "id")
                                             final Integer id) {
        LOGGER.debug("departmentId({})", id);
        return departmentService.getDepartmentById(id);
    }

    /**
     *
     * @param department body for http create.
     * @return persisted object.
     */
    @PostMapping(value = "/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public final Department addDepartment(@RequestBody
                                              final Department department) {
        LOGGER.debug("addDepartment({})", department);
         return departmentService.saveDepartment(department);
    }

    /**
     *
     * @param id for find.
     */
    @DeleteMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deleteDepartment(@PathVariable(value = "id")
                                           final Integer id) {
        LOGGER.debug("deleteDepartment({})", id);
        departmentService.deleteDepartmentById(id);
    }


    @PutMapping(value = "/departments")
    @ResponseStatus(HttpStatus.OK)
    void updateDepartment(@RequestBody Department department){
        LOGGER.debug("updateDepartment({})", department);
        departmentService.updateDepartment(department);
    }

}
