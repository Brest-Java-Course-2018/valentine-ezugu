package com.epam.brest.course.dto;

import java.util.Date;

public class TruckForSaveAndUpdate {
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
     * default.
     */
    public TruckForSaveAndUpdate() {
    }

    /**
     *
     * @return truckId.
     */
    public Integer getTruckId() {
        return truckId;
    }

    /**
     *
     * @param truckId setter.
     */
    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    /**
     *
     * @return truckCode.
     */
    public String getTruckCode() {
        return truckCode;
    }

    /**
     *
     * @param truckCode setter.
     */
    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    /**
     *
     * @return date.
     */
    public Date getPurchasedDate() {
        return purchasedDate;
    }

    /**
     *
     * @param purchasedDate setter.
     */
    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    /**
     *
     * @return desc.
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     *
     * @param descriptions setter.
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    /**
     *
     * @return debuggable class.
     */
    @Override
    public String toString() {
        return "TruckForSaveAndUpdate{"
                + "truckId=" + truckId
                + ", truckCode='" + truckCode + '\''
                + ", purchasedDate=" + purchasedDate
                + ", descriptions='" + descriptions + '\''
                + '}';
    }
}
