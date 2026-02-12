package com.techouts.dao;

import com.techouts.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrdersDAO {

        CartDAO cartDAO = new CartDAO();
        public void addOrderDetails(Orders order,Cart cart) {
            List<CartProducts> orderCartProducts =cartDAO.findAllCartProducts(cart);
            ProductDAO productDAO = new ProductDAO();
            if(orderCartProducts!=null) {

                try (Session session = HibernateUtil.getSession())
                {
                    Transaction transaction = session.beginTransaction();
                    order.setUser(cart.getUser());
                    User user =  cart.getUser();
                    order.setName(user.getName());
                    order.setEmail(user.getEmail());
                    order.setPhoneNumber(user.getPhone());

                    List<ProductDetails> productDetailsList = null;

                    for(CartProducts cartProduct:orderCartProducts)
                    {
                        ProductDetails pd = new ProductDetails();
                        pd.setName(cartProduct.getProduct().getName());
                        pd.setPrice(cartProduct.getProduct().getPrice());
                        pd.setQuantity(cartProduct.getQuantity());
                        pd.setTotalPrice((long) cartProduct.getProduct().getPrice() *cartProduct.getQuantity());
                        session.persist(pd);
                        productDetailsList.add(pd);
                    }

                    order.setProductDetailsList(productDetailsList);

                    session.persist(order);
                    transaction.commit();

                }
            }

        }

}
