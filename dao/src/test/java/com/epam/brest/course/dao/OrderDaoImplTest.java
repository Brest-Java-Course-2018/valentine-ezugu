package com.epam.brest.course.dao;

import com.epam.brest.course.dto.OrderWithTruckCodeDto;
import com.epam.brest.course.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:dao-test.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class OrderDaoImplTest {

    private static final String DATE = "2006-01-21";
    private static final int TRUCK_ID = 3;
    private static final double PETROL_QTY = 23.0;
    public static final int ONE = 1;

    @Autowired
    private OrderDao orderDao;


    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DATE_STRING = "2007-01-01";
    public static final String DATE_STRING_2 = "2009-01-01";


    @Test
    public void getOrderById() {
        Order order = orderDao.getOrderById(ONE);
        Assert.assertNotNull(order);
        Assert.assertTrue(order.getOrderId().equals(ONE));
        Assert.assertTrue(order.getTruckId().equals(TRUCK_ID));
    }

    @Test
    public void getAllOrderWithTruckCodeDto() {
        Collection<OrderWithTruckCodeDto> orders =
                orderDao.getAllOrdersWithTruckCode();
        Assert.assertFalse(orders.isEmpty());
        Assert.assertTrue(orders.size() == 7);
    }

    @Test
    public void getAllOrders() {
        Collection<Order> orderCollection = orderDao.getAllOrders();
        Assert.assertFalse(orderCollection.isEmpty());
        Assert.assertTrue(orderCollection.size() == 7);
    }

    @Test
    public void addOrder() throws ParseException {

        Date date = formatter.parse(DATE);
        Collection<Order> orders = orderDao.getAllOrders();
        int sizeBefore = orders.size();
        // add order
        Order order = new Order(234.3, date, ONE);
        Order order1 = orderDao.addOrder(order);
        Assert.assertNotNull(order1);
        Assert.assertTrue(order.getOrderDate().equals(date));
        Assert.assertTrue((sizeBefore + 1) == orderDao.getAllOrders().size());
    }

    @Test
    public void updateOrder() throws Exception {
        // set date
        Date date = formatter.parse(DATE);
        //pass date to new order
        Order order = new Order(25.0, date, TRUCK_ID);
        // add order
        Order newOrder = orderDao.addOrder(order);
        newOrder.setOrderDate(date);
        //set new truck code for order
        newOrder.setTruckId(5);
        newOrder.setPetrolQty(PETROL_QTY);
        //perform update
        orderDao.updateOrder(newOrder);
        //assert
        Order updatedOrder = orderDao.getOrderById(newOrder.getOrderId());
        Assert.assertTrue(updatedOrder.getOrderId().equals(newOrder.getOrderId()));
    }


    @Test
    public void deleteOrderById() throws ParseException {

        Date date = formatter.parse(DATE);

        Order order =
                new Order(PETROL_QTY, date, TRUCK_ID);
        order = orderDao.addOrder(order);

        Collection<Order> orders = orderDao.getAllOrders();
        //get size before delete
        int sizeBefore = orders.size();

        orderDao.deleteOrderById(order.getOrderId());
        orders = orderDao.getAllOrders();
       //get size after delete
        int sizeAfter = orders.size();
        //assert there was a reduction after delete
        Assert.assertTrue(sizeAfter < sizeBefore);
    }

    @Test
    public void filterByDate() throws ParseException {

        Date date = formatter.parse(DATE_STRING);
        Date date2 = formatter.parse(DATE_STRING_2);

        Collection<OrderWithTruckCodeDto> orders =   orderDao.filterOrdersByDate(date, date2);
     Assert.assertNotNull(orders);
     Assert.assertTrue(orders.size() > 1 );
    }

    @Test
    public void doFilterWithNoNullValuesShouldGiveDefault() throws ParseException {

        Collection<OrderWithTruckCodeDto> orders =   orderDao.filterOrdersByDate(null, null);
        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() > 6 );
    }

}