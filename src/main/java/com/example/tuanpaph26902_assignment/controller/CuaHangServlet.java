package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.CuaHang;
import com.example.tuanpaph26902_assignment.service.Service;
import com.example.tuanpaph26902_assignment.service.impl.CuaHangServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "CuaHangController", value = {
        "/cua-hang/hien-thi",  // GET
        "/cua-hang/add",      // POST
        "/cua-hang/update",      // POST
        "/cua-hang/delete",    // GET
        "/cua-hang/view-update",// GET
        "/cua-hang/view-add", // GET
        "/cua-hang/detail"    // GET
})
public class CuaHangServlet extends HttpServlet {

    private Service<CuaHang> service = new CuaHangServiceImpl();

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
        request.setAttribute("chs", service.getAll());
        request.getRequestDispatcher("/view/cua-hang/cua-hang.jsp").forward(request, response);
    }

    private UUID idUpdate = null;

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CuaHang ch = service.getOne(UUID.fromString(id));
        request.setAttribute("ch", ch);
        request.getRequestDispatcher("/view/cua-hang/update-cua-hang.jsp").forward(request, response);
        idUpdate = UUID.fromString(id);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CuaHang ch = service.getOne(UUID.fromString(id));
        request.setAttribute("ch", ch);
        this.hienThi(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        service.delete(service.getOne(UUID.fromString(id)));
        response.sendRedirect("/cua-hang/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CuaHang ch = new CuaHang();
        try {
            BeanUtils.populate(ch, request.getParameterMap());
            String validate = service.validate(ch);
            if (validate != null) {
                request.setAttribute("errorCH", validate);
                this.hienThi(request, response);
                return;
            }
            service.add(ch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/cua-hang/hien-thi");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        CuaHang ch = new CuaHang(idUpdate, ma, ten, diaChi, thanhPho, quocGia, null);
        service.update(ch);
        response.sendRedirect("/cua-hang/hien-thi");
    }
}
