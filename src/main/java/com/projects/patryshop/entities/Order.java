package com.projects.patryshop.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Column(name = "CREATION_TIMESTAMP", nullable = false)
    @CreatedDate
    private ZonedDateTime creationTimestamp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "CAKE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_CAKE"))
    private Cake cake;

    public Order() {
    }

    public Order(Long id, Integer amount, ZonedDateTime creationTimestamp, Cake cake) {
        this.id = id;
        this.amount = amount;
        this.creationTimestamp = creationTimestamp;
        this.cake = cake;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }
}
