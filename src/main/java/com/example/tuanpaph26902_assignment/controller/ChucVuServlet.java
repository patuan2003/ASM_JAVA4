package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.ChucVu;
import com.example.tuanpaph26902_assignment.service.Service;
import com.example.tuanpaph26902_assignment.service.impl.ChucVuServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "ChucVuController", value = {
        "/chuc-vu/hien-thi",  // GET
        "/chuc-vu/add",      // POST
        "/chuc-vu/update",      // POST
        "/chuc-vu/delete",    // GET
        "/chuc-vu/view-update",// GET
        "/chuc-vu/view-add", // GET
        "/chuc-vu/detail"    // GET
})
public class ChucVuServlet extends HttpServlet {

    private Service<ChucVu> chucVuService = new ChucVuServiceImpl();

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

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("chucVus", chucVuService.getAll());
        request.getRequestDispatcher("/view/chuc-vu/chuc-vu.jsp").forward(request, response);
    }

    private UUID idUpdate = null;

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ChucVu cv = chucVuService.getOne(UUID.fromString(id));
        request.setAttribute("cv", cv);
        request.getRequestDispatcher("/view/chuc-vu/update-chuc-vu.jsp").forward(request, response);
        idUpdate = UUID.fromString(id);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ChucVu cv = chucVuService.getOne(UUID.fromString(id));
        request.setAttribute("cv", cv);
        request.setAttribute("chucVus", chucVuService.getAll());
        request.getRequestDispatcher("/view/chuc-vu/chuc-vu.jsp").forward(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        chucVuService.delete(chucVuService.getOne(UUID.fromString(id)));
        response.sendRedirect("/chuc-vu/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChucVu cv = new ChucVu();
        try {
            BeanUtils.populate(cv, request.getParameterMap());
            String validate = chucVuService.validate(cv);
            if (validate != null) {
                request.setAttribute("errorCV", validate);
                this.hienThi(request, response);
                return;
            }
            chucVuService.add(cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/chuc-vu/hien-thi");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        ChucVu cv = new ChucVu();
        cv.setId(idUpdate);
        cv.setMa(ma);
        cv.setTen(ten);
        chucVuService.update(cv);
        response.sendRedirect("/chuc-vu/hien-thi");
    }
}
