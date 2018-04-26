package com.epam.brest.course.utility.data;

import com.epam.brest.course.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *This dto is used to get truck
 *  by id including a net average per month of petrol used by truck.
 */
public class TruckWithAvgPetrolDto {

    /**
     * truck id.
     */
    private Integer truckId;
    /**
     * truck_code it is unique.
     */
    private String truckCode;
    /**
     * this can be purchase date or when truck was added to company.
     */
    private Date purchasedDate;
    /**
     * this is descriptions.
     */
    private String descriptions;
    /**
     *avg.
     */
    private Double avgPerMonth;

    /**
     * constructor.
     */
    public TruckWithAvgPetrolDto() {
    }

    /**
     * list of orders.
     */
    private  List<Order> orderList = new ArrayList<>();

    /**
     * @return list.
     */
    public final List<Order> getOrderList() {
        return orderList;
    }

    /**
     * @param list setter.
     */
    public final void setOrderList(final List<Order> list) {
        this.orderList = list;
    }

    /**
     * @return .
     */
    public final Integer getTruckId() {
        return truckId;
    }

    /**
     * @param id .
     */
    public final void setTruckId(final Integer id) {
        this.truckId = id;
    }

    /**
     * @return code.
     */
    public final String getTruckCode() {
        return truckCode;
    }

    /**
     * @param code setter.
     */
    public final void setTruckCode(final String code) {
        this.truckCode = code;
    }

    /**
     * @return dade.
     */
    public final Date getPurchasedDate() {
        return purchasedDate;
    }

    /**
     * @param purchasedDate1 setter .
     */
    public final void setPurchasedDate(final Date purchasedDate1) {
        this.purchasedDate = purchasedDate1;
    }

    /**
     * @return desc.
     */
    public final String getDescriptions() {
        return descriptions;
    }

    /**
     * @param descriptions1 setter .
     */
    public final void setDescriptions(final String descriptions1) {
        this.descriptions = descriptions1;
    }

    /**
     * @return avg.
     */
    public final Double getAvgPerMonth() {
        return avgPerMonth;
    }

    /**
     * @param avgPerMonth1 setter.
     */
    public final void setAvgPerMonth(final Double avgPerMonth1) {
        this.avgPerMonth = avgPerMonth1;
    }

}
