package com.techouts.dao;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.techouts.entity.Order;
import com.techouts.entity.OrderItem;
import com.techouts.entity.Product;
import com.techouts.entity.User;

public class HibernateUtil {

	public static Session getSession() {
		
		Configuration cfg = new Configuration().addAnnotatedClass(User.class).addAnnotatedClass(Product.class).addAnnotatedClass(Order.class).addAnnotatedClass(OrderItem.class);
		cfg.configure("hibernate.cfg.xml");
		
		return cfg.buildSessionFactory().openSession();
	
	}
	
}
