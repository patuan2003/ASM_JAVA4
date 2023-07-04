package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.KhachHang;
import com.example.tuanpaph26902_assignment.request.KhanhHangRequest;
import com.example.tuanpaph26902_assignment.service.impl.KhachHangServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@WebServlet(name = "KhachHangController", value = {
        "/khach-hang/hien-thi",  // GET
        "/khach-hang/add",      // POST
        "/khach-hang/update",      // POST
        "/khach-hang/delete",    // GET
        "/khach-hang/view-update",// GET
        "/khach-hang/view-add", // GET
        "/khach-hang/detail"    // GET
})
public class KhachHangServlet extends HttpServlet {

    private KhachHangServiceImpl service = new KhachHangServiceImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThi(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
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
            this.save(request, response);
        }
        if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sdf", sdf);
        request.setAttribute("khs", service.getAll());
        request.getRequestDispatcher("/view/khach-hang/khach-hang.jsp").forward(request, response);
    }

    protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        KhanhHangRequest khanhHangRequest = new KhanhHangRequest();
        try {
            BeanUtils.populate(khanhHangRequest, request.getParameterMap());
            String check = service.validate(khanhHangRequest);
            if (check != null) {
                request.setAttribute("errorKH", check);
                this.hienThi(request, response);
                return;
            }
            service.save(khanhHangRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/khach-hang/hien-thi");
    }

    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        KhachHang kh = this.service.getOne(UUID.fromString(id));
        request.setAttribute("kh", kh);
        request.setAttribute("ngaySinh", sdf.format(kh.getNgaySinh()));
        this.hienThi(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        service.delete(service.getOne(UUID.fromString(id)));
        response.sendRedirect("/khach-hang/hien-thi");
    }

    private String idUpdate = null;

    protected void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        KhachHang kh = service.getOne(UUID.fromString(id));
        request.setAttribute("ngaySinh", sdf.format(kh.getNgaySinh()));
        request.setAttribute("kh", kh);
        request.getRequestDispatcher("/view/khach-hang/update-khach-hang.jsp").forward(request, response);
        idUpdate = id;
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        KhanhHangRequest khanhHangRequest = new KhanhHangRequest();
        khanhHangRequest.setId(idUpdate);
        try {
            BeanUtils.populate(khanhHangRequest, request.getParameterMap());
            service.update(khanhHangRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/khach-hang/hien-thi");
    }
}
