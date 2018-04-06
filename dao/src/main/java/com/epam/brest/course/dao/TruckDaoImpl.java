package com.epam.brest.course.dao;

import com.epam.brest.course.dto.TruckWIthAvgPetrolPerMonth;
import com.epam.brest.course.model.Truck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

/**
 *dao implementation for truck.
 */
public class TruckDaoImpl implements TruckDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * sql query for insert.
     */
    @Value("${truck.add}")
    private String addTruckSql;
    /**
     * sql query for delete.
     */
    @Value("${truck.deleteById}")
    private String deleteByIdSql;
    /**
     * sql query for update.
     */
    @Value("${truck.update}")
    private String updateSql;

    /**
     * sql query for select where @id.
     */
    @Value("${truck.selectById}")
    private String selectById;
    /**
     * sql query for select all.
     */
    @Value("${truck.selectAllWithAvg}")
    private String selectAllTrucksWithAvg;
    /**
     *to check if truck code i unique.
     */
    @Value("${truck.checkIfUnique}")
    private String checkIfTruckCodeUnique;

    /**
     * basic select all.
     */
    @Value("${truck.selectAll}")
    private String selectAll;


    /**
     *named param jdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * @param namedParameterJdbcTemplate1 inject.
     */
    public final void setNamedParameterJdbcTemplate(
         final NamedParameterJdbcTemplate namedParameterJdbcTemplate1) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate1;
    }

    /**
     * @return trucks.
     */
    @Override
    public final List<Truck> getAllTrucks() {
        LOGGER.debug("getAllTrucks()");
        List<Truck> trucks =
                namedParameterJdbcTemplate.getJdbcOperations()
                .query(selectAll,
                        BeanPropertyRowMapper.newInstance(Truck.class));
        return trucks;
    }

    /**
     * @param truck .
     * @return new truck.
     */
    @Override
    public final Truck addTruck(final Truck truck) {

        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("truckCode",
                        truck.getTruckCode());
        Integer result =
                namedParameterJdbcTemplate.queryForObject(
                        checkIfTruckCodeUnique, namedParameters, Integer.class);
        LOGGER.debug("result({})", result);

        if (result == 0) {
            namedParameters = new MapSqlParameterSource();

            namedParameters.addValue("truckCode",
                    truck.getTruckCode());

            namedParameters.addValue("purchasedDate",
                    truck.getPurchasedDate());

            namedParameters.addValue("description",
                    truck.getDescription());

            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            namedParameterJdbcTemplate.update(
                    addTruckSql, namedParameters, generatedKeyHolder);

            truck.setTruckId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException("such Truck already exits ");
        }
        LOGGER.debug("addTruck({})", truck);
        return truck;
    }

    /**
     * @param id .
     * @return truck.
     */
    @Override
    public final Truck getTruckById(final Integer id) {
        LOGGER.debug("getTruckById({})", id);
        SqlParameterSource parameterSource =
                new MapSqlParameterSource("truckId", id);

        Truck truck = namedParameterJdbcTemplate
                .queryForObject(
                        selectById, parameterSource,
                        BeanPropertyRowMapper.newInstance(Truck.class));
        return truck;
    }

    /**
     * @param id delete by id.
     */
    @Override
    public final void deleteTruckById(final Integer id) {
        LOGGER.debug("deleteTruckById({})", id);

        namedParameterJdbcTemplate
                .getJdbcOperations()
                .update(deleteByIdSql, id);
    }

    /**
     * @param truck for update.
     */
    @Override
    public final void updateTruck(final Truck truck) {
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource("truckId",
                        truck.getTruckId())
                        .addValue("truckCode", truck.getTruckCode())
                        .addValue("purchasedDate", truck.getPurchasedDate())
                        .addValue("description", truck.getDescription());

        namedParameterJdbcTemplate.update(updateSql, namedParameterSource);
    }

    /**
     * @return
     */
    @Override
    public final List<TruckWIthAvgPetrolPerMonth>
                        getAllTruckWithAvgPetrolPerMonth() {
        List<TruckWIthAvgPetrolPerMonth> trucks =
                namedParameterJdbcTemplate.getJdbcOperations()

                .query(selectAllTrucksWithAvg, BeanPropertyRowMapper
                        .newInstance(TruckWIthAvgPetrolPerMonth.class));
        return trucks;
    }

}
