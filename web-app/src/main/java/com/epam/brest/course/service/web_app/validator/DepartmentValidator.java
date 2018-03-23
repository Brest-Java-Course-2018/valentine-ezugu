package com.epam.brest.course.service.web_app.validator;

import com.epam.brest.course.model.Department;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * department validator class to be passed in controller.
 */
public class DepartmentValidator implements Validator {

    /**
     * min name length for head of department.
     */
    private static final int MIN_HEAD_OF_DEPARTMENT_LENGTH = 2;
    /**
     * max name length for head of department.
     */
    private static final int MAX_LENGTH_OF_HEAD_OF_DEPARTMENT = 25;
    /**
     *  min name length for department name.
     */
    private static final int MIN_DEPARTMENT_NAME_LENGTH = 2;
    /**
     * max name length for department name.
     */
    private static final int MAX_DEPARTMENT_NAME_LENGTH = 250;

    @Override
    public final boolean supports(final Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public final void validate(final Object obj, final Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "departmentName", "departmentName.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "description", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "headOfDepartment", "field.required");

        Department department = (Department) obj;

        if (department.getHeadOfDepartment().length()
                < MIN_HEAD_OF_DEPARTMENT_LENGTH) {
            e.rejectValue("headOfDepartment", "too short");

        } else if (department.getHeadOfDepartment().length()
                > MAX_LENGTH_OF_HEAD_OF_DEPARTMENT) {
            e.rejectValue("headOfDepartment", "too long");
        }
        if (department.getDepartmentName().length()
                < MIN_DEPARTMENT_NAME_LENGTH) {
            e.rejectValue("description", "description is too short");

        } else if (department.getDepartmentName().length()
                > MAX_DEPARTMENT_NAME_LENGTH) {
            e.rejectValue("description", "To long description");
        }
     }
  }

