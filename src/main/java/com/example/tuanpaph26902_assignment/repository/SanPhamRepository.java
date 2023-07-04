package com.example.tuanpaph26902_assignment.repository;

import com.example.tuanpaph26902_assignment.entity.SanPham;
import com.example.tuanpaph26902_assignment.utility.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SanPhamRepository extends GenericRepo<SanPham, UUID> {

    public SanPhamRepository() {
        super(SanPham.class);
    }

//    public List<SanPham> getAll() {
//        List<SanPham> sanPhams = new ArrayList<>();
//
//        try (Session ss = HibernateUtil.getFactory().openSession()) {
//            Query query = ss.createQuery("FROM SanPham", SanPham.class);
//            sanPhams = query.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return sanPhams;
//    }
//
//    public SanPham getOne(UUID id) {
//        SanPham sp = null;
//        try (Session ss = HibernateUtil.getFactory().openSession()) {
//            Query query = ss.createQuery("FROM SanPham where id=: id", SanPham.class);
//            query.setParameter("id", id);
//            sp = (SanPham) query.getSingleResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sp;
//    }
//
//    public SanPham getByMa(String ma) {
//        SanPham sp = null;
//        try (Session ss = HibernateUtil.getFactory().openSession()) {
//            Query query = ss.createQuery("FROM SanPham where ma=: ma", SanPham.class);
//            query.setParameter("ma", ma);
//            sp = (SanPham) query.getSingleResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sp;
//    }
//
//    public Boolean add(SanPham sp) {
//        Transaction transaction = null;
//        try (Session ss = HibernateUtil.getFactory().openSession()) {
//            transaction = ss.beginTransaction();
//            ss.persist(sp);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return false;
//    }
//
//    public Boolean update(SanPham sp) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.merge(sp);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return false;
//    }
//
//    public Boolean delete(SanPham sp) {
//        Transaction transaction = null;
//        try (Session ss = HibernateUtil.getFactory().openSession()) {
//            transaction = ss.beginTransaction();
//            ss.delete(sp);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return false;
//    }
}
