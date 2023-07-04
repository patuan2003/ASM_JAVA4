package com.example.tuanpaph26902_assignment.repository;

import com.example.tuanpaph26902_assignment.entity.NhanVien;
import com.example.tuanpaph26902_assignment.utility.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class NhanVienRepository {

    public List<NhanVien> getAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM NhanVien ");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien getOne(UUID id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM NhanVien where id=:id");
            query.setParameter("id", id);
            return (NhanVien) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien getByMa(String ma) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM NhanVien where ma=:ma");
            query.setParameter("ma", ma);
            return (NhanVien) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean add(NhanVien nhanVien) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(nhanVien);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(NhanVien nhanVien) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(nhanVien);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(NhanVien nhanVien) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nhanVien);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        new NhanVienRepository().getAll().forEach(s -> System.out.println(s));
    }
}
