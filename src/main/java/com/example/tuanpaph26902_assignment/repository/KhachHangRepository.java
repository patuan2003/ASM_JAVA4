package com.example.tuanpaph26902_assignment.repository;

import com.example.tuanpaph26902_assignment.entity.KhachHang;
import com.example.tuanpaph26902_assignment.utility.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class KhachHangRepository {
    public List<KhachHang> getAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM KhachHang ");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHang getOne(UUID id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM KhachHang where id=:id");
            query.setParameter("id", id);
            return (KhachHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHang getByMa(String ma) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM KhachHang where ma=:ma");
            query.setParameter("ma", ma);
            return (KhachHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean add(KhachHang khachHang) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(KhachHang khachHang) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(KhachHang khachHang) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
