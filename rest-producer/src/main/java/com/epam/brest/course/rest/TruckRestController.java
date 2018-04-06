package com.epam.brest.course.rest;

import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;
import com.epam.brest.course.service.TruckService;
import com.epam.brest.course.utility.data.TruckDto;
import com.epam.brest.course.utility.data.TruckWithAvgPetrolDto;
import com.epam.brest.course.utility.dozer.MappingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * truck controller.
 */
@RestController
public class TruckRestController {

    /**
     * Log class for debug.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Autowired mapping service bean dozer.
     */
    @Autowired
    private MappingService mappingService;

    /**
     * truck service.
     */
    @Autowired
    private TruckService truckService;

    /**
     * @param truckId param .
     * @return  new truck through dto.
     */
    @GetMapping(value = "/trucks/{truckId}")
    @ResponseStatus(HttpStatus.FOUND)
    public final TruckDto getTruckId(@PathVariable(value =  "truckId")
                                                    final Integer truckId) {

        LOGGER.debug("test: truckId({})", truckId);
        Truck truck = truckService.getTruckById(truckId);
       return mappingService.map(truck, TruckDto.class);
    }

    /**
     * @return collection through dto.
     */
    @GetMapping(value = "/trucks/trucksAvgPetrol")
    public final Collection<TruckWithAvgPetrolDto>
                                                truckWithAvgPetrolPerMonth() {

        LOGGER.debug("test: truckWithAvgPetrolPerMonth()");
        Collection<TruckWIthAvgPetrolPerMonth> avgPetrolPerMonths =

                           truckService.getAllTruckWithAvgPetrolPerMonth();

        return mappingService.map(avgPetrolPerMonths,
                                                TruckWithAvgPetrolDto.class);
    }

    /**
     * @return collection of just order list through dto.
     */
    @GetMapping(value = "/trucks/truckList")
    public final Collection<TruckDto>  getAllTrucks() {

        LOGGER.debug("test: getAllTrucks()");
        Collection<Truck> trucks = truckService.getAllTrucks();

        return mappingService.map(trucks, TruckDto.class);
    }


    /**
     * @param truckDto take dto and convert to model and save.
     *                 then convert to dto.
     * @return posted truck.
     */
    @PostMapping(value = "/trucks")
    @ResponseStatus(HttpStatus.CREATED)
    public final TruckDto addTruck(@Valid @RequestBody
                                                final TruckDto truckDto) {
        LOGGER.debug("addTruck({})", truckDto);


        Truck mappedTruck = mappingService.map(truckDto, Truck.class);
        Truck persisted =  truckService.addTruck(mappedTruck);
        return mappingService.map(persisted, TruckDto.class);
    }

    /**
     * @param truckDto take dto convert to model and update.
     */
    @PutMapping(value = "/trucks")
    @ResponseStatus(HttpStatus.OK)
    public final void update(@Valid @RequestBody final TruckDto truckDto) {
        LOGGER.debug("update({})", truckDto);

        Truck mappedTruck =  mappingService.map(truckDto, Truck.class);
        truckService.updateTruck(mappedTruck);
    }

    /**
     * @param truckId param.
     */
    @DeleteMapping(value = "/trucks/{truckId}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deleteTruck(@PathVariable(value = "truckId")
                                       final Integer truckId) {

        LOGGER.debug("deleteTruck({})", truckId);

        truckService.deleteTruckById(truckId);
    }

}
