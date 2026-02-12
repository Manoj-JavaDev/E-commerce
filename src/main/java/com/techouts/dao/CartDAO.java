package com.techouts.dao;

import com.techouts.entity.Cart;
import com.techouts.entity.CartProducts;
import com.techouts.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class CartDAO {

    public void addItemToCart(Cart cart, Product product) {

        if (cart != null && product != null) {

            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();

                // Reattach cart and product (VERY IMPORTANT)
                Cart managedCart = session.get(Cart.class, cart.getId());
                Product managedProduct = session.get(Product.class, product.getId());

                CartProducts cartProducts = new CartProducts();
                cartProducts.setCart(managedCart);
                cartProducts.setProduct(managedProduct);


                session.persist(cartProducts);

                transaction.commit();
            }
        }
    }









}
