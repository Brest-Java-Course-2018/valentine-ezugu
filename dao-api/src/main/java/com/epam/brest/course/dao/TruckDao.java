package com.epam.brest.course.dao;

import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * the interface of Truck dao.
 */
public interface TruckDao {
    /**
     *
     * @return collection.
     */
    Collection<Truck> getAllTrucks();

    /**
     *
     * @param truck to add.
     * @return truck.
     */
    Truck addTruck(Truck truck);

    /**
     *
     * @param id to get.
     * @return truck.
     */
    Truck getTruckById(Integer id);

    /**
     *
     * @param id to delete.
     */
    void deleteTruckById(Integer id);

    /**
     *
     * @param truck to update.
     */
    void updateTruck(Truck truck);

    /**
     *
     * @return collection.
     */
     List<TruckWIthAvgPetrolPerMonth> getTruckWithAvgPetrolPerMonth(String name);
}
