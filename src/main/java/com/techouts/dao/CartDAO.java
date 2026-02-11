package com.techouts.dao;

import com.techouts.entity.Cart;
import com.techouts.entity.CartProducts;
import com.techouts.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class CartDAO {

    public void addItemToCart(Cart cart, Product product) {

        if(cart != null && product != null) {

            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();
                CartProducts cartProducts = new CartProducts();
                cartProducts.setCart(cart);
                cartProducts.setProduct(product);
                cartProducts.setUser(cart.getUser());
                session.persist(cartProducts);
                cart.getCartProducts().add(cartProducts);
                session.persist(cart);
                transaction.commit();
            }

        }

    }

    public void removeItemFromCart(Cart cart, Product product) {
        if(cart != null && product != null) {



        }
    }






}
