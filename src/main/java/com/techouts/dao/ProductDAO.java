package com.techouts.dao;

import com.techouts.entity.Product;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {
    
    public void save(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error saving product", e);
        }
    }
    
    public Product findById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Product> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Product WHERE active = true ORDER BY createdAt DESC", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Product> findByCategory(String category) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Product> query = session.createQuery(
                "FROM Product WHERE category = :category AND active = true ORDER BY createdAt DESC", Product.class);
            query.setParameter("category", category);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Product> searchByName(String keyword) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Product> query = session.createQuery(
                "FROM Product WHERE LOWER(name) LIKE :keyword AND active = true ORDER BY createdAt DESC", Product.class);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<String> getAllCategories() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery(
                "SELECT DISTINCT category FROM Product WHERE category IS NOT NULL AND active = true", String.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void update(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error updating product", e);
        }
    }
    
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setActive(false); // Soft delete
                session.merge(product);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error deleting product", e);
        }
    }
}