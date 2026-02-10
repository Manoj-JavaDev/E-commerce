package com.techouts.dao;

import com.techouts.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class AddProducts {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();

        try(Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            Product product = new Product("IPhone 16 Pro Max","12G BRAM - 128 ROM", BigDecimal.valueOf(8090.90),6);
            Product product1 = new Product("Redmi","4GB RAM - 64GB ROM",BigDecimal.valueOf(90000),2);
            Product product2 = new Product("POCO","6GB RAM - 128GB ROM",BigDecimal.valueOf(1000),3);

            session.persist(product2);
            session.persist(product);
            session.persist(product1);

            tx.commit();
        }

    }

}
