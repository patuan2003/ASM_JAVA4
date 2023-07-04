package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.ChiTietSanPham;
import com.example.tuanpaph26902_assignment.request.ChiTietSanPhamRequest;
import com.example.tuanpaph26902_assignment.service.impl.ChiTietSanPhamServiceImpl;
import com.example.tuanpaph26902_assignment.service.impl.DongSanPhamServiceImpl;
import com.example.tuanpaph26902_assignment.service.impl.MauSacServiceImpl;
import com.example.tuanpaph26902_assignment.service.impl.NhaSanXuatServiceImpl;
import com.example.tuanpaph26902_assignment.service.impl.SanPhamServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "ChiTietSPController", value = {
        "/ctsp/hien-thi",  // GET
        "/ctsp/add",      // POST
        "/ctsp/update",      // POST
        "/ctsp/delete",    // GET
        "/ctsp/view-update",// GET
        "/ctsp/view-add", // GET
        "/ctsp/detail"    // GET
})

public class ChiTietSanPhamServlet extends HttpServlet {
    private ChiTietSanPhamServiceImpl chiTietSPService = new ChiTietSanPhamServiceImpl();
    private SanPhamServiceImpl sanPhamService = new SanPhamServiceImpl();
    private MauSacServiceImpl mauSacService = new MauSacServiceImpl();
    private DongSanPhamServiceImpl dongSPService = new DongSanPhamServiceImpl();
    private NhaSanXuatServiceImpl nsxService = new NhaSanXuatServiceImpl();

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

    private void displayCombobox(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("sanPhams", sanPhamService.getAll());
        request.setAttribute("mauSacs", mauSacService.getAll());
        request.setAttribute("nhaSanXuats", nsxService.getAll());
        request.setAttribute("dongSanPhams", dongSPService.getAll());
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ctsps", chiTietSPService.getAll());
        displayCombobox(request, response);
        request.getRequestDispatcher("/view/chi-tiet-sp/chi-tiet-sp.jsp").forward(request, response);
    }

    private String idUpdate = null;

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ChiTietSanPham ctsp = chiTietSPService.getOne(UUID.fromString(id));
        this.displayCombobox(request, response);
        request.setAttribute("sp", ctsp);
        request.getRequestDispatcher("/view/chi-tiet-sp/update-ctsp.jsp").forward(request, response);
        idUpdate = id;
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ChiTietSanPham ctsp = chiTietSPService.getOne(UUID.fromString(id));
        request.setAttribute("sp", ctsp);
        this.hienThi(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        chiTietSPService.delete(chiTietSPService.getOne(UUID.fromString(id)));
        response.sendRedirect("/ctsp/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChiTietSanPhamRequest chiTietSanPhamRequest = new ChiTietSanPhamRequest();
        try {
            BeanUtils.populate(chiTietSanPhamRequest, request.getParameterMap());
            String validate = chiTietSPService.validate(chiTietSanPhamRequest);
            if (validate != null) {
                System.out.println("validate: " + validate);
                request.setAttribute("errorCTSP", validate);
                this.hienThi(request, response);
                return;
            }
            chiTietSPService.save(chiTietSanPhamRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/ctsp/hien-thi");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChiTietSanPhamRequest ctsp = new ChiTietSanPhamRequest();
        ctsp.setId(idUpdate);
        try {
            BeanUtils.populate(ctsp, request.getParameterMap());
            chiTietSPService.update(ctsp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/ctsp/hien-thi");
    }
}
