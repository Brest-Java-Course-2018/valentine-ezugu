package com.epam.brest.course.service.web_app.validator;

import com.epam.brest.course.model.Department;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * department validator class to be passed in controller.
 */
public class DepartmentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "departmentName", "departmentName.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "description", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "headOfDepartment", "field.required");

        Department department = (Department) obj;

        if (department.getHeadOfDepartment().length() < 2) {
            e.rejectValue("headOfDepartment", "too short");
        } else if (department.getHeadOfDepartment().length() > 25 ) {
            e.rejectValue("headOfDepartment", "too long");
        }
        if (department.getDepartmentName().length() < 2) {
            e.rejectValue("description", "description is too short");
        } else if (department.getDepartmentName().length()  > 250) {
            e.rejectValue("description", "To long description");
        }
     }
  }

