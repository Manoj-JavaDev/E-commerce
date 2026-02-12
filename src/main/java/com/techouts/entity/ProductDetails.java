package com.techouts.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;

import java.math.BigDecimal;

@Entity
public class ProductDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private String name;
    private int quantity;

    public void setOrder(Orders order) {
        this.order = order;
    }

    private BigDecimal price;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn (name="order_id")
    private Orders order;
    public Orders getOrder() {
        return order;
    }

}
