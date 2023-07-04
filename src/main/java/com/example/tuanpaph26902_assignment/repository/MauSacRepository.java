package com.example.tuanpaph26902_assignment.repository;

import com.example.tuanpaph26902_assignment.entity.MauSac;
import com.example.tuanpaph26902_assignment.utility.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class MauSacRepository {

    public List<MauSac> getAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM MauSac", MauSac.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MauSac getOne(UUID id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM MauSac where id =:id", MauSac.class);
            query.setParameter("id", id);
            return (MauSac) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MauSac getById(String ma) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM MauSac where ma =:ma", MauSac.class);
            query.setParameter("ma", ma);
            return (MauSac) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean add(MauSac mauSac) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(MauSac mauSac) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(MauSac mauSac) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
