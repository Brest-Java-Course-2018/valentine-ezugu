package com.epam.brest.course.service.web_app.validator;

import com.epam.brest.course.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * employee validator  to passed in controller.
 */
public class EmployeeValidator implements Validator {
    /**
     * minimum salary for employee.
     */
    private static final int MIN_SALARY = 250;
    /**
     * maximum salary for employee.
     */
    private static final int MAX_SALARY = 1000;
    /**
     * name length.
     */
    private static final int NAME_LENGTH = 30;
    /**
     * minimum name length.
     */
    private static final int MIN_NAME_LENGTH = 2;

    @Override
    public final boolean supports(final Class<?> clazz) {

        return Employee.class.equals(clazz);
    }

    /**
     *
     * @param obj to validate.
     * @param e error param.
     */
    @Override
    public final void validate(final Object obj, final Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "employeeName", "employeeName.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "description", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "email", "field.required");

        Employee employee = (Employee) obj;

        if (employee.getSalary() < MIN_SALARY) {
            e.rejectValue("salary", "too small");
        } else if (employee.getSalary() > MAX_SALARY) {

            e.rejectValue("salary", "too large", "too large");
        }
        if (employee.getEmployeeName().length() < MIN_NAME_LENGTH) {
            e.rejectValue("employeeName",
                    "name cannot be of two letters, please fix",
                    "name cannot be of two letters, please fix");

        } else if (employee.getEmployeeName().length()  > NAME_LENGTH) {
            e.rejectValue("employeeName", "employeeName too long",
                    "employeeName too long");
        }
    }
}
