package com.epam.brest.course.client;
import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;
import com.epam.brest.course.service.TruckService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rest-client-test.xml")
public class TruckRestConsumerMockTest {

    /**
     * logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    private  static final double QTY = 23.0;
    private static final int ID = 1;
     private static final String DESCRIPTION = "New truck for trucks";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TruckService truckService;

    private static String DATE_STRING = "2004-02-02";

    private Truck truck;
    private TruckWIthAvgPetrolPerMonth truckWIthAvgPetrolPerMonth;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setup() throws ParseException {

        Date date = formatter.parse(DATE_STRING);
        truck = new Truck();
        truck.setTruckId(ID);
        truck.setPurchasedDate(date);
        truck.setTruckCode("BY2432");
        truck.setDescriptions(DESCRIPTION);

        truckWIthAvgPetrolPerMonth = new TruckWIthAvgPetrolPerMonth();
        truckWIthAvgPetrolPerMonth.setYear(ID);
        truckWIthAvgPetrolPerMonth.setMonth("JANUARY");
        truckWIthAvgPetrolPerMonth.setTruckCode("BY2432");
        truckWIthAvgPetrolPerMonth.setAvgPetrolQty(QTY);
    }

    @After
    public void tearDown() {
        Mockito.reset(restTemplate);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getTrucks() {
        LOGGER.debug("client test: getTrucks()");

        List trucks = Arrays.asList(truck);
        ResponseEntity entity = new ResponseEntity(trucks, HttpStatus.FOUND);

        when(restTemplate.getForEntity("http://localhost:8088/trucks", List.class))
                .thenReturn(entity);

        Collection<Truck> results
                = truckService.getAllTrucks();

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Mockito.verify(restTemplate).getForEntity("http://localhost:8088/trucks", List.class);
    }

//
//    @Test
//    public void getTrucksWithAvgPerMonth() {
//        LOGGER.debug("client test: getTrucksWithAvgPerMonth()");
//
//        List trucks = Arrays.asList(truckWIthAvgPetrolPerMonth);
//
//        ResponseEntity entity = new ResponseEntity<>(trucks, HttpStatus.FOUND);
//
//        when(restTemplate.getForEntity("http://localhost:8088/trucks/trucksAvgPetrol",
//                List.class))
//                .thenReturn(entity);
//
//        Collection<TruckWIthAvgPetrolPerMonth> results
//                = truckService.getAllTruckWithAvgPetrolPerMonth();
//
//        Assert.assertNotNull(results);
//        Assert.assertEquals(1, results.size());
//        Mockito.verify(restTemplate)
//                .getForEntity("http://localhost:8088/trucks/trucksAvgPetrol",
//                List.class);
//
//    }

    @Test
    public void getTruckId()  {
        LOGGER.debug("client test: getTruckId()");

         ResponseEntity entity = new ResponseEntity<>(truck, HttpStatus.FOUND);

        when(restTemplate.getForEntity("http://localhost:8088/trucks/1", Truck.class))
                .thenReturn(entity);

            Truck results = truckService.getTruckById(ID);

        Assert.assertNotNull(results);
        Mockito.verify(restTemplate)
                .getForEntity("http://localhost:8088/trucks/1", Truck.class);
    }



    @SuppressWarnings("unchecked")
    @Test
    public void addTruck() {
        LOGGER.debug("client test: addTruck()");

        ResponseEntity entity = new ResponseEntity<>(truck, HttpStatus.FOUND);

        when(restTemplate.postForEntity("http://localhost:8088/trucks",
                truck, Truck.class))
                .thenReturn(entity);

        Truck result = truckService.addTruck(truck);

        Assert.assertNotNull(result);
        Assert.assertEquals(ID, result.getTruckId().intValue());

        Mockito.verify(restTemplate).postForEntity("http://localhost:8088/trucks",
                truck, Truck.class);
    }

    @Test
    public void updateTruck() {
        LOGGER.debug("client test: updateTruck()");

        restTemplate.put("http://localhost:8088/trucks", truck);
        Mockito.verify(restTemplate).put("http://localhost:8088/trucks", truck);
    }

    @Test
    public void deleteTruck() {
        LOGGER.debug("client test: deleteTruck()");
        restTemplate.delete("http://localhost:8088/trucks/" + ID);

        Mockito.verify(restTemplate).delete("http://localhost:8088/trucks/" + ID);
    }
}
