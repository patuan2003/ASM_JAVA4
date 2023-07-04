package com.example.tuanpaph26902_assignment.repository;

import com.example.tuanpaph26902_assignment.utility.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class GenericRepo<T, U> {

    private final Class<T> classzz;

    public GenericRepo(Class<T> classzz) {
        this.classzz = classzz;
    }

    public List<T> getAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            String hql = "FROM " + classzz.getName();
            Query query = session.createQuery(hql);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getOne(U u) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            String hql = "FROM " + classzz.getName() + " WHERE id =:u";
            Query query = session.createQuery(hql, classzz);
            query.setParameter("u", u);
            return (T) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getByMa(String ma) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            String hql = "FROM " + classzz.getName() + " WHERE ma =:ma";
            Query query = session.createQuery(hql, classzz);
            query.setParameter("ma", ma);
            return (T) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean save(T t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(T t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(T t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
