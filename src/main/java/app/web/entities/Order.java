package app.web.entities;

import java.time.LocalDateTime;

public class Order
{
    private Integer orderId;
    private Integer userId;
    private Integer priceSuggested;
    private Integer priceActual;
    private LocalDateTime dateRequested;
    private LocalDateTime dateApproved;
    private LocalDateTime dateFinished;
    private String status;

    private Carport carport;


    //Getters and Setters

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPriceSuggested() {
        return this.priceSuggested;
    }

    public void setPriceSuggested(Integer priceSuggested) {
        this.priceSuggested = priceSuggested;
    }

    public Integer getPriceActual() {
        return this.priceActual;
    }

    public void setPriceActual(Integer priceActual) {
        this.priceActual = priceActual;
    }

    public LocalDateTime getDateRequested() {
        return this.dateRequested;
    }

    public void setDateRequested(LocalDateTime dateRequested) {
        this.dateRequested = dateRequested;
    }

    public LocalDateTime getDateApproved() {
        return this.dateApproved;
    }

    public void setDateApproved(LocalDateTime dateApproved) {
        this.dateApproved = dateApproved;
    }

    public LocalDateTime getDateFinished() {
        return this.dateFinished;
    }

    public void setDateFinished(LocalDateTime dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Carport getCarport() {
        return this.carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }
}
