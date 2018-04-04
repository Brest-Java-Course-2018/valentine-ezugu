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
     *@throws DataAccessException exception.
     * @return collection.
     */
    Collection<Truck> getAllTrucks() throws DataAccessException;

    /**
     *@throws DataAccessException exception.
     * @param truck to add.
     * @return truck.
     */
    Truck addTruck(Truck truck) throws DataAccessException;

    /**
     *@throws DataAccessException exception.
     * @param id to get.
     * @return truck.
     */
    Truck getTruckById(Integer id) throws DataAccessException;

    /**
     *@throws DataAccessException exception.
     * @param id to delete.
     */
    void deleteTruckById(Integer id) throws DataAccessException;

    /**
     *@throws DataAccessException exception.
     * @param truck to update.
     */
    void updateTruck(Truck truck) throws DataAccessException;

    /**
     *@throws DataAccessException exception.
     * @return collection.
     */
    Collection<TruckWIthAvgPetrolPerMonth> getAllTruckWithAvgPetrolPerMonth()
                                            throws DataAccessException;

}
