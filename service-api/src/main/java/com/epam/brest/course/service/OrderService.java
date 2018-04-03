package com.epam.brest.course.service;

import com.epam.brest.course.dto.OrderWithTruckCodeDto;
import com.epam.brest.course.model.Order;
import org.springframework.dao.DataAccessException;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

/**
 * Order service abstracted methods.
 */
public interface OrderService {

    /**
     *
     * @return collection.
     */
    Collection<OrderWithTruckCodeDto> getAllOrdersWithTruckCode()
                                            throws DataAccessException;

    /**
     *
     * @param id to get order.
     * @return order.
     */
    Order getOrderById(Integer id) throws DataAccessException;

    /**
     *
     * @param order for add.
     * @return new order.
     */
    Order addOrder(Order order) throws DataAccessException;

    /**
     *
     * @param id by id.
     */
    void deleteOrderById(Integer id) throws DataAccessException;

    /**
     *
     * @param order for update.
     */
    void updateOrder(Order order) throws DataAccessException;

    /**
     *
     * @return list of all orders.
     * @throws DataAccessException
     */
    Collection<Order> getAllOrder() throws DataAccessException;

    /**
     *
     * @param start date.
     * @param end date.
     * @return collection that matches criteria.
     * @throws ParseException text exception.
     */
    Collection<OrderWithTruckCodeDto> filterOrdersByDate(Date start, Date end)
            throws ParseException, DataAccessException;

}
