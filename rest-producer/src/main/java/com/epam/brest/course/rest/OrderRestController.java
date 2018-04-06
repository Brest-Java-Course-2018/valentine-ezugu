package com.epam.brest.course.rest;

import com.epam.brest.course.dto.OrderWithTruckCodeDto;
import com.epam.brest.course.model.Order;
import com.epam.brest.course.service.OrderService;
import com.epam.brest.course.utility.data.OrderDto;
import com.epam.brest.course.utility.data.OrderWithTruckCodeForList;
import com.epam.brest.course.utility.dozer.MappingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * rest controller for order.
 */
@RestController
public class OrderRestController {
    /**
     * Log class for debug.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     *service.
     */
    @Autowired
    private OrderService orderService;
    /**
     * simple formatter.
     */
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     *mapping service.
     */
    @Autowired
    private MappingService mappingService;

    /**
     *
     * @param orderId for get.
     * @return order.
     */
    @GetMapping("/orders/{orderId}")
    @ResponseStatus(HttpStatus.FOUND)
    public final OrderDto getOrderId(@PathVariable(value = "orderId")
                                                    final Integer orderId) {
        LOGGER.debug("getOrderId({})", orderId);

        // get order then map to dto
        Order order = orderService.getOrderById(orderId);

        return mappingService.map(order, OrderDto.class);
    }

    /**
     *
     * @return list with truckCode.
     */
    @GetMapping(value = "/orders/ordersWithTruckCode")
    public final Collection<OrderWithTruckCodeForList> ordersWithTruckCode() {
        LOGGER.debug("ordersWithTruckCode()");

        //get list first then transfer to dozer data obj
        Collection<OrderWithTruckCodeDto> orders =
                orderService.getAllOrdersWithTruckCode();

        return mappingService.map(orders, OrderWithTruckCodeForList.class);
    }

    /**
     * @param orderDto for add.
     * @return persisted model through dto.
     */
    @PostMapping(value = "/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public final OrderDto addOrder(@Valid @RequestBody
                                                final OrderDto orderDto) {
        LOGGER.debug("addOrder({})", orderDto);

        //take dto and convert to model
        Order mappedOrder = mappingService.map(orderDto, Order.class);
        //persist model
        Order persistedOrder = orderService.addOrder(mappedOrder);
        //convert to dto for return
        return mappingService.map(persistedOrder, OrderDto.class);
    }

    /**
     * @param orderDto for update.
     */
    @PutMapping(value = "/orders")
    @ResponseStatus(HttpStatus.OK)
    public final void updateOrder(@Valid @RequestBody
                                            final OrderDto orderDto) {

        LOGGER.debug("updateOrder({})", orderDto);

            // transfer data to model then update
        Order mappedOrder = mappingService.map(orderDto, Order.class);

        orderService.updateOrder(mappedOrder);
    }

    /**
     * @param orderId param.
     */
    @DeleteMapping(value = "/orders/{orderId}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deleteOrder(@PathVariable(value = "orderId")
                                                final Integer orderId) {

        LOGGER.debug("deleteOrder({})", orderId);
            //delete
        orderService.deleteOrderById(orderId);
    }

    /**
     * @return order list.
     */
    @GetMapping(value = "/orders")
    public final Collection<OrderDto> getOrders() {
        LOGGER.debug("getOrders()");

        Collection<Order> orders = orderService.getAllOrders();
        // copy all orders to order dto.
        return mappingService.map(orders, OrderDto.class);
    }

    /**
     *
     * @param start date.
     * @param end date.
     * @return list after filter.
     * @throws ParseException .
     */
    @GetMapping(value = "/orders/from/{start}/to/{end}")
    public final Collection<OrderWithTruckCodeForList>
    filterOrderByDate(@PathVariable(value = "start")final String start,
                                @PathVariable(value = "end") final String end)
                                                      throws ParseException {
        LOGGER.debug("filterOrderByDate({})", start, end);

        Collection<OrderWithTruckCodeDto> orders =
                                 orderService.filterOrdersByDate(new
                                         Date(formatter.parse(start)
                                         .getTime()),
                                         new Date(formatter.parse(end)
                                                 .getTime()));
        //mapped filtered list to dto
        return mappingService.map(orders, OrderWithTruckCodeForList.class);

    }

}
