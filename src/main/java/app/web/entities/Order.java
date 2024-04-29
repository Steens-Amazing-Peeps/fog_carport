package app.web.entities;

import java.util.Date;

public class Order  {

    private Integer orderId;  // t
    private Integer userId;  // t
    private Date dateOrdered;  // t
    private Date dateReady;  // t
    private Integer statusId;  // t

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId( Integer orderId ) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }

    public Date getDateOrdered() {
        return this.dateOrdered;
    }

    public void setDateOrdered( Date dateOrdered ) {
        this.dateOrdered = dateOrdered;
    }

    public Date getDateReady() {
        return this.dateReady;
    }

    public void setDateReady( Date dateReady ) {
        this.dateReady = dateReady;
    }

    public Integer getStatusId() {
        return this.statusId;
    }

    public void setStatusId( Integer statusId ) {
        this.statusId = statusId;
    }
    
    @Override
    public String toString()
    {
        return "Order{" +
               "orderId=" + this.orderId +
               ", userId=" + this.userId +
               ", dateOrdered=" + this.dateOrdered +
               ", dateReady=" + this.dateReady +
               ", statusId=" + this.statusId +
               '}';
    }
    
}