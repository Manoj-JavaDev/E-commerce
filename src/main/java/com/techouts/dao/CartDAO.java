package com.techouts.dao;

import com.techouts.entity.Cart;
import com.techouts.entity.CartProducts;
import com.techouts.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class CartDAO {

    public void addItemToCart(Cart cart, Product product) {

        if (cart != null && product != null) {

            String query = "from CartProducts cp where cp.product.id = :productId AND cp.cart.id = :cartId";

            try (Session session = HibernateUtil.getSession()) {

                CartProducts ifPresent = session.createQuery(query, CartProducts.class)
                        .setParameter("productId", product.getId())
                        .setParameter("cartId", cart.getId()).uniqueResult();
                Transaction transaction = session.beginTransaction();

                if (ifPresent != null) {
                    ifPresent.setQuantity(ifPresent.getQuantity() + 1);
                } else {
                    // Reattach cart and product (VERY IMPORTANT)
                    Cart managedCart = session.get(Cart.class, cart.getId());
                    Product managedProduct = session.get(Product.class, product.getId());

                    CartProducts cartProducts = new CartProducts();
                    cartProducts.setCart(managedCart);
                    cartProducts.setProduct(managedProduct);
                    cartProducts.setQuantity(1);
                    session.persist(cartProducts);

                }
                transaction.commit();
            }
        }


    }


    public List<CartProducts> findAllCartProducts(Cart cart) {

        String query = "from CartProducts cp where cp.cart.id = :cartId";
        try (Session session = HibernateUtil.getSession()) {

            List<CartProducts> cartProducts = session.createQuery(query, CartProducts.class)
                    .setParameter("cartId",cart.getId()).list();
            return !cartProducts.isEmpty() ? cartProducts : null;

        }
    }

    public CartProducts findCartProductByProductId(Cart cart,long productId) {
        String query = "from CartProducts cp where cp.product.id = :productId AND cp.cart.id = :cartId";

        try (Session session = HibernateUtil.getSession()) {
            return (CartProducts) session.createQuery(query).setParameter("productId", productId)
                    .setParameter("cartId",cart.getId())
            .uniqueResult();
        }

    }

    public void deleteCartAfterPlacingOrder(Cart cart) {

        if (cart != null) {

            String hql = "delete from CartProducts cp where cp.cart.id = :cartId";

            try (Session session = HibernateUtil.getSession()) {

                Transaction transaction = session.beginTransaction();

                session.createQuery(hql)
                        .setParameter("cartId", cart.getId())
                        .executeUpdate();

                transaction.commit();
            }
        }
    }


    public void deleteCartProductByProductId(Cart cart, Product product) {
        String query = "from CartProducts cp where cp.product.id = :productId AND cp.cart.id = :cartId";
        try (Session session = HibernateUtil.getSession()) {
            CartProducts ifPresent = session.createQuery(query, CartProducts.class)
                    .setParameter("productId", product.getId())
                    .setParameter("cartId", cart.getId()).uniqueResult();
            Transaction transaction = session.beginTransaction();
            if (ifPresent != null) {
                session.remove(ifPresent);
                transaction.commit();
            }
        }
    }

    public int removeItemFromCart(Cart cart, Product product) {

        if (cart != null && product != null) {

            String query = "from CartProducts cp where cp.product.id = :productId AND cp.cart.id = :cartId";
            try (Session session = HibernateUtil.getSession()) {

                CartProducts ifPresent = session.createQuery(query, CartProducts.class)
                        .setParameter("productId", product.getId())
                        .setParameter("cartId", cart.getId()).uniqueResult();
                Transaction transaction = session.beginTransaction();
                if (ifPresent != null) {
                    if (ifPresent.getQuantity() == 1){
                        session.remove(ifPresent);
                        transaction.commit();
                        return 1;
                    }
                    ifPresent.setQuantity(ifPresent.getQuantity() - 1);
                    transaction.commit();
                    return 0;
                }
                else {
                    return -1;
                }

            }

        }
        else {
            return -1;
        }


    }

}

