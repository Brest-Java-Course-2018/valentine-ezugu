package com.epam.brest.course.client;

import com.epam.brest.course.dto.TruckFullDetailDto;
import com.epam.brest.course.model.Truck;
import com.epam.brest.course.service.TruckService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

/**
 * client rest consumer service.
 */
@Service
public class TruckRestConsumer implements TruckService {

    /**
     * logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * url.
     */
    @Value("${truck.ClientUrl}")
    private String url;
    /**
     * rest template.
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @return list of trucks.
     * @throws DataAccessException exception.
     */
    @SuppressWarnings("unchecked")
    @Override
    public final Collection<Truck> getAllTrucks() throws DataAccessException {
        LOGGER.debug("getAllTrucks()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, List.class);
        List<Truck> trucks = (List<Truck>) responseEntity.getBody();
        responseEntity.getBody();
        return trucks;
    }

    /**
     * @param truck to add.
     * @return new truck.
     * @throws DataAccessException exception.
     */
    @Override
    public final Truck addTruck(final Truck truck) throws DataAccessException {
        LOGGER.debug("addTruck({})", truck);
        ResponseEntity<Truck> responseEntity =
                restTemplate.postForEntity(url, truck, Truck.class);
        Truck result = responseEntity.getBody();
        return result;
    }

    /**
     * @param id to get.
     * @return truck.
     * @throws DataAccessException exception.
     */
    @Override
    public final TruckFullDetailDto getTruckById(final Integer id)
            throws DataAccessException {
        LOGGER.debug("getTruckById({})", id);

        ResponseEntity<TruckFullDetailDto> responseEntity =
                restTemplate.getForEntity(url + "/" + id,
                        TruckFullDetailDto.class);

        TruckFullDetailDto result = responseEntity.getBody();
        return result;
    }

    /**
     * @param id to delete.
     * @throws DataAccessException exception.
     */
    @Override
    public final void deleteTruckById(final Integer id)
            throws DataAccessException {
        LOGGER.debug("deleteTruckById({})", id);

        restTemplate.delete(url + "/" + id);
    }

    /**
     * @param truck to update.
     * @throws DataAccessException exception.
     */
    @Override
    public final void updateTruck(final Truck truck)
            throws DataAccessException {
        LOGGER.debug("updateTruck({})", truck);

        restTemplate.put(url, truck);

    }

}
