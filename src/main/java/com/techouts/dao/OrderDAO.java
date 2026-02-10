package com.techouts.dao;

import com.techouts.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO {
    
    public void save(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error saving order", e);
        }
    }
    
    public Order findById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Order.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Order findByOrderNumber(String orderNumber) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Order> query = session.createQuery(
                "FROM Order WHERE orderNumber = :orderNumber", Order.class);
            query.setParameter("orderNumber", orderNumber);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Order> findByUserId(Long userId) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Order> query = session.createQuery(
                "FROM Order WHERE user.id = :userId ORDER BY createdAt DESC", Order.class);
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Order> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Order ORDER BY createdAt DESC", Order.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void update(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error updating order", e);
        }
    }
}