package com.projects.patryshop.dto;

import java.time.ZonedDateTime;

public class OrderDTO {

    private Long orderId;
    private Long cakeId;
    private String cakeName;
    private Integer amount;
    private ZonedDateTime creationTimestamp;
    private Double price;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCakeId() {
        return cakeId;
    }

    public void setCakeId(Long cakeId) {
        this.cakeId = cakeId;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ZonedDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(ZonedDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
