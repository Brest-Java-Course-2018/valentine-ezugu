package com.epam.brest.course.dto;

import java.util.Date;
/**
 *This dto is used to get truck
 *  by id including a net average per month of petrol used by truck.
 */
public class TruckWithAvgDto {

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
     *for avg per month.
     */
    private Double avgPerMonth;

    /**
     *
     * @return id.
     */
    public final Integer getTruckId() {
        return truckId;
    }

    /**
     * @param id setter.
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
     * @param code code.
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
     *
     * @param date date.
     */
    public final void setPurchasedDate(final Date date) {
        this.purchasedDate = date;
    }

    /**
     *
     * @return desc.
     */
    public final String getDescriptions() {
        return descriptions;
    }

    /**
     *
     * @param descriptions1 desc.
     */
    public final void setDescriptions(final String descriptions1) {
        this.descriptions = descriptions1;
    }

    /**
     *
     * @return avg.
     */
    public final Double getAvgPerMonth() {
        return avgPerMonth;
    }

    /**
     *
     * @param avg setter.
     */
    public final void setAvgPerMonth(final Double avg) {
        this.avgPerMonth = avg;
    }

}
