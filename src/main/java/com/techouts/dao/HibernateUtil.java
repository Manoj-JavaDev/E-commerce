package com.techouts.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.techouts.entity.Product;
import com.techouts.entity.User;

public class HibernateUtil {

	public static Session getSession() {
		
		Configuration cfg = new Configuration().addAnnotatedClass(User.class).addAnnotatedClass(Product.class);
		cfg.configure("hibernate.cfg.xml");
		
		return cfg.buildSessionFactory().openSession();
	
	}
	
}
