package com.epam.brest.course.dto;

/**
 * this is a dto class to calculate avg per month of individual trucks.
 */
public class TruckWIthAvgPetrolPerMonth {
    /**
     * truckCode.
     */
    private String truckCode;

    /**
     *year.
     */
    private Integer year;

    /**
     * month.
     */
    private String month;
    /**
     * avgPetrolQty.
      */
    private Double avgPetrolQty;

    /**
     * constructor.
     */
    public TruckWIthAvgPetrolPerMonth() {
    }

    /**
     *
     * @param code .
     * @param monthOfOrder .
     * @param qty .
     * @param year1 .
     */
    public TruckWIthAvgPetrolPerMonth(final String code, final Integer year1,
                                      final String monthOfOrder,
                                      final Double qty) {
        this.truckCode = code;
        this.year = year1;
        this.month = monthOfOrder;
        this.avgPetrolQty = qty;
    }


    /**
     * @return truck code.
     */
    public final String getTruckCode() {
        return truckCode;
    }

    /**
     * @param truckCode1 for setter.
     */
    public final void setTruckCode(final String truckCode1) {
        this.truckCode = truckCode1;
    }

    /**
     *
     * @return month.
     */
    public final String getMonth() {
        return month;
    }

    /**
     *
     * @param monthForAvg for setter.
     */
    public final void setMonth(final String monthForAvg) {
        this.month = monthForAvg;
    }

    /**
     * @return avg.
     */
    public final Double getAvgPetrolQty() {
        return avgPetrolQty;
    }

    /**
     * @param qty .
     */
    public final void setAvgPetrolQty(final Double qty) {
        this.avgPetrolQty = qty;
    }

    /**
     *
     * @return year.
     */
    public final Integer getYear() {
        return year;
    }

    /**
     *
     * @param year1 .
     */
    public final void setYear(final Integer year1) {
        this.year = year1;
    }

}
