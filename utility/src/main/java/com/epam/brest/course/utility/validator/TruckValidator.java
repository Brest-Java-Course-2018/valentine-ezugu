package com.epam.brest.course.utility.validator;

import com.epam.brest.course.utility.data.TruckDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * validator from spring jsr303.
 */
public class TruckValidator implements Validator {


    private static final int MIN_TRUCK_CODE = 5;
    private static final int MAX_TRUCK_CODE = 7;

    /**
     * @param clazz generic.
     * @return truck.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return TruckDto.class.equals(clazz);
    }

    /**
     * @param obj target.
     * @param e errors.
     */
    @Override
    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "truckCode", "truckCode.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "purchasedDate", "purchasedDate.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "description", "description.empty");

        TruckDto truckDto = (TruckDto) obj;

        if (truckDto.getTruckCode().length() < MIN_TRUCK_CODE) {
            e.rejectValue("truckCode", "truckCode.short");
        } else if (truckDto.getTruckCode().length() > MAX_TRUCK_CODE) {
            e.rejectValue("truckCode", "truckCode.long");
        }

    }
}
