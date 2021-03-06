package com.epam.brest.course.dto;


import java.util.Date;

/**
 * this is a dto class for order.
 */
public class OrderWithTruckCodeDto {
    /**
     * order_id.
     */
    private Integer orderId;
    /**
     * order date.
     */
    private Date orderDate;
    /**
     * petrol Qty.
     */
    private Double petrolQty;
    /**
     * unique truckCode.
     */
    private String truckCode;

    /**
     * no args .
     */
    public OrderWithTruckCodeDto() {
    }

     /**
     * @param date orderDate.
     * @param qty petrol qty can be litres.
     * @param code  truckCode.
     */
    public OrderWithTruckCodeDto(final Date date,
                                 final Double qty, final String code) {
        this.orderDate = date;
        this.petrolQty = qty;
        this.truckCode = code;
    }

    /**
     * @return id.
     */
    public final Integer getOrderId() {
        return orderId;
    }

    /**
     * @param id setter.
     */
    public final void setOrderId(final Integer id) {
        this.orderId = id;
    }

    /**
     * @return date.
     */
    public final Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param date setter.
     */
    public final void setOrderDate(final Date date) {
        this.orderDate = date;
    }

    /**
     *
     * @return petrolQty.
     */
    public final Double getPetrolQty() {
        return petrolQty;
    }

    /**
     *
     * @param qty for setter.
     */
    public final void setPetrolQty(final Double qty) {
        this.petrolQty = qty;
    }

    /**
     * @return truckCode.
     */
    public final String getTruckCode() {
        return truckCode;
    }

    /**
     * @param code for setter.
     */
    public final void setTruckCode(final String code) {
        this.truckCode = code;
    }

    /**
     *
     * @return for debug.
     */
    @Override
    public final String toString() {
        return "OrderWithTruckCodeDto{"
                + "orderId=" + orderId
                + ", orderDate=" + orderDate
                + ", petrolQty=" + petrolQty
                + ", truckCode='" + truckCode + '\''
                + '}';
    }
}


