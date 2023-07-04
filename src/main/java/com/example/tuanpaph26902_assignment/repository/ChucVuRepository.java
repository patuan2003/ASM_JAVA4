package com.example.tuanpaph26902_assignment.repository;

import com.example.tuanpaph26902_assignment.entity.ChucVu;
import com.example.tuanpaph26902_assignment.utility.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChucVuRepository {
    public List<ChucVu> getAll() {
        List<ChucVu> list = new ArrayList<>();
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM ChucVu");
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ChucVu getOne(UUID id) {
        ChucVu cv = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM ChucVu where id =:id");
            query.setParameter("id", id);
            cv = (ChucVu) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cv;
    }

    public ChucVu getByMa(String ma) {
        ChucVu cv = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM ChucVu where ma =:ma");
            query.setParameter("ma", ma);
            cv = (ChucVu) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cv;
    }

    public Boolean add(ChucVu cv) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(cv);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(ChucVu cv) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(cv);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(ChucVu cv) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(cv);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
