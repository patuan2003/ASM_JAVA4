package com.example.tuanpaph26902_assignment.utility;

import com.example.tuanpaph26902_assignment.entity.ChiTietSanPham;
import com.example.tuanpaph26902_assignment.entity.ChucVu;
import com.example.tuanpaph26902_assignment.entity.CuaHang;
import com.example.tuanpaph26902_assignment.entity.DongSanPham;
import com.example.tuanpaph26902_assignment.entity.KhachHang;
import com.example.tuanpaph26902_assignment.entity.MauSac;
import com.example.tuanpaph26902_assignment.entity.NhaSanXuat;
import com.example.tuanpaph26902_assignment.entity.NhanVien;
import com.example.tuanpaph26902_assignment.entity.SanPham;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT,
                "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER,
                "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL,
                "jdbc:sqlserver://localhost:1433;databaseName=FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041;encrypt=true;trustServerCertificate=true;");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);

        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(ChiTietSanPham.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(CuaHang.class);
        conf.addAnnotatedClass(DongSanPham.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(NhaSanXuat.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return FACTORY;
    }


}
