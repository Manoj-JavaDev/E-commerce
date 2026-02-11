package com.techouts.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class CartProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn (name="Cart_id")
    private Cart cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @OneToOne
    private Product product;
}
