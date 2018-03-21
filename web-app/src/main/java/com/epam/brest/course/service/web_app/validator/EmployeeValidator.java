package com.epam.brest.course.service.web_app.validator;

import com.epam.brest.course.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * employee validator  to passed in controller.
 */
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e, "employeeName", "employeeName.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e, "description", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "field.required");

        Employee employee = (Employee) obj;

        if (employee.getSalary() < 250) {
            e.rejectValue("salary", "too small");
        } else if (employee.getSalary() > 10000) {
            e.rejectValue("salary", "too large", "too large");
        }
        if (employee.getEmployeeName().length() < 2) {
            e.rejectValue("employeeName", "name cannot be of two letters, please fix",
                    "name cannot be of two letters, please fix");
        } else if (employee.getEmployeeName().length()  > 30) {
            e.rejectValue("employeeName", "employeeName too long",
                    "employeeName too long");
        }
    }
}
