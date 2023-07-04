package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.SanPham;
import com.example.tuanpaph26902_assignment.service.impl.SanPhamServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "SanPhamController", value = {
        "/san-pham/hien-thi",  // GET
        "/san-pham/add",      // POST
        "/san-pham/update",      // POST
        "/san-pham/delete",    // GET
        "/san-pham/view-update",// GET
        "/san-pham/view-add", // GET
        "/san-pham/detail"    // GET
})
public class SanPhamServlet extends HttpServlet {

    private SanPhamServiceImpl sanPhamService = new SanPhamServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiSanPham(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdate(request, response);
        } else {
            this.hienThiSanPham(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            this.save(request, response);
        }
        if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    private void hienThiSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sanPhams", sanPhamService.getAll());
        request.getRequestDispatcher("/view/san-pham/san-pham.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SanPham sp = new SanPham();
        try {
            BeanUtils.populate(sp, request.getParameterMap());
            String validate = sanPhamService.validate(sp);
            if (validate != null) {
                request.setAttribute("errorSP", validate);
                this.hienThiSanPham(request, response);
                return;
            }
            sanPhamService.add(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/san-pham/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        SanPham sp = this.sanPhamService.getOne(UUID.fromString(id));
        request.setAttribute("sp", sp);
        request.setAttribute("sanPhams", sanPhamService.getAll());
        request.getRequestDispatcher("/view/san-pham/san-pham.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        sanPhamService.delete(sanPhamService.getOne(UUID.fromString(id)));
        response.sendRedirect("/san-pham/hien-thi");
    }

    private UUID idUpdate = null;

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        SanPham sp = sanPhamService.getOne(UUID.fromString(id));
        request.setAttribute("sp", sp);
        request.getRequestDispatcher("/view/san-pham/update-san-pham.jsp").forward(request, response);
        idUpdate = UUID.fromString(id);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SanPham sp = new SanPham();
        sp.setId(idUpdate);
        try {
            BeanUtils.populate(sp, request.getParameterMap());
            sanPhamService.update(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/san-pham/hien-thi");
    }
}
