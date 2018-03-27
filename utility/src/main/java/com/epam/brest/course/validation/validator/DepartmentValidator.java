package com.epam.brest.course.validation.validator;

import com.epam.brest.course.model.Department;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;


/**
 * department validator class to be passed in controller.
 */
public class DepartmentValidator {

    /**
     * min name length for head of department.
     */
    private static final int MIN_HEAD_OF_DEPARTMENT_LENGTH = 2;
    /**
     * max name length for head of department.
     */
    private static final int MAX_LENGTH = 25;
    /**
     *  min name length for department name.
     */
    private static final int MIN_DEPARTMENT_NAME_LENGTH = 2;
    /**
     * max name length for department name.
     */
    private static final int MAX_DEPARTMENT_NAME_LENGTH = 250;

    /**
     *
     * @param clazz .
     * @return .
     */
    public final boolean supports(final Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    /**
     *
     * @param obj for validation.
     * @param e error.
     */
    public static void validate(final Object obj, final Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "departmentName", "departmentName.empty",
                "enter name or clear whitespace");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "description", "field.required",
                "enter description");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "headOfDepartment", "field.required",
                "enter head of department name");

        Department department = (Department) obj;

        if (department.getHeadOfDepartment().length()
                < MIN_HEAD_OF_DEPARTMENT_LENGTH) {
                e.rejectValue("headOfDepartment",
                        "too short", "Minimum is two letters");
        } else if (department.getHeadOfDepartment().length() > MAX_LENGTH) {
            e.rejectValue("headOfDepartment",
                    "too long", "name should be less than 26 chars");
        }
        if (department.getDepartmentName().length()
                < MIN_DEPARTMENT_NAME_LENGTH) {
            e.rejectValue("departmentName",
                    "department name is too short",
                    "department name is too short");

        } else if (department.getDepartmentName().length()
                > MAX_DEPARTMENT_NAME_LENGTH) {
            e.rejectValue("departmentName",
                    "To long department name", "To long department name");
        }
     }
  }

