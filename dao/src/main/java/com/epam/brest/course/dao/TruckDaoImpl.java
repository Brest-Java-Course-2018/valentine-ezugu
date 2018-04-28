package com.epam.brest.course.dao;

import com.epam.brest.course.dto.TruckFullDetailDto;
import com.epam.brest.course.dto.TruckWithAvgDto;
import com.epam.brest.course.model.Order;
import com.epam.brest.course.model.Truck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * dao implementation for truck.
 */
public class TruckDaoImpl implements TruckDao {
    /**
     * desc.
     */
    public static final String DESCRIPTIONS = "descriptions";
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * id.
     */
    private static final String TRUCK_ID = "truckId";
    /**
     * code.
     */
    private static final String TRUCK_CODE = "truckCode";

    /**
     * desc.
     */
    private static final String DESCRIPTIONS1 = "descriptions";
    /**
     * date of purchase.
     */
    private static final String PURCHASED_DATE = "purchasedDate";
    /**
     * id.
     */
    private static final String ORDER_ID = "orderId";
    /**
     * qty petrol.
     */
    private static final String PETROL_QTY = "petrolQty";
    /**
     * date.
     */
    private static final String ORDER_DATE = "orderDate";
    /**
     * truckId.
     */
    private static final String T_TRUCK_ID = "t.truckId";
    /**
     * sql query for insert.
     */
    @Value("${truck.add}")
    private String addTruckSql;
    /**
     * sql query for delete.
     */
    @Value("${truck.deleteById}")
    private String deleteTruckByIdSql;
    /**
     * sql query for update.
     */
    @Value("${truck.update}")
    private String updateTruckSql;

    /**
     * basic select all.
     */
    @Value("${truck.selectAll}")
    private String getAllTrucksSql;

    /**
     * This is sql full truck detail.
     */
    @Value("${truck.selectFullTruckDetail}")
    private String fullTruckDetailsql;

    /**
     * This is sql get by truckCode.
     */
    @Value("${truck.selectTruckDetailById}")
    private String getTruckDetailByIdsql;



    /**
     * named param jdbcTemplate.
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
                        .query(getAllTrucksSql,
                                BeanPropertyRowMapper.newInstance(Truck.class));
        return trucks;
    }

    /**
     * @param truck .
     * @return new truck.
     */
    @Override
    public final Truck addTruck(final Truck truck) {
        LOGGER.debug("addTruck({})", truck);

        MapSqlParameterSource
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(TRUCK_CODE,
                    truck.getTruckCode());
            namedParameters.addValue(PURCHASED_DATE,
                    truck.getPurchasedDate());
            namedParameters.addValue(DESCRIPTIONS,
                    truck.getDescriptions());
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(
                    addTruckSql, namedParameters, generatedKeyHolder);
            truck.setTruckId(generatedKeyHolder.getKey().intValue());


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
                .update(deleteTruckByIdSql, id);
    }

    /**
     * @param truck for update.
     */
    @Override
    public final void updateTruck(final Truck truck) {
        SqlParameterSource namedParameterSource =
                new MapSqlParameterSource(TRUCK_ID,
                        truck.getTruckId())
                        .addValue(TRUCK_CODE, truck.getTruckCode())
                        .addValue(PURCHASED_DATE, truck.getPurchasedDate())
                        .addValue(DESCRIPTIONS, truck.getDescriptions());
        namedParameterJdbcTemplate.update(updateTruckSql, namedParameterSource);
    }

    //Both get BY id can be used depend what is needed,
    // simple getById or getTruckFullDetailById.
    /**
     * @param id .
     * @return truck.
     */
    @Override
    public final TruckWithAvgDto getTruckById(final Integer id) {
        LOGGER.debug("getTruckById({})", id);
        SqlParameterSource parameterSource =
                new MapSqlParameterSource(T_TRUCK_ID, id);
        TruckWithAvgDto truckWithAvgDto = namedParameterJdbcTemplate
                .queryForObject(getTruckDetailByIdsql,
                        parameterSource,
                     BeanPropertyRowMapper.newInstance(TruckWithAvgDto.class));
        return truckWithAvgDto;
    }

    /**
     * @param id .
     * @return .This is an example of get BY
     * id with full-truck detail- mapping of one to many.
     */
    @Override
    public final TruckFullDetailDto getTruckFullDetailById(final Integer id) {
     SqlParameterSource namedParameterSource =
                new MapSqlParameterSource(T_TRUCK_ID, id);
       TruckDetailMapper mapper = new TruckDetailMapper();
       namedParameterJdbcTemplate.query(fullTruckDetailsql,
                                                namedParameterSource, mapper);
       TruckFullDetailDto truck = mapper.getDetail();
       return truck;
     }

    /**
     * This is mapper maps one to many relationship.
     */
    public class TruckDetailMapper implements RowMapper<TruckFullDetailDto> {
        /**
         * dto.
         */
    private TruckFullDetailDto detail;

        /**
         *
         * @param rs .
         * @param rowNum .
         * @return mapped class.
         * @throws SQLException ex.
         */
     public final TruckFullDetailDto mapRow(
                                    final ResultSet rs, final int rowNum)
            throws SQLException {

        if (detail == null) {

            this.detail = new TruckFullDetailDto();

            detail.setTruckId(rs.getInt(TRUCK_ID));
            detail.setTruckCode(rs.getString(TRUCK_CODE));
            detail.setDescriptions(rs.getString(DESCRIPTIONS1));
            detail.setPurchasedDate(rs.getDate(PURCHASED_DATE));
        }

         Order order = new Order();
         order.setOrderId(rs.getInt(ORDER_ID));
         order.setPetrolQty(rs.getDouble(PETROL_QTY));
         order.setOrderDate(rs.getDate(ORDER_DATE));
         order.setTruckId(rs.getInt(TRUCK_ID));

        this.detail.getOrderList().add(order);
        return null;

     }
        /**
         * @return detail.
         */
     private TruckFullDetailDto getDetail() {
        return detail;
     }

  }

}




