package com.epam.brest.course.dao;

import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String DATE = "2006-01-21";

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private static final int ID = 2;
    private static final int SIZE = 5;

    @Autowired
    private TruckDao truckDao;


    @Test(expected = EmptyResultDataAccessException.class)
    public void getTruckByIdWithNullValue() {
        //give null value we should get empty result .
       Truck truck =  truckDao.getTruckById(null);
        Assert.assertNotNull(truck);
    }

    @Test
    public void getTruckByIdWithValidInput() {
        Truck truck =  truckDao.getTruckById(ID);
        Assert.assertNotNull(truck);
    }

    @Test
    public void getAllTrucks() {
        Collection<Truck> trucks = truckDao.getAllTrucks();
        //assert we have list of trucks
        Assert.assertNotNull(trucks);
        Assert.assertTrue(trucks.size() > SIZE);
    }

    @Test
    public void getAllTrucksWithAvgPetrolPerMonth()  {
        Collection<TruckWIthAvgPetrolPerMonth> truckWIthAvgPetrolPerMonths =
                truckDao.getAllTruckWithAvgPetrolPerMonth();
        //get all trucks with calculated average petrol used
        Assert.assertNotNull(truckWIthAvgPetrolPerMonths);
        Assert.assertFalse(truckWIthAvgPetrolPerMonths.isEmpty());
        Assert.assertTrue(truckWIthAvgPetrolPerMonths.size() > SIZE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTruckWithTruckCodeAlreadyInUse() throws Exception {
        //give date and pass code which is already in use
      Date date = formatter.parse(DATE);
      Truck truck = new Truck("BY2334", date, "BLUE VAN");
      //save truck
      truckDao.addTruck(truck);
      Assert.assertNotNull(truck);
    }

    @Test
    public void addTruckWithTruckCodeUnique() throws Exception {
        Date date = formatter.parse(DATE);
        //new truck to save
        Truck truck = new Truck("BY2000", date, "BLUE VAN");
        //save method
        truckDao.addTruck(truck);

        //assert not null and code is what i expect
        Assert.assertNotNull(truck);
        Assert.assertEquals(truck.getTruckCode(),"BY2000");
    }


    @Test
    public void updateAnExistingTruck() throws Exception {
       // Date date = formatter.parse(DATE);
        Truck truck = truckDao.getTruckById(ID);
        Assert.assertEquals(truck.getTruckCode(), "BY8754");
       //set new code
        truck.setTruckCode("BY8888");
        //update
        truckDao.updateTruck(truck);

        //get same truck from db
        Truck truckWithNewCode = truckDao.getTruckById(ID);

        //assert if code changed then update works
        Assert.assertEquals(truckWithNewCode.getTruckCode(), "BY8888");
     }

    @Test
    public void deleteAnExistingTruck() throws Exception {
        // Date date = formatter.parse(DATE);
        Truck truck = truckDao.getTruckById(ID);
        Collection<Truck> collection = truckDao.getAllTrucks();

        //take size before delete
        int sizeBefore = collection.size();

        //perform delete
        truckDao.deleteTruckById(truck.getTruckId());
        Collection<Truck> collectionAfterDelete = truckDao.getAllTrucks();
        //take size after delete
        int sizeAfter = collectionAfterDelete.size();
        //check to confirm reduction after delete
        Assert.assertTrue((sizeAfter + 1) == sizeBefore);

    }

}
