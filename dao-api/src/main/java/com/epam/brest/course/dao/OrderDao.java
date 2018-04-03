package com.epam.brest.course.dao;


import com.epam.brest.course.dto.OrderWithTruckCodeDto;
import com.epam.brest.course.model.Order;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

/**
 *dao interface.
 */
public interface OrderDao {
    /**
     *
     * @return orders.
     */
    Collection<Order> getAllOrders();

    /**
     *
     * @return collection.
     */
    Collection<OrderWithTruckCodeDto> getAllOrdersWithTruckCode();

    /**
     *
     * @param id to get order.
     * @return order.
     */
      Order getOrderById(Integer id);

    /**
     *
     * @param order for add.
     * @return new order.
     */
    Order addOrder(Order order);

    /**
     *
     * @param id by id.
     */
    void deleteOrderById(Integer id);

    /**
     *
     * @param order for update.
     */
    void updateOrder(Order order);

    /**
     *
     * @param from start date.
     * @param to end date.
     * @return collection that matches criteria.
     * @throws ParseException text exception.
     */
    Collection<OrderWithTruckCodeDto> filterOrdersByDate(Date from, Date to)
                                        throws ParseException;
}
