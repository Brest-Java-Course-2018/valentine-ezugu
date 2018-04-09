package com.epam.brest.course.utility.validator;

import com.epam.brest.course.utility.data.OrderDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "petrolQty", "petrolQty.empty", "enter petrol qty");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "orderDate", "orderDate.empty", "enter the order date");

        ValidationUtils.rejectIfEmptyOrWhitespace(e,
                "truckId", "truckId.empty", "choose truck");

        OrderDto orderDto = (OrderDto) obj;

        if (orderDto.getPetrolQty() == null || orderDto.getPetrolQty() < 12.0) {
            e.rejectValue("petrolQty", "petrol.small",
                    "petrol qty must be above 249");
        } else if (orderDto.getPetrolQty() > 500.0) {
            e.rejectValue("salary", "petrol.large",
                    "salary must be above 249");
        }

    }
}

