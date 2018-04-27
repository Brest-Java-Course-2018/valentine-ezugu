package com.epam.brest.course.dto;

import com.epam.brest.course.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class TruckFullDetailDto {

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
     * list of orders.
     */
    private List<Order> orderList = new ArrayList<>();


    /**
     *
     * @param id truckId.
     * @param code code for truck.
     * @param date date.
     * @param description truck descriptions.
     */
    public TruckFullDetailDto(final Integer id, final String code,
                              final Date date,
                              final String description
                               ) {
        this.truckId = id;
        this.truckCode = code;
        this.purchasedDate = date;
        this.descriptions = description;

    }

    /**
     * constructor.
     */
    public TruckFullDetailDto() {
    }

    /**
     * @return id.
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
     * @return date.
     */
    public final Date getPurchasedDate() {
        return purchasedDate;
    }

    /**
     * @param date date.
     */
    public final void setPurchasedDate(final Date date) {
        this.purchasedDate = date;
    }

    /**
     * @return descriptions.
     */
    public final String getDescriptions() {
        return descriptions;
    }

    /**
     * @param description setter.
     */
    public final void setDescriptions(final String description) {
        this.descriptions = description;
    }

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
     *
     * @return string.
     */
    @Override
    public final String toString() {
        return "TruckDetail{"
                + "truckId=" + truckId
                + ", truckCode='" + truckCode + '\''
                + ", purchasedDate=" + purchasedDate
                + ", descriptions='" + descriptions + '\''
                + ", orderList=" + orderList
                + '}';
    }


}
