package com.epam.brest.course;

import com.epam.brest.course.model.Order;

import org.junit.Assert;
import org.junit.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderTest {



    private static final Integer ID = 1;
    private static final Double QTY = 222.0;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    private static String DATE ="2004-33-01" ;

    @Test
    public void truckTest() throws ParseException {
        Date date  = format.parse(DATE);
        Order order = new Order();
        order.setTruckId(ID);
        order.setOrderId(ID);
        order.setOrderDate(date);
        order.setPetrolQty(QTY);


        //asserts
        Assert.assertEquals(order.getTruckId(),ID);
        Assert.assertEquals(order.getOrderId(), ID);
        Assert.assertEquals(order.getOrderDate(), date);
        Assert.assertEquals(QTY, order.getPetrolQty());

        String toOrderString  = "Order{"
                + "orderId=" + ID
                + ", petrolQty=" + QTY
                + ", orderDate=" + date
                + ", truckId=" + ID
                + '}';

        Assert.assertEquals(toOrderString,order.toString());
    }

}
