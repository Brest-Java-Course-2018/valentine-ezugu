package com.epam.brest.course.service;

import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Truck service abstracted methods.
 */
public interface TruckService {
    /**
     *
     * @return collection.
     */
    Collection<Truck> getAllTrucks() throws DataAccessException;

    /**
     *
     * @param truck to add.
     * @return truck.
     */
    Truck addTruck(Truck truck) throws DataAccessException;

    /**
     *
     * @param id to get.
     * @return truck.
     */
    Truck getTruckById(Integer id) throws DataAccessException;

    /**
     *
     * @param id to delete.
     */
    void deleteTruckById(Integer id) throws DataAccessException;

    /**
     *
     * @param truck to update.
     */
    void updateTruck(Truck truck) throws DataAccessException;

    /**
     *
     * @return collection.
     */
    Collection<TruckWIthAvgPetrolPerMonth> getAllTruckWithAvgPetrolPerMonth()
                                            throws DataAccessException;

}
