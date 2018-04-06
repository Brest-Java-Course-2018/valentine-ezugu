package com.epam.brest.course.utility.data;

import java.util.Date;

/**
 * Dto class for truck.
 */
public class TruckDto {
    /**
     * id .
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
     * this is description.
     */
    private String description;

    /**
     * default.
     */
    public TruckDto() {
    }
    /**
     *
     * @return date.
     */
    public final Date getPurchasedDate() {
        return purchasedDate;
    }

    /**
     *
     * @param purchasedDate1 for setter.
     */
    public final void setPurchasedDate(final Date purchasedDate1) {
        this.purchasedDate = purchasedDate1;
    }

    /**
     *
     * @return truck id.
     */
    public final Integer getTruckId() {
        return truckId;
    }

    /**
     *
     * @param truckId1 for setter.
     */
    public final void setTruckId(final Integer truckId1) {
        this.truckId = truckId1;
    }

    /**
     *
     * @return truck_code.
     */
    public final String getTruckCode() {
        return truckCode;
    }

    /**
     *
     * @param truckCode1 for setter.
     */
    public final void setTruckCode(final String truckCode1) {
        this.truckCode = truckCode1;
    }


    /**
     *
     * @return description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     *
     * @param description1 for setter.
     */
    public final void setDescription(final String description1) {
        this.description = description1;
    }

}
