package com.epam.brest.course.rest;

import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;
import com.epam.brest.course.service.TruckService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rest.spring.test.xml")
public class TruckRestControllerMockTest {

    private static final String TRUCK_CODE = "BY2442";
    private static final String DESCRIPTION = "NEW TRUCK";
    private static final String DESCRIPTION_1 = "BLACK TRUCK";
    private static final String TRUCK_CODE1 = "BY2606";
    private static final int TRUCK_ID = 2;
    private static final Double QTY = 123.0;
    private static Integer ID = 1;

    /**
     * Log class for debug.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TruckService truckService;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private TruckRestController truckRestController;

    private MockMvc mockMvc;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private Truck truck;

    private Truck truck2;

    private TruckWIthAvgPetrolPerMonth truckPerMonth;


    @Before
    public void setUp() {

        truck = new Truck();
        truck.setDescription(DESCRIPTION);
        truck.setTruckCode(TRUCK_CODE);
        truck.setTruckId(ID);

        truck2 = new Truck();
        truck2.setDescription(DESCRIPTION_1);
        truck2.setTruckCode(TRUCK_CODE1);
        truck2.setTruckId(TRUCK_ID);

        truckPerMonth = new TruckWIthAvgPetrolPerMonth();
        truckPerMonth.setTruckCode(TRUCK_CODE);
        truckPerMonth.setAvgPetrolQty(QTY);
        truckPerMonth.setMonth("JANUARY");
        truckPerMonth.setYear(2009);

        mockMvc = MockMvcBuilders.standaloneSetup(truckRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void getTruckById() throws Exception {
        LOGGER.debug("test: getTruckById()");
        when(truckService.getTruckById(ID)).thenReturn(truck);

        mockMvc.perform(get("/trucks/{id}", ID).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("truckId", Matchers.is(ID)))
                .andExpect(jsonPath("truckCode", Matchers.is(TRUCK_CODE)))
                .andExpect(MockMvcResultMatchers.jsonPath("description", Matchers.is(DESCRIPTION)));

        Mockito.verify(truckService).getTruckById(ID);
    }

        //update
    @Test
    public void update() throws Exception {
        LOGGER.debug("test: update() ");

        mockMvc.perform(put("/trucks").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(truck))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print()).andExpect(status().isOk());

        Mockito.verify(truckService).updateTruck(truck);
    }

        //delete
    @Test
    public void deleteTruck() throws Exception {
        LOGGER.debug("test: deleteTruck() ");

        mockMvc.perform(delete("/trucks/{truckId}", ID)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isFound());
        Mockito.verify(truckService).deleteTruckById(ID);
    }


       //getlist
    @Test
    public void getTrucks() throws Exception {
        LOGGER.debug("test: getTrucks()");

        when(truckService.getAllTrucks()).thenReturn(Arrays.asList(truck, truck2));

        mockMvc.perform(get("/trucks/truckList").accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]truckId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0]truckCode", Matchers.is(TRUCK_CODE)))
                .andExpect(jsonPath("$[0]description", Matchers.is(DESCRIPTION)))

                .andExpect(jsonPath("$[1]truckId", Matchers.is(TRUCK_ID)))
                .andExpect(jsonPath("$[1]truckCode", Matchers.is(TRUCK_CODE1)))
                .andExpect(jsonPath("$[1]description", Matchers.is(DESCRIPTION_1)));

        Mockito.verify(truckService).getAllTrucks();
    }

     @Test
    public void trucksWithAvg() throws Exception {

        LOGGER.debug("test: trucksWithAvg()");

        when(truckService.getAllTruckWithAvgPetrolPerMonth())
                .thenReturn(Arrays.asList(truckPerMonth));

        mockMvc.perform(get("/trucks/trucksAvgPetrol")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(jsonPath("$[0]truckCode", Matchers.is(TRUCK_CODE)))
                .andExpect(jsonPath("$[0]avgPetrolQty", Matchers.is(QTY)))
                .andExpect(jsonPath("$[0]month", Matchers.is("JANUARY")))
                .andExpect(jsonPath("$[0]year", Matchers.is(2009)));

        Mockito.verify(truckService).getAllTruckWithAvgPetrolPerMonth();

    }

}
