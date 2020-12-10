package com.projects.patryshop.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CAKES")
public class Cake {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME", nullable = false, length = 250)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Lob
    @Column(name = "INGREDIENTS", nullable = false)
    private String ingredients;

    @OneToMany(mappedBy = "cake", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Order> orders;

    public Cake() {
    }

    public Cake(Long id, String name, Double price, String ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
