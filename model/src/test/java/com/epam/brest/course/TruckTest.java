package com.epam.brest.course;

import com.epam.brest.course.model.Truck;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TruckTest {


    private static final String TRUCK_CODE = "BY000";
    private static final Integer ID = 1;
    private static final String DESCRIPTIONS = "TEST TRUCK";
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    private static String DATE ="2004-33-01" ;


    @Test
    public void truckTest() throws ParseException {
        Date date  = format.parse(DATE);
        Truck truck = new Truck();
        truck.setTruckId(ID);
        truck.setTruckCode(TRUCK_CODE);
        truck.setPurchasedDate(date);
        truck.setDescriptions(DESCRIPTIONS);

        //asserts
        Assert.assertEquals(truck.getTruckId(),ID);
        Assert.assertEquals(truck.getTruckCode(), TRUCK_CODE);
        Assert.assertEquals(truck.getDescriptions(), DESCRIPTIONS);
        Assert.assertEquals(truck.getPurchasedDate(), date);

        String toTruckString  = "Truck{"
                + "truckId=" + ID
                + ", truckCode='" + TRUCK_CODE + '\''
                + ", purchasedDate=" + date
                + ", descriptions='" + DESCRIPTIONS + '\''
                + '}';

        Assert.assertEquals(toTruckString,truck.toString());

    }

}
