package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.MauSac;
import com.example.tuanpaph26902_assignment.service.Service;
import com.example.tuanpaph26902_assignment.service.impl.MauSacServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MauSacController", value = {
        "/mau-sac/hien-thi",  // GET
        "/mau-sac/add",      // POST
        "/mau-sac/update",      // POST
        "/mau-sac/delete",    // GET
        "/mau-sac/view-update",// GET
        "/mau-sac/view-add", // GET
        "/mau-sac/detail"    // GET
})
public class MauSacServlet extends HttpServlet {
    private Service<MauSac> service = new MauSacServiceImpl();

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
            this.save(request, response);
        }
        if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mauSacs", service.getAll());
        request.getRequestDispatcher("/view/mau-sac/mau-sac.jsp").forward(request, response);
    }

    protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MauSac ms = new MauSac();
        try {
            BeanUtils.populate(ms, request.getParameterMap());
            String validate = service.validate(ms);
            if (validate != null) {
                request.setAttribute("errorMS", validate);
                this.hienThi(request, response);
                return;
            }
            service.add(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/mau-sac/hien-thi");
    }

    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        MauSac ms = this.service.getOne(UUID.fromString(id));
        request.setAttribute("ms", ms);
        this.hienThi(request, response);
    }

    protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        service.delete(service.getOne(UUID.fromString(id)));
        response.sendRedirect("/mau-sac/hien-thi");
    }

    private UUID idUpdate = null;

    protected void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        MauSac ms = service.getOne(UUID.fromString(id));
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("/view/mau-sac/update-mau-sac.jsp").forward(request, response);
        idUpdate = UUID.fromString(id);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MauSac ms = new MauSac();
        ms.setId(idUpdate);
        try {
            BeanUtils.populate(ms, request.getParameterMap());
            service.update(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/mau-sac/hien-thi");
    }
}
