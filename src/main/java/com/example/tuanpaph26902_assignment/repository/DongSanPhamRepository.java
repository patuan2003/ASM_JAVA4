package com.example.tuanpaph26902_assignment.repository;

import com.example.tuanpaph26902_assignment.entity.DongSanPham;
import com.example.tuanpaph26902_assignment.utility.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class DongSanPhamRepository {
    public List<DongSanPham> getAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM DongSanPham");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DongSanPham getOne(UUID id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM DongSanPham where id =: id");
            query.setParameter("id", id);
            return (DongSanPham) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DongSanPham getByMa(String ma) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM DongSanPham where ma =: ma");
            query.setParameter("ma", ma);
            return (DongSanPham) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean add(DongSanPham dongSanPham) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(dongSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(DongSanPham dongSanPham) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(dongSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(DongSanPham dongSanPham) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(dongSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
