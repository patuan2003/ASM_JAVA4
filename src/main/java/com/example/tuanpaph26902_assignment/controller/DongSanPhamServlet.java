package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.DongSanPham;
import com.example.tuanpaph26902_assignment.service.Service;
import com.example.tuanpaph26902_assignment.service.impl.DongSanPhamServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "DongSPController", value = {
        "/dong-sp/hien-thi",  // GET
        "/dong-sp/add",      // POST
        "/dong-sp/update",      // POST
        "/dong-sp/delete",    // GET
        "/dong-sp/view-update",// GET
        "/dong-sp/view-add", // GET
        "/dong-sp/detail"    // GET
})
public class DongSanPhamServlet extends HttpServlet {
    private Service<DongSanPham> service = new DongSanPhamServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThi(request, response);
        } else if (uri.contains("delete")) {
            this.remove(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdate(request, response);
        } else {
            this.hienThi(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            this.add(request, response);
        }
        if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dongSPs", service.getAll());
        request.getRequestDispatcher("/view/dong-sp/DongSP.jsp").forward(request, response);
    }

    private UUID idUpdate = null;

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        DongSanPham sp = service.getOne(UUID.fromString(id));
        request.setAttribute("sp", sp);
        request.getRequestDispatcher("/view/dong-sp/update-DongSP.jsp").forward(request, response);
        idUpdate = UUID.fromString(id);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        DongSanPham sp = service.getOne(UUID.fromString(id));
        request.setAttribute("sp", sp);
        this.hienThi(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        service.delete(service.getOne(UUID.fromString(id)));
        response.sendRedirect("/dong-sp/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DongSanPham sp = new DongSanPham();
        try {
            BeanUtils.populate(sp, request.getParameterMap());
            String validate = service.validate(sp);
            if (validate != null) {
                request.setAttribute("errorDSP", validate);
                this.hienThi(request, response);
                return;
            }
            service.add(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/dong-sp/hien-thi");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DongSanPham sp = new DongSanPham();
        sp.setId(idUpdate);
        try {
            BeanUtils.populate(sp, request.getParameterMap());
            service.update(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/dong-sp/hien-thi");
    }
}
