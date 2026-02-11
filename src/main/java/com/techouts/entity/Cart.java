package com.techouts.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "cart")
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany (mappedBy = "cart")
    private List<CartProducts>  cartProducts;

    public void setCartProducts(List<CartProducts> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public List<CartProducts> getCartProducts() {
        return cartProducts;
    }

}
