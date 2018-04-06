package com.epam.brest.course.service;

import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.xml", "classpath:bean-test.xml",
        "classpath:test-db-spring.xml"})
@Transactional
@Rollback
public class TruckServiceImplTest {

    /*** logger*/
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int ONE = 1;
    private static final int SIZE = 5;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private static final String DATE_STRING = "2007-01-01";

    @Autowired
    private TruckService truckService;

    @Test
    public void getTruckCollection() {
        LOGGER.debug("test: getTruckCollection()");

        Collection<Truck> trucks = truckService.getAllTrucks();

        Assert.assertNotNull(trucks);
        Assert.assertFalse(trucks.isEmpty());
        Assert.assertTrue(trucks.size() > SIZE);
    }

    @Test
    public void getTruckById() {
        LOGGER.debug("test: getTruckById()");

        Truck truck = truckService.getTruckById(ONE);

        //Assertions
        Assert.assertNotNull(truck);
        Assert.assertEquals(truck.getDescription(), "AUDI TRUCK");
    }

    @Test
    public void getTruckWithAvgPetrolUsed() {
        LOGGER.debug("test: getTruckWithAvgPetrolUsed()");

        Collection<TruckWIthAvgPetrolPerMonth> truckWIthAvgPetrolPerMonths =
                truckService.getAllTruckWithAvgPetrolPerMonth();

        //assertions
        Assert.assertNotNull(truckWIthAvgPetrolPerMonths);
        Assert.assertFalse(truckWIthAvgPetrolPerMonths.isEmpty());
        Assert.assertTrue(truckWIthAvgPetrolPerMonths.size() > SIZE);
    }

    @Test
    public void addNewTruck() throws Exception {
        LOGGER.debug("test: addNewTruck()");

        Date date = formatter.parse(DATE_STRING);

        Truck truckToAdd = new Truck("BY9000", date, "TESLA-BUS");

        Truck addedTruck = truckService.addTruck(truckToAdd);
        // this is line i use truckService to get truck but this is a add truck test
        Truck truck = truckService.getTruckById(addedTruck.getTruckId());
        //assertions
        Assert.assertEquals(addedTruck.getTruckId(), truck.getTruckId());
        Assert.assertEquals(addedTruck.getTruckCode(), truck.getTruckCode());

    }


    @Test(expected = IllegalArgumentException.class)
    public void deleteTruckById() throws Exception {
        LOGGER.debug("test: deleteTruckById()");
        truckService.deleteTruckById(null);
    }

    @Test
    public void deleteTruckWithValidId() throws Exception {
        LOGGER.debug("test: deleteTruckWithValidId()");
        Collection<Truck> collection = truckService.getAllTrucks();
        int sizeBefore = collection.size();

        truckService.deleteTruckById(ONE);
        Collection<Truck> collection2 = truckService.getAllTrucks();
        int sizeAfter = collection2.size();
        Assert.assertTrue((sizeAfter + 1) == sizeBefore);
    }

    @Test
    public void updateTruck() {
        LOGGER.debug("test: updateTruck()");

        Truck truck = truckService.getTruckById(ONE);
        Assert.assertEquals(truck.getDescription(), "AUDI TRUCK");

        truck.setDescription("BMW TRUCK");
        truckService.updateTruck(truck);
        Truck truckWithUpdate = truckService.getTruckById(ONE);
        Assert.assertEquals(truckWithUpdate.getDescription(), "BMW TRUCK");

    }
}
