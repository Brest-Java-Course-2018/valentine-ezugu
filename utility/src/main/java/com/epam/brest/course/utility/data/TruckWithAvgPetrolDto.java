package com.epam.brest.course.utility.data;

import java.util.Objects;

/**
 * This class is a replica of data object in model
 * will be used for data transfer to ui.
 */
public class TruckWithAvgPetrolDto {
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
    public TruckWithAvgPetrolDto() {
    }

    /**
     *
     * @param code .
     * @param monthOfOrder .
     * @param qty .
     * @param year1 .
     */
    public TruckWithAvgPetrolDto(final String code, final Integer year1,
                                      final String monthOfOrder,
                                      final Double qty) {
        this.truckCode = code;
        this.year = year1;
        this.month = monthOfOrder;
        this.avgPetrolQty = qty;
    }

    /**
     *
     * @return truckCode.
     */
    public final String getTruckCode() {
        return truckCode;
    }

    /**
     *
     * @param truckCode1 code.
     */
    public final void setTruckCode(final String truckCode1) {
        this.truckCode = truckCode1;
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
     * @param year1 for setter.
     */
    public final void setYear(final Integer year1) {
        this.year = year1;
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
     * @param month1 for setter,
     */
    public final void setMonth(final String month1) {
        this.month = month1;
    }

    /**
     *
     * @return petrl_qty.
     */
    public final Double getAvgPetrolQty() {
        return avgPetrolQty;
    }

    /**
     *
     * @param avgPetrolQty1 for setter.
     */
    public final void setAvgPetrolQty(final Double avgPetrolQty1) {
        this.avgPetrolQty = avgPetrolQty1;
    }

    /**
     *
     * @param o object.
     * @return boolean.
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TruckWithAvgPetrolDto)) {
            return false;
        }
        TruckWithAvgPetrolDto that = (TruckWithAvgPetrolDto) o;
        return Objects.equals(getTruckCode(), that.getTruckCode())
                && Objects.equals(getYear(), that.getYear())
                && Objects.equals(getMonth(), that.getMonth())
                && Objects.equals(getAvgPetrolQty(), that.getAvgPetrolQty());
    }

    /**
     *
     * @return value.
     */
    @Override
    public final int hashCode() {

        return Objects.hash(getTruckCode(),
                getYear(), getMonth(),
                getAvgPetrolQty());
    }

}

