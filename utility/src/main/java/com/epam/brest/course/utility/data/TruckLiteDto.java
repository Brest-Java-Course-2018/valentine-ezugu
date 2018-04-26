package com.epam.brest.course.utility.data;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.Valid;
import java.util.Date;

/**
 * truckLite for few details.
 */
public class TruckLiteDto {
    /**
     * id .
     */
    private Integer truckId;
    /**
     * truck_code it is unique.
     */
    @Valid
    private String truckCode;
    /**
     * this can be purchase date or when truck was added to company.
     */
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date purchasedDate;

    /**
     * constructor.
     */
    public TruckLiteDto() {
    }

    /**
     * this is descriptions.
     */
    private String descriptions;

    /**
     *
     * @return id.
     */
    public final Integer getTruckId() {
        return truckId;
    }

    /**
     *
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
     * @param date setter.
     */
    public final void setPurchasedDate(final Date date) {
        this.purchasedDate = date;
    }

    /**
     * @return desc.
     */
    public final String getDescriptions() {
        return descriptions;
    }

    /**
     * @param descriptions1 setter.
     */
    public final void setDescriptions(final String descriptions1) {
        this.descriptions = descriptions1;
    }

}
