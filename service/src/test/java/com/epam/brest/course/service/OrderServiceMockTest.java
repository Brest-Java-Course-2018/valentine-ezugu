package com.epam.brest.course.service;

import com.epam.brest.course.dao.OrderDao;
import com.epam.brest.course.dto.OrderWithTruckCodeDto;
import com.epam.brest.course.model.Order;
import com.epam.brest.course.service.mockConfig.MockConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MockConfig.class, OrderServiceImpl.class})
public class OrderServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final double PETROL_QTY = 435.3;
    private static final Integer ID = 15;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private static final String DATE_STRING = "2007-01-01";
    private static final String DATE_STRING_2 = "2009-01-01";
    private static int TRUCK_ID = 1;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    private Order newOrder;
    private OrderWithTruckCodeDto order;
    private OrderWithTruckCodeDto order1;

    @Before
    public void setup() {
        order = new OrderWithTruckCodeDto();
        order1 = new OrderWithTruckCodeDto();
    }

    @Test
    public void addOrder() throws Exception {
        LOGGER.debug("test: addOrder()");

        Date date = formatter.parse(DATE_STRING_2);

        Order order = new Order(PETROL_QTY, date, TRUCK_ID);

        when(orderDao.addOrder(order))
                .thenReturn(order);

        newOrder = orderService.addOrder(order);

        Assert.assertNotNull(newOrder);
        Assert.assertEquals(newOrder.getTruckId(),
                order.getTruckId());

        Mockito.verify(orderDao)
                .addOrder(order);
    }


    @Test
    public void getOrderById() throws Exception {
        LOGGER.debug("test: getOrderById()");

        Order order = new Order();
        order.setOrderId(ID);
        when(orderDao.getOrderById(Mockito.anyInt()))
                .thenReturn(order);

        newOrder = orderService.getOrderById(ID);
        Assert.assertEquals(order.getOrderId(),
                order.getOrderId());

        Mockito.verify(orderDao)
                .getOrderById(Mockito.anyInt());
    }

    /**
     * @throws Exception in case of rule violation.
     */
    @Test
    public void deleteOrderById() throws Exception {
        LOGGER.debug("test: deleteOrderById()");

        Order order = new Order();
        order.setOrderId(ID);

        orderService.deleteOrderById(ID);

        //verify that dao's delete was called
        Mockito.verify(orderDao).deleteOrderById(ID);
    }


    @Test
    public void getOrders() throws Exception {
        LOGGER.debug("test: getOrders()");

        Order firstOrder = new Order();
        Order secondOrder = new Order();

        when(orderDao.getAllOrders())
                .thenReturn(Arrays.asList(firstOrder, secondOrder));
        Collection<Order> orders = orderService.getAllOrders();

        Assert.assertEquals(orders.size(), 2);
        Assert.assertTrue(orders.contains(firstOrder));
        //verify that dao getAll orders was called
        Mockito.verify(orderDao).getAllOrders();
    }

    /**
     * @throws Exception is thrown because order cannot be null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenIncompleteArgumentIsGiven() throws Exception {
        orderService.addOrder(null);
    }


    @Test
    public void updateAnOrder() throws Exception {
        LOGGER.debug("test: updateAnOrder()");
        Order order = new Order();
        order.setPetrolQty(PETROL_QTY);

        orderService.updateOrder(order);

        Assert.assertNotNull(order);
        //verify that we called the dao when update occurs
        Mockito.verify(orderDao).updateOrder(order);
    }

    @Test
    public void getAllOrdersWithTruckCode() {
        LOGGER.debug("test: getAllOrdersWithTruckCode()");

        when(orderDao.getAllOrdersWithTruckCode())
                .thenReturn(Arrays.asList(order, order1));

        Collection<OrderWithTruckCodeDto> getAllOrdersWithTruckCode =
                orderService.getAllOrdersWithTruckCode();

        //assertions
        Assert.assertTrue(getAllOrdersWithTruckCode.containsAll(Arrays.asList(order,order1)));
        Mockito.verify(orderDao).getAllOrdersWithTruckCode();
    }

    @Test
    public void filterOrdersByDate() throws ParseException {
        LOGGER.debug("test: filterOrdersByDate");

        Date date = formatter.parse(DATE_STRING);
        Date date1 = formatter.parse(DATE_STRING_2);

        when(orderDao.filterOrdersByDate(date,date1))
                .thenReturn(Arrays.asList(order, order1));


        Collection<OrderWithTruckCodeDto> filterOrdersByDate =
                             orderService.filterOrdersByDate(date, date1);

        //assertions
        Assert.assertTrue(filterOrdersByDate.containsAll(Arrays.asList(order, order1)));
        Mockito.verify(orderDao).filterOrdersByDate(date,date1);
    }


    @Test
    public void findOneOrder_ThrowsIllegalArgs() {

        LOGGER.debug("test: findOneOrder_ThrowsIllegalArgs()");

        when(orderDao.getOrderById(null)).thenThrow(new IllegalArgumentException("") {
        });

        exception.expect(IllegalArgumentException.class);
        orderDao.getOrderById(null);
    }

}
