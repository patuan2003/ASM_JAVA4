package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.NhanVien;
import com.example.tuanpaph26902_assignment.request.NhanVienRequest;
import com.example.tuanpaph26902_assignment.service.impl.ChucVuServiceImpl;
import com.example.tuanpaph26902_assignment.service.impl.CuaHangServiceImpl;
import com.example.tuanpaph26902_assignment.service.impl.NhanVienServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@WebServlet(name = "NhanVienController", value = {
        "/nhan-vien/hien-thi",  // GET
        "/nhan-vien/add",      // POST
        "/nhan-vien/update",      // POST
        "/nhan-vien/delete",    // GET
        "/nhan-vien/view-update",// GET
        "/nhan-vien/view-add", // GET
        "/nhan-vien/detail"    // GET
})
public class NhanVienServlet extends HttpServlet {

    private NhanVienServiceImpl service = new NhanVienServiceImpl();
    private ChucVuServiceImpl chucVuService = new ChucVuServiceImpl();
    private CuaHangServiceImpl cuaHangService = new CuaHangServiceImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
        } else {
            this.update(request, response);
        }
    }

    private void displayCombobox(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("chucVus", chucVuService.getAll());
        request.setAttribute("cuaHangs", cuaHangService.getAll());
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nvs", service.getAll());
        this.displayCombobox(request, response);
        request.setAttribute("sdf", sdf);
        request.getRequestDispatcher("/view/nhan-vien/nhan-vien.jsp").forward(request, response);
    }

    private String idUpdate = null;

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NhanVien nv = service.getOne(UUID.fromString(id));
        request.setAttribute("nv", nv);
        request.setAttribute("ngaySinh", sdf.format(nv.getNgaySinh()));
        this.displayCombobox(request, response);
        request.getRequestDispatcher("/view/nhan-vien/update-nhan-vien.jsp").forward(request, response);
        idUpdate = id;
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NhanVien nv = service.getOne(UUID.fromString(id));
        request.setAttribute("nv", nv);
        request.setAttribute("ngaySinh", sdf.format(nv.getNgaySinh()));
        this.hienThi(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        service.delete(service.getOne(UUID.fromString(id)));
        response.sendRedirect("/nhan-vien/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        NhanVienRequest nhanVienRequest = new NhanVienRequest();
        try {
            BeanUtils.populate(nhanVienRequest, request.getParameterMap());
            String validate = service.validate(nhanVienRequest);
            if (validate != null) {
                request.setAttribute("errorNV", validate);
                this.hienThi(request, response);
                return;
            }
            service.save(nhanVienRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/nhan-vien/hien-thi");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NhanVienRequest nhanVienRequest = new NhanVienRequest();
        nhanVienRequest.setId(idUpdate);
        try {
            BeanUtils.populate(nhanVienRequest, request.getParameterMap());
            service.update(nhanVienRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/nhan-vien/hien-thi");
    }
}
