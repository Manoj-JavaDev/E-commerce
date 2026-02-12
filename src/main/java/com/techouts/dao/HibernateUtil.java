package com.techouts.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.techouts.entity.Product;
import com.techouts.entity.User;

public class HibernateUtil {

	public static SessionFactory sf = new Configuration().addAnnotatedClass(User.class).addAnnotatedClass(Product.class)
		.configure("hibernate.cfg.xml").buildSessionFactory();

	public static Session getSession() {

		return sf.openSession();
	
	}
	
}
