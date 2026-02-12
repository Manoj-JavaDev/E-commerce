package com.techouts.dao;

import com.techouts.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class AddProducts {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();


            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();

            Product product = new Product("Puma Shoes","Trending Casual Out door sneakers for men", BigDecimal.valueOf(899),6);
            Product product1 = new Product("Adidas ","Base Breeze Running shoes for men",BigDecimal.valueOf(2999),2);
            Product product2 = new Product("Campus","First Men's Sports Shoes ",BigDecimal.valueOf(1499),3);

            session.persist(product2);
            session.persist(product);
            session.persist(product1);

            tx.commit();

            session.close();





    }

}
