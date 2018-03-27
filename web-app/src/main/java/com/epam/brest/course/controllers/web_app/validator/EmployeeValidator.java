package com.epam.brest.course.controllers.web_app.validator;

import com.epam.brest.course.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * employee validator  to passed in controller.
 */
public class EmployeeValidator  {
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
    private static final int MAX_NAME_LENGTH = 30;
    /**
     * minimum name length.
     */
    private static final int MIN_NAME_LENGTH = 3;


    public final boolean supports(final Class<?> clazz) {

        return Employee.class.equals(clazz);
    }

    /**
     * @param obj to validate.
     * @param e   error param.
     */
    public void validate(final Object obj, final Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "employeeName", "employeeName.empty", "name cannot be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "email", "email.empty", "email cannot be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "salary", "field.required", "salary cannot be empty");


        Employee employee = (Employee) obj;

        if (employee.getSalary() < MIN_SALARY) {
            e.rejectValue("salary", "too small",
                    "salary must be above 249");
        } else if (employee.getSalary() > MAX_SALARY) {

            e.rejectValue("salary", "too large",
                    "salary must be above 249");
        }

        if (employee.getEmployeeName().length() < MIN_NAME_LENGTH) {
            e.rejectValue("employeeName",
                    "minimum of three letters",
                    "minimum of three letters");

        } else if (employee.getEmployeeName().length() > MAX_NAME_LENGTH) {
            e.rejectValue("employeeName", "employeeName too long",
                    "employeeName too long");
        }
        if (!EmailValidator.EMAIL_PATTERN.matcher(employee.getEmail()).matches()) {
            e.rejectValue("email",
                    "please enter correct email",
                    "please enter correct email");
        }


  }
}