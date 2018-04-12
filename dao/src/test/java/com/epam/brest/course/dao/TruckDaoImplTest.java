package com.epam.brest.course.dao;

import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:dao-test.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class TruckDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DATE = "2006-01-21";

    private SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

    private static final int ID = 2;
    private static final int SIZE = 5;

    @Autowired
    private TruckDao truckDao;


    @Test(expected = EmptyResultDataAccessException.class)
    public void getTruckByIdWithNullValue() {
        LOGGER.debug("test: getTruckByIdWithNullValue()");

        //give null value we should get empty result .
       Truck truck =  truckDao.getTruckById(null);
        Assert.assertNotNull(truck);
    }

    @Test
    public void getTruckByIdWithValidInput() {
        LOGGER.debug("test: getTruckByIdWithValidInput()");

        Truck truck =  truckDao.getTruckById(ID);
        Assert.assertNotNull(truck);
    }

    @Test
    public void getAllTrucks() {
        LOGGER.debug("test: getAllTrucks()");


        Collection<Truck> trucks = truckDao.getAllTrucks();
        //assert we have list of trucks
        Assert.assertNotNull(trucks);
        Assert.assertTrue(trucks.size() > SIZE);
    }

    @Test
    public void getAllTrucksWithAvgPetrolPerMonth()  {
        LOGGER.debug("test: getAllTrucksWithAvgPetrolPerMonth()");


        Collection<TruckWIthAvgPetrolPerMonth> truckWIthAvgPetrolPerMonths =
                truckDao.getAllTruckWithAvgPetrolPerMonth();
        //get all trucks with calculated average petrol used
        Assert.assertNotNull(truckWIthAvgPetrolPerMonths);
        Assert.assertFalse(truckWIthAvgPetrolPerMonths.isEmpty());
        Assert.assertTrue(truckWIthAvgPetrolPerMonths.size() > SIZE);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void addTruckWithTruckCodeAlreadyInUse() throws Exception {
        LOGGER.debug("test: addTruckWithTruckCodeAlreadyInUse()");

      Date date = formatter.parse(DATE);
      Truck truck = new Truck("BY2334", date, "BLUE VAN");
      //save truck
      truckDao.addTruck(truck);
      Assert.assertNotNull(truck);
    }

    @Test
    public void addTruckWithTruckCodeUnique() throws Exception {
        LOGGER.debug("test: addTruckWithTruckCodeUnique()");

        Date date = formatter.parse(DATE);
        Truck truck = new Truck("BY2000", date, "BLUE VAN");
        //save method
        truckDao.addTruck(truck);

        //assert not null and code is what i expect
        Assert.assertNotNull(truck);
        Assert.assertEquals(truck.getTruckCode(),"BY2000");
    }


    @Test
    public void updateAnExistingTruck() throws Exception {
        LOGGER.debug("test: updateAnExistingTruck()");

        Truck truck = truckDao.getTruckById(ID);
        Assert.assertEquals(truck.getTruckCode(), "BY8754");

        truck.setTruckCode("BY8888");
        truckDao.updateTruck(truck);

        //get same truck from db
        Truck truckWithNewCode = truckDao.getTruckById(ID);
        Assert.assertEquals(truckWithNewCode.getTruckCode(), "BY8888");
     }

    @Test
    public void deleteAnExistingTruck() throws Exception {
        LOGGER.debug("test: deleteAnExistingTruck()");

        Truck truck = truckDao.getTruckById(ID);
        Collection<Truck> collection = truckDao.getAllTrucks();

        int sizeBefore = collection.size();
        truckDao.deleteTruckById(truck.getTruckId());
        Collection<Truck> collectionAfterDelete = truckDao.getAllTrucks();
        int sizeAfter = collectionAfterDelete.size();
        Assert.assertTrue((sizeAfter + 1) == sizeBefore);
    }

}
