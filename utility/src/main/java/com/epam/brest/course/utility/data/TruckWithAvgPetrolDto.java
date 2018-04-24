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
     *
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
    private List<Order> orderList = new ArrayList<>();

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    /**
     *
     * @return .
     */
    public Integer getTruckId() {
        return truckId;
    }

    /**
     *
     * @param truckId .
     */
    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    /**
     *
     * @return
     */
    public String getTruckCode() {
        return truckCode;
    }

    /**
     *
     * @param truckCode .
     */
    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    /**
     *
     * @return
     */
    public Date getPurchasedDate() {
        return purchasedDate;
    }

    /**
     *
     * @param purchasedDate .
     */
    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    /**
     *
     * @return
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     *
     * @param descriptions .
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    /**
     *
     * @return .
     */
    public Double getAvgPerMonth() {
        return avgPerMonth;
    }

    /**
     *
     * @param avgPerMonth .
     */
    public void setAvgPerMonth(Double avgPerMonth) {
        this.avgPerMonth = avgPerMonth;
    }

}
