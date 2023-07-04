package com.example.tuanpaph26902_assignment.repository;

import com.example.tuanpaph26902_assignment.entity.ChiTietSanPham;
import com.example.tuanpaph26902_assignment.utility.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class ChiTietSanPhamRepository {
    public List<ChiTietSanPham> getAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM ChiTietSanPham", ChiTietSanPham.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChiTietSanPham getOne(UUID id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM ChiTietSanPham c where c.id=:id", ChiTietSanPham.class);
            query.setParameter("id", id);
            return (ChiTietSanPham) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean add(ChiTietSanPham chiTietSanPham) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(chiTietSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(ChiTietSanPham chiTietSanPham) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(chiTietSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(ChiTietSanPham chiTietSanPham) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(chiTietSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
      new ChiTietSanPhamRepository().getAll().forEach(s -> System.out.println(s));
    }
}
