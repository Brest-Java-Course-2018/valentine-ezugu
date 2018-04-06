package com.epam.brest.course.rest;


import com.epam.brest.course.dto.OrderWithTruckCodeDto;
import com.epam.brest.course.model.Order;
import com.epam.brest.course.service.OrderService;
import com.epam.brest.course.utility.data.OrderDto;
import com.epam.brest.course.utility.dozer.MappingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rest.spring.test.xml")
public class OrderRestControllerMockTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final double QTY = 23.0;
    private static final long VALUE = 1078092000000L;
    private static final int ID_1 = 2;
    private static final String TRUCK_CODE = "BY234";
    private static Integer ID = 1;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private OrderRestController orderRestController;

    //orders
    private Order order;
    private Order order1;

    //order with truckCode
    private OrderWithTruckCodeDto orderWithTruckCodeDto;

    private OrderDto orderDto;

    @Autowired
    private MappingService mappingService;


    @Autowired
    private OrderService orderService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        order = new Order();
        order.setOrderId(ID);
        order.setPetrolQty(QTY);
        order.setTruckId(ID);

        order1 = new Order();
        order1.setOrderId(ID_1);
        order1.setPetrolQty(QTY);
        order1.setTruckId(ID_1);

        orderDto = new OrderDto();
        orderDto.setOrderId(1);
        orderDto.setPetrolQty(QTY);
        orderDto.setTruckId(ID);

        orderWithTruckCodeDto = new OrderWithTruckCodeDto();
        orderWithTruckCodeDto.setPetrolQty(QTY);
        orderWithTruckCodeDto.setOrderDate(new Date());
        orderWithTruckCodeDto.setOrderId(ID);
        orderWithTruckCodeDto.setTruckCode(TRUCK_CODE);

        mockMvc = MockMvcBuilders.standaloneSetup(
                orderRestController)
                .setMessageConverters(
                        new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void getOrderById() throws Exception {
        LOGGER.debug("test: getOrderById() ");

        order.setOrderDate(new Date(formatter.parse("2004-03-01").getTime()));

        when(orderService.getOrderById(ID)).thenReturn(order);

        mockMvc.perform(get("/orders/{orderId}", ID).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("orderId", Matchers.is(ID)))
                .andExpect(jsonPath("petrolQty", Matchers.is(QTY)))
                .andExpect(jsonPath("orderDate", Matchers.is(VALUE)))
                .andExpect(jsonPath("truckId", Matchers.is(ID)));

        Mockito.verify(orderService).getOrderById(ID);
    }



    @Test
    public void update() throws Exception {
        LOGGER.debug("test: update() ");

        mockMvc.perform(put("/orders").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(order))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print()).andExpect(status().isOk());

        Mockito.verify(orderService).updateOrder(order);
    }


    @Test
    public void getAllOrders() throws Exception {
        LOGGER.debug("test: getAllOrders()");

        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order, order1));

    mockMvc.perform(get("/orders").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$[0]orderId", Matchers.is(ID)))
            .andExpect(jsonPath("$[0]petrolQty", Matchers.is(QTY)))
            .andExpect(jsonPath("$[0]truckId", Matchers.is(ID)))

            .andExpect(jsonPath("$[1]orderId", Matchers.is(ID_1)))
            .andExpect(jsonPath("$[1]petrolQty", Matchers.is(QTY)))
            .andExpect(jsonPath("$[1]truckId", Matchers.is(ID_1)));

            Mockito.verify(orderService).getAllOrders();
    }


    @Test
    public void filterOrderByDate() throws Exception {
        LOGGER.debug("test: filterOrderByDate()");

        when(orderService.filterOrdersByDate(
                new Date(formatter.parse("2003-02-01").getTime()),
                new Date(formatter.parse("2009-02-01").getTime())))
                .thenReturn(Arrays.asList(orderWithTruckCodeDto));

        mockMvc.perform(get("/orders/from/{start}/to/{end}", "2003-02-01", "2009-02-01")

                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]orderId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0]petrolQty", Matchers.is(QTY)))
                .andExpect(jsonPath("$[0]truckCode", Matchers.is(TRUCK_CODE)));

        Mockito.verify(orderService).filterOrdersByDate(new Date(formatter.parse("2003-02-01").getTime()),
                new Date(formatter.parse("2009-02-01").getTime()));
    }


    @Test
    public void ordersListWithTruck() throws Exception {

        LOGGER.debug("test: ordersListWithTruck()");

        when(orderService.getAllOrdersWithTruckCode()).thenReturn(Arrays.asList(orderWithTruckCodeDto));

        mockMvc.perform(get("/orders/ordersWithTruckCode")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]orderId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0]petrolQty", Matchers.is(QTY)))
                .andExpect(jsonPath("$[0]truckCode", Matchers.is(TRUCK_CODE)));

                Mockito.verify(orderService).getAllOrdersWithTruckCode();

     }

}
